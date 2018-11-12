/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Jorge
 */
public class Fila {
    /**
     * Local variables
     */
    String idToken;
    String lexema;
    int token;
    String tipoDato;
    String valor;
    int linea;
    
    /**
     * Constructor - Defines an lexem and its characteristics. They represent a row in the simbols table
     * @param idToken - The ID which represent the specific token type 
     * @param lexema - The lexema classified
     * @param token - The number of the token type
     * @param linea - The line number of the token
     */
    public Fila(String idToken, String lexema, int token, int linea){
        this.idToken = idToken;
        this.lexema = lexema;
        this.token = token;
        this.tipoDato = "";
        this.valor = "";
        this.linea = linea;
    }
    
    /**
     * Method to set the data type of a lexeme if this is an ID
     * @param tipoDato
     */
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * Method to set the value of a lexeme if this is an ID
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * 
     * @return 
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * 
     * @return 
     */
    public String getValor() {
        return valor;
    }
    
    /**
     * 
     * @return 
     */
    public String getIdToken() {
        return idToken;
    }

    /**
     * 
     * @param idToken 
     */
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    /**
     * 
     * @return 
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * 
     * @param lexema 
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * 
     * @return 
     */
    public int getToken() {
        return token;
    }

    /**
     * 
     * @param token 
     */
    public void setToken(int token) {
        this.token = token;
    }
    
    /**
     * 
     * @return 
     */
    public int getLinea() {
        return linea;
    }
    
    /**
     * 
     * @param linea 
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }
}
