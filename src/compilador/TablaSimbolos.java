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
public class TablaSimbolos {
    /**
     * Local variables
     */
    ArrayList<Fila> tabla;
    String idToken, contenidoTabla;
    int numeroID;
    boolean existeID;

    /**
     * 
     */
    public TablaSimbolos() {
        tabla = new ArrayList<>();
    }    
    
    /**
     * Method to get the ID of the token type and add the lexeme and its characteristcs inside the symbols table
     * @param lexema - The lexeme
     * @param token - The token type in which the lexeme was classified
     */
    public void agregarElemento(String lexema, int token, int linea) {
        idToken="";
        if (!(token == 0)) {
            if(lexema.equals("Execute"))        idToken = "PR1";
            else if(lexema.equals("Write"))     idToken = "PR2";
            else if(lexema.equals("Read"))      idToken = "PR3";
            else if(lexema.equals("Inc"))       idToken = "PR4";
            else if(lexema.equals("True"))      idToken = "PR5";
            else if(lexema.equals("False"))     idToken = "PR6";
            else if(lexema.equals("Numeric"))   idToken = "TD1";
            else if(lexema.equals("String"))    idToken = "TD2";
            else if(lexema.equals("Boolean"))   idToken = "TD3";
            else if(lexema.equals("Null"))      idToken = "TD4";
            else if(lexema.equals("If"))        idToken = "SC1";
            else if(lexema.equals("Else"))      idToken = "SC2";
            else if(lexema.equals("For"))       idToken = "SC3";
            else if(lexema.equals("While"))     idToken = "SC4";
            else if(lexema.equals("And")){      idToken = "OL1";
                token = 6;
            }else if(lexema.equals("Or")){
                idToken = "OL2";
                token = 6;
            }else if(lexema.equals("{"))         idToken = "DL1";
            else if(lexema.equals("}"))         idToken = "DL2";
            else if(lexema.equals("("))         idToken = "DL3";
            else if(lexema.equals(")"))         idToken = "DL4";
            else if(lexema.equals(";"))         idToken = "DL5";
            else if(lexema.equals(","))         idToken = "DL6";
            else if(lexema.equals(":"))         idToken = "DL7";
            else if(lexema.equals("+"))         idToken = "OP1";
            else if(lexema.equals("-"))         idToken = "OP2";
            else if(lexema.equals("*"))         idToken = "OP3";
            else if(lexema.equals("/"))         idToken = "OP4";
            else if(lexema.equals("="))         idToken = "OA";
            else if(lexema.equals("<"))         idToken = "OR1";
            else if(lexema.equals(">"))         idToken = "OR2";
            else if(lexema.equals("<="))        idToken = "OR3";
            else if(lexema.equals(">="))        idToken = "OR4";
            else if(lexema.equals("=="))        idToken = "OR5";
            else if(lexema.equals("!="))        idToken = "OR6";
            else if(token == 9)                 idToken = "CT";
            else if(token == 8)                 idToken = "CD";
            else if(token == 2){
                existeID = false;
                for (Fila fila : tabla) {
                    if (fila.lexema.equals(lexema)) {
                        idToken = fila.idToken;
                        existeID = true;
                        break;                        
                    }
                }    
                if (!existeID) {
                    idToken = "ID" + numeroID;
                    numeroID++;
                }
            }
        }else
            idToken = "ERR";
        tabla.add(new Fila(idToken, lexema, token, linea));
    }

    public ArrayList<Fila> getTabla() {
        return tabla;
    }
    
}