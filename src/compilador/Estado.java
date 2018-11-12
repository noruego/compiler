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
public class Estado {
    /**
     * Local variables
     */
    int estado;
    ArrayList<String> caracteresValidos;
    boolean estadoFinal;

    /**
     * Constructor - To set the attributes of the object Estado
     * @param estado - The number of the next state
     * @param caracteresValidos - Array of elements with which it can move to the next state
     * @param estadoFinal - To indicate if the next state is final
     */
    public Estado(int estado, ArrayList<String> caracteresValidos, boolean estadoFinal){
        this.estado = estado;
        this.caracteresValidos = caracteresValidos;
        this.estadoFinal = estadoFinal;
    }

    /**
     * Constructor - To set the attributes of the object Estado
     * @param estado - The number of the next state
     * @param caracterValido - Element with which it can move to the next state
     * @param estadoFinal - To indicate if the next state is final
     */
    public Estado(int estado, String caracterValido, boolean estadoFinal){
        this.estado = estado;
        this.caracteresValidos = new ArrayList<>();
        caracteresValidos.add(caracterValido);
        this.estadoFinal = estadoFinal;
    }

    /**
     * Constructor - To set the attributes of the object Estado
     * @param estado - The number of the current state
     * @param estadoFinal -  To indicate if the current state is final
     */
    public Estado(int estado, boolean estadoFinal){
        this.estado = estado;
        this.estadoFinal = estadoFinal;
    }

    /**
     * Method to get the state number of an object Estado
     * @return int - state number
     */
    public int getEstado() {
        return estado;
    }
    
    /**
     * Method to get the valid characters through which a state is reached
     * @return ArrayList<> - All the valid characters
     */
    public ArrayList<String> getCaracteres_validos() {
        return caracteresValidos;
    }
    
    /**
     * Method to get the status of the property "estadoFinal"
     * @return boolean - Indicate if the state is final
     */
    public boolean estadoFinal(){
        return estadoFinal;
    }
}
