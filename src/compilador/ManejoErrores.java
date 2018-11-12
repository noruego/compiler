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
public class ManejoErrores {
    /**
     * Local variables
     */
    Alfabeto alfabeto;
    ArrayList<String> errores;
    
    /**
     * Constructor - Defines the instances of the objects of other classes
     */
    public ManejoErrores() {
        alfabeto = new Alfabeto();
        errores = new ArrayList<>();
    }
    
    /**
     * Method to classify an error ocurred during the analysis
     * @param estado - The last state reached
     * @param lexema - The lexeme evaluated
     * @param caracter - The character evaluated
     * @param linea - The number of the line where the lexeme is
     */
    public void addErrorLexico(int estado, String lexema, char caracter, int linea){
        if(!(alfabeto.delimitadores.contains(caracter + "") || alfabeto.especiales.contains(caracter + "") || alfabeto.mayusculas.contains(caracter + "") ||
                alfabeto.minusculas.contains(caracter + "") || alfabeto.numeros.contains(caracter + "") || alfabeto.operadores.contains(caracter + "")))            
            errores.add("100,"+ lexema + "," + caracter + "," + linea);
        if(estado == 0){
            if(alfabeto.mayusculas.contains(caracter + ""))
                errores.add("104,"+ lexema + "," + caracter + "," + linea);
            else if(alfabeto.especiales.contains(caracter + ""))
                errores.add("105,"+ lexema + "," + caracter + "," + linea);
        }
        if(estado > 14)
            errores.add("104,"+ lexema + "," + caracter + "," + linea);
        if(estado == 4)
            errores.add("105,"+ lexema + "," + caracter + "," + linea);
        if(estado > 6 && estado < 10)
            errores.add("103,"+ lexema + "," + caracter + "," + linea);
        if(estado > 9 && estado < 13)
            errores.add("101,"+ lexema + "," + caracter + "," + linea);
        if(estado > 12 && estado < 15)
            errores.add("102,"+ lexema + "," + caracter + "," + linea);
    }
    
    /**
     * Method to get all the errors produced during the analysis 
     * @return ArrayList<> - List of errors
     */
    public ArrayList<String> getErrores(){
        return errores;
    }
}
