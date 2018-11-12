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
public class AnalizadorSintactico {
    
    TablaAnalisis tAnalisis;
    ArrayList<Fila> codigo;
    ArrayList<String> reglas;
    String[] componentes; 
    String idToken, elemento, noTerminal, produccion, instruccionMayor, esperado, cadenaError, elementoAux;
    int lineaError;
        
    public AnalizadorSintactico() {
        tAnalisis = new TablaAnalisis();
        tAnalisis.generarTabla();
    }
    
    public void analizarEstructuras(ArrayList<Fila> tabla){
        cadenaError = "";
        codigo = new ArrayList<>();
        tabla.forEach((tabla1) -> {
            codigo.add(tabla1);
        });
        reglas = new ArrayList<>();
        reglas.add("$");
        reglas.add("Main");
        codigo.add(new Fila("$$$", "$", 0, 0));
        
        while (!codigo.isEmpty()) {  
            idToken = codigo.get(0).getIdToken().substring(0, 2);
            if(!idToken.equals("$$")){
                switch (idToken) {
                    case "ID": elemento = "Id"; break;
                    case "CD": elemento = "Cadena"; break;
                    case "CT": elemento = "Numero"; break;
                    default:   elemento = codigo.get(0).getLexema(); break;
                }
            } else {
                elemento = "$";
            }
            noTerminal = reglas.get(reglas.size() - 1);
            switch (noTerminal){
                case "Main":
                case "Tipo":
                case "Asignacion":
                case "Expresion":
                case "Lectura":
                case "Escritura":
                case "Contenido":
                case "Si":                
                case "Sino":
                case "Para":
                case "Mientras":
                    instruccionMayor = noTerminal;
                    lineaError = codigo.get(0).getLinea();
                    break;
            } 
            if(noTerminal.equals(elemento)){
                codigo.remove(0);
                reglas.remove((reglas.size()-1));
                continue;
            }
            produccion = tAnalisis.getRegla(elemento, reglas.remove(reglas.size() - 1));
            if(!tAnalisis.getError()){
                if(!produccion.equalsIgnoreCase("λ")){
                    componentes = produccion.split(" ");
                    for (int i = componentes.length- 1 ; 0 <= i; i--) {
                        reglas.add(componentes[i]);
                    }                    
                }
            } else {
                getErrorSintactico(lineaError, noTerminal, elemento, instruccionMayor);
                break;
            }
            
 /*                      
/*            if(reglas.get(reglas.size() - 1).equals(elemento)){
                codigo.remove(0);
                reglas.remove((reglas.size()-1));
                continue;
            }
            produccion = tAnalisis.getRegla(elemento, reglas.remove(reglas.size() - 1));
            if(!tAnalisis.getError()){
                if(!produccion.equalsIgnoreCase("λ")){
                    componentes = produccion.split(" ");
                    for (int i = componentes.length- 1 ; 0 <= i; i--) {
                        reglas.add(componentes[i]);
                    }                    
                }
            } else {
                getErrorSintactico(lineaError, noTerminal);
                //System.out.println("Error en la línea " + lineaError + " en el elemento " + instruccion);
                break;
            }*/
        }
        //System.out.println("Fin");
        /*if (!reglas.isEmpty() && codigo.isEmpty()) {
            System.out.println("Error en la línea " + lineaError + " en el elemento " + instruccion);
        }*/
    }    
    
    public void getErrorSintactico(int lineaError, String noTerminal, String elelemnto, String intruccionMayor){
        /*if(tAnalisis.getVacio(noTerminal)){
             //instruccion = reglas.get(reglas.size() - 1);
        } else {
            esperado = noTerminal;
        }*/
        System.out.println(intruccionMayor + "  " + elelemnto + "  " + noTerminal + "  " + lineaError); 
        
        switch(intruccionMayor){
            case "Main":
                cadenaError = "Error 200: Inicio de programa incorrecto  (Línea : " + lineaError + ")";
                switch(noTerminal){
                    case "Main": cadenaError += " - Se esperaba Execute"; break;
                    case "Parentesis": cadenaError = "Error 210: Uso ilegal de expresion (Linea: " + lineaError + ")- Se esperaba ("; break;
                    case ")": cadenaError = "Error 202: Paréntesis desequilibrados (Linea: " + lineaError + ") - Se esperaba )"; break;
                    case "Bloque": cadenaError = "Error 210: Uso ilegal de expresion (Linea: " + lineaError + ")- Se esperaba {"; break;
                    case "Instrucciones":
                        if(elelemnto.equals("$"))
                            cadenaError = "Error 201: Bloque de código mal construido (Linea: " + lineaError + ") - Se esperaba }";
                        else 
                            cadenaError = "Error 210: Uso ilegal de expresion (Linea: " + lineaError + ") - Elemento \"" + elelemnto; break;                        
                }
                 break;
            case "Tipo":
                cadenaError = "Error 204: Declración incorrecta (Línea : " + lineaError + ")"; 
                switch(noTerminal){
                    case "Id": cadenaError += "Se esperaba un Id"; break;
                    case "Instancia": cadenaError = "Error 203: Fin de instrucción incorrecto (Linea: " + lineaError + ") - Se esperaba ;"; break;
                    case "Instrucciones": cadenaError = "Error 201: Bloque de código mal construido (Linea: " + lineaError + ") - Se esperaba }"; break;
                }
                break;
            case "Asignacion":
                cadenaError = "Error 205: Asignación incorrecta (Línea : " + lineaError + ")";
                switch(noTerminal){
                    case "Valor": cadenaError += " - Se esperaba un Valor"; break;
                    case "Opera": cadenaError = "Error 203: Fin de instrucción incorrecto (Linea: " + lineaError + ") - Se esperaba ;"; break;
                    case "Instrucciones": cadenaError = "Error 201: Bloque de código mal construido (Linea: " + lineaError + ") - Se esperaba }"; break;
                }
                break;
            case "Expresion":
                cadenaError = "Error 208: Expresión incorrecta (Línea : " + lineaError + ")"; break;
            case "Contenido":
                cadenaError = "Error 207: Uso de parámetros incorrecto (Línea : " + lineaError + ")";
                switch(noTerminal){
                    case "Parametros": cadenaError = "Error 210: Uso ilegal de expresion (Linea: " + lineaError + ")- Se esperaba ("; break;
                    case "Opera": cadenaError = "Error 202: Paréntesis desequilibrados (Linea: " + lineaError + ") - Se esperaba )"; break;
                    case "Concat": cadenaError = "Error 202: Paréntesis desequilibrados (Linea: " + lineaError + ") - Se esperaba )"; break;
                    case "Termino": cadenaError += " - Se esperaba Valor)"; break;
                    case ";": cadenaError = "Error 203: Fin de instrucción incorrecto (Linea: " + lineaError + ") - Se esperaba ;"; break;
                    case "Instrucciones": cadenaError = "Error 201: Bloque de código mal construido (Linea: " + lineaError + ") - Se esperaba }"; break;
                }
                break;
            case "Lectura":
            case "Escritura":
                cadenaError = "Error 206: Uso de función incorrecto (Línea : " + lineaError + ")"; 
                switch(noTerminal){
                    case "Parametros": cadenaError = "Error 210: Uso ilegal de expresion (Linea: " + lineaError + ")- Se esperaba ("; break;
                }
                break;
            case "Si":                
            case "Sino":
            case "Para":
            case "Mientras":
                cadenaError = "Error 209: Estructura de control incorrecta (Línea : " + lineaError + ")"; break;
        }
    }
    
    public String getError(){
        return cadenaError;
    }
}
