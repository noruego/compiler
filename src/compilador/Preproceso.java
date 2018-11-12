/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class Preproceso {
    /**
     * Local variables
     */
    Alfabeto alfabeto;
    ArrayList<String> instrucciones, lexemas;
    ArrayList<Integer> numeros, numeroLinea;
    String lineas[];
    String lexema;
    int linea, s;
    char caracter;
    
    /**
     * Constructor - The instances of the other classes are created
     */
    public Preproceso(){
        alfabeto = new Alfabeto();
    }
            
    /**
     * Method to clean the code written removing blank lines, spacing and comments.
     * The resulting code is stored in a local ArrayList 
     * @param codigo - The text written in the code panel
     */
    public void setInstrucciones(String codigo){
        numeros = new ArrayList<>();
        instrucciones = new ArrayList<>();
        if(!codigo.equals("")){
            lineas=codigo.split("\n");
            for(int i = 0; i < lineas.length; i++){
                if(!(lineas[i].trim().equals(""))){
                    if(!(lineas[i].trim().charAt(0) == '#')){
                        numeros.add(i + 1);
                        instrucciones.add(lineas[i].trim());
                    }                    
                }                
            }
        }
    }
    
    /**
     * Method to separate the incoming code string in the different lexemes that will be classified in tokens
     */
    public void setLexemas(){
        s = 0;
        lexema = "";
        lexemas = new ArrayList<>();
        numeroLinea = new ArrayList<>();
        for (String instruccion : instrucciones) {
            linea = numeros.get(s);
            s++;
            for (int i = 0; i < instruccion.length(); i++) {
                caracter = instruccion.charAt(i);            
                if(alfabeto.delimitadores.contains(caracter + "") || alfabeto.operadores.contains(caracter + "") || caracter == '"' || caracter==' '){
                    if(!lexema.equals("")){
                        lexemas.add(lexema);
                        numeroLinea.add(linea);
                        lexema = "";
                    }
                    if(caracter != ' ')
                        lexema += caracter;
                    if(caracter == '"'){
                        i++;
                        while(i < instruccion.length()){
                            if(instruccion.charAt(i) == '"'){
                                lexema += caracter;
                                break;
                            }else
                                lexema += instruccion.charAt(i);
                            i++;
                        }
                    }
                    if(caracter == '!' || caracter == '=' || caracter == '>' || caracter == '<'){
                        i++; 
                        if(i < instruccion.length()){
                            if(instruccion.charAt(i) == '='){
                                lexema += instruccion.charAt(i);
                            }else{
                                i--;
                            }
                        }
                    }
                    if(!lexema.equals("")){
                        lexemas.add(lexema); 
                        numeroLinea.add(linea);
                        lexema = "";
                    }
                }else{
                    lexema += caracter;
                }
            }
            if(!lexema.equals("")){
                lexemas.add(lexema);
                numeroLinea.add(linea);
                lexema = "";
            }
        }
    }
    
    /**
     * Method to get all the lexemes produced by the incoming code string
     * @return ArrayList - Lista
     */
    public ArrayList<String> getLexemas(){
        return lexemas;
    }
    
    /**
     * Method to get the line number of the lexemes produced by the incoming code string
     * @return ArrayList - 
     */
    public ArrayList<Integer> getNumLinea(){
        return numeroLinea;
    }
}
