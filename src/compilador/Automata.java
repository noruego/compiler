/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class Automata {
    /**
     * Local variables
     */
    Alfabeto alfabeto;
    HashMap<Estado, ArrayList<Estado>> automata;
    ArrayList<Estado> estados_siguientes;
    ArrayList<String> caracteres_validos;
    int estadoAuxiliar;
    boolean error, siguienteEstado;

    /**
     * Cosntructor - Defines the instances of the objects of other classes
     */
    public Automata() {
        alfabeto = new Alfabeto();
        automata = new HashMap<>();
   }    
    
    /**
     * Method to create the structure of the automata using a HashMap
     */
    public void crearAutomata(){
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(1, alfabeto.delimitadores, true));
        estados_siguientes.add(new Estado(2, "=", true));
        caracteres_validos = new ArrayList<>();
        caracteres_validos.add("<");
        caracteres_validos.add(">");
        estados_siguientes.add(new Estado(3, caracteres_validos, true));
        estados_siguientes.add(new Estado(4, "!", false));
        caracteres_validos = new ArrayList<>();
        caracteres_validos.add("+");
        caracteres_validos.add("-");
        caracteres_validos.add("*");
        caracteres_validos.add("/");
        estados_siguientes.add(new Estado(6, caracteres_validos, true));
        estados_siguientes.add(new Estado(7, alfabeto.numeros, true));
        estados_siguientes.add(new Estado(8, ".", false));
        estados_siguientes.add(new Estado(10, alfabeto.minusculas, true));
        estados_siguientes.add(new Estado(13, String.valueOf('"'), false));
        estados_siguientes.add(new Estado(15, alfabeto.mayusculas.get(0), false));
        estados_siguientes.add(new Estado(18, alfabeto.mayusculas.get(1), false));
        estados_siguientes.add(new Estado(25, alfabeto.mayusculas.get(4), false));
        estados_siguientes.add(new Estado(33, alfabeto.mayusculas.get(5), false));
        estados_siguientes.add(new Estado(36, alfabeto.mayusculas.get(8), false));
        estados_siguientes.add(new Estado(40, alfabeto.mayusculas.get(13), false));
        estados_siguientes.add(new Estado(34, alfabeto.mayusculas.get(14), false));
        estados_siguientes.add(new Estado(47, alfabeto.mayusculas.get(17), false));
        estados_siguientes.add(new Estado(49, alfabeto.mayusculas.get(18), false));
        estados_siguientes.add(new Estado(55, alfabeto.mayusculas.get(19), false));
        estados_siguientes.add(new Estado(57, alfabeto.mayusculas.get(22), false));
        automata.put(new Estado(0, false), estados_siguientes);
        automata.put(new Estado(1, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(5, "=", true));
        automata.put(new Estado(2, true), estados_siguientes);
        automata.put(new Estado(3, true), estados_siguientes);
        automata.put(new Estado(4, false), estados_siguientes);
        automata.put(new Estado(5, true), null);
        automata.put(new Estado(6, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(7, alfabeto.numeros, true));
        estados_siguientes.add(new Estado(8, ".", false));
        automata.put(new Estado(7, true), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(9, alfabeto.numeros, true));
        automata.put(new Estado(8, false), estados_siguientes);
        automata.put(new Estado(9, true), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(11, alfabeto.minusculas, true));
        estados_siguientes.add(new Estado(11, alfabeto.mayusculas, true));
        automata.put(new Estado(10, true), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(11, alfabeto.minusculas, true));
        estados_siguientes.add(new Estado(11, alfabeto.mayusculas, true));
        estados_siguientes.add(new Estado(12, alfabeto.numeros, true));
        automata.put(new Estado(11, true), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(12, alfabeto.numeros, true));
        automata.put(new Estado(12, true), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(13, alfabeto.delimitadores, false));
        estados_siguientes.add(new Estado(13, alfabeto.minusculas, false));
        estados_siguientes.add(new Estado(13, alfabeto.mayusculas, false));
        estados_siguientes.add(new Estado(13, alfabeto.numeros, false));
        estados_siguientes.add(new Estado(13, alfabeto.especiales, false));
        estados_siguientes.add(new Estado(14, String.valueOf('"'), true));
        automata.put(new Estado(13, false), estados_siguientes);
        automata.put(new Estado(14, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(16, alfabeto.minusculas.get(13), false));
        automata.put(new Estado(15, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(17, alfabeto.minusculas.get(3), true));
        automata.put(new Estado(16, false), estados_siguientes);
        automata.put(new Estado(17, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(19, alfabeto.minusculas.get(14), false));
        automata.put(new Estado(18, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(20, alfabeto.minusculas.get(14), false));
        automata.put(new Estado(19, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(21, alfabeto.minusculas.get(11), false));
        automata.put(new Estado(20, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(22, alfabeto.minusculas.get(4), false));
        automata.put(new Estado(21, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(23, alfabeto.minusculas.get(0), false));
        automata.put(new Estado(22, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(24, alfabeto.minusculas.get(13), false));
        automata.put(new Estado(23, false), estados_siguientes);
        automata.put(new Estado(24, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(26, alfabeto.minusculas.get(11), false));
        estados_siguientes.add(new Estado(29, alfabeto.minusculas.get(23), false));
        automata.put(new Estado(25, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(27, alfabeto.minusculas.get(18), false));
        automata.put(new Estado(26, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(28, alfabeto.minusculas.get(4), true));
        automata.put(new Estado(27, false), estados_siguientes);
        automata.put(new Estado(28, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(30, alfabeto.minusculas.get(4), false));
        automata.put(new Estado(29, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(31, alfabeto.minusculas.get(2), false));
        automata.put(new Estado(30, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(32, alfabeto.minusculas.get(20), false));
        automata.put(new Estado(31, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(27, alfabeto.minusculas.get(19), false));
        automata.put(new Estado(32, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(25, alfabeto.minusculas.get(0), false));
        estados_siguientes.add(new Estado(34, alfabeto.minusculas.get(14), false));
        automata.put(new Estado(33, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(35, alfabeto.minusculas.get(17), true));
        automata.put(new Estado(34, false), estados_siguientes);
        automata.put(new Estado(35, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(37, alfabeto.minusculas.get(5), true));
        estados_siguientes.add(new Estado(38, alfabeto.minusculas.get(13), false));
        automata.put(new Estado(36, false), estados_siguientes);
        automata.put(new Estado(37, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(39, alfabeto.minusculas.get(2), true));
        automata.put(new Estado(38, false), estados_siguientes);
        automata.put(new Estado(39, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(41, alfabeto.minusculas.get(20), false));
        automata.put(new Estado(40, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(42, alfabeto.minusculas.get(11), false));
        estados_siguientes.add(new Estado(44, alfabeto.minusculas.get(12), false));
        automata.put(new Estado(41, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(43, alfabeto.minusculas.get(11), true));
        automata.put(new Estado(42, false), estados_siguientes);
        automata.put(new Estado(43, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(45, alfabeto.minusculas.get(4), false));
        automata.put(new Estado(44, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(46, alfabeto.minusculas.get(17), false));
        automata.put(new Estado(45, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(38, alfabeto.minusculas.get(8), false));
        automata.put(new Estado(46, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(48, alfabeto.minusculas.get(4), true));
        automata.put(new Estado(47, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(16, alfabeto.minusculas.get(0), true));
        automata.put(new Estado(48, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(50, alfabeto.minusculas.get(19), false));
        automata.put(new Estado(49, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(51, alfabeto.minusculas.get(17), false));
        automata.put(new Estado(50, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(52, alfabeto.minusculas.get(8), false));
        automata.put(new Estado(51, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(53, alfabeto.minusculas.get(13), false));
        automata.put(new Estado(52, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(54, alfabeto.minusculas.get(6), false));
        automata.put(new Estado(53, false), estados_siguientes);
        automata.put(new Estado(54, true), null);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(56, alfabeto.minusculas.get(17), false));
        automata.put(new Estado(55, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(27, alfabeto.minusculas.get(20), false));
        automata.put(new Estado(56, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(58, alfabeto.minusculas.get(7), false));
        estados_siguientes.add(new Estado(60, alfabeto.minusculas.get(17), false));
        automata.put(new Estado(57, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(59, alfabeto.minusculas.get(8), false));
        automata.put(new Estado(58, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(27, alfabeto.minusculas.get(11), false));
        automata.put(new Estado(59, false), estados_siguientes);
        estados_siguientes = new ArrayList<>();
        estados_siguientes.add(new Estado(32, alfabeto.minusculas.get(8), false));
        automata.put(new Estado(60, false), estados_siguientes);        
    }
            
    /**
     * Method to get the next state of a lexeme inside the automata
     * @param estadoActual - The actual state inside the automata 
     * @param caracter - The character through which it can pass to the next state
     * @return 
     */
    public int getEstadoSiguiente(int estadoActual, char caracter){
        error = true;
        siguienteEstado = false;
        estadoAuxiliar = estadoActual;        
        for (Estado estado : automata.keySet()) {
            if (estadoActual == estado.getEstado()) {
                if (automata.get(estado) != null) {
                    for (Estado estadosSig : automata.get(estado)) {
                        if (estadosSig.getCaracteres_validos().contains(String.valueOf(caracter))) {
                            estadoAuxiliar = estadosSig.getEstado();
                            siguienteEstado = true;
                            break;
                        }
                    }
                    if (siguienteEstado) 
                        error = false;
                    break;
                }
            }
        }
        return estadoAuxiliar;
    }    
    
    /**
     * Method to indicate if an error is produced by the automata
     * @return boolean - Indicates if there is an error
     */
    public boolean error() {
        return error;
    }
    
    /**
     * Method to search and know if an state is final or not
     * @param estadoActual - The number of the state to be evaluated
     * @return boolean - Indicate if the state is final
     */
    public boolean estadoFinal(int estadoActual) {
        for(Estado estado : automata.keySet()){
            if (estadoActual == estado.getEstado())
                return estado.estadoFinal();
        }
        return false;
    }
}