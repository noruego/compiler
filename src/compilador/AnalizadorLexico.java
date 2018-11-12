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

public class AnalizadorLexico {
    /**
     * Local variables
     */
    Automata automata;
    TablaSimbolos tablaSimbolos;
    ManejoErrores manejoErrores;
    String listaErrores;
    String lineaError [];
    int estadoActual, clasificado, auxiliar;

    /**
     * Constructor - Defines the instances of the objects of other classes
     */
    public AnalizadorLexico() {
        automata = new Automata();
        automata.crearAutomata();
        tablaSimbolos = new TablaSimbolos();
        manejoErrores = new ManejoErrores();
    }    
    
    /**
     * Method to clissify the lexemes coming of the preprocess
     * @param lexemas - All the lexemes to classify
     * @param linea - List of the line number of eavh lexeme
     */
    public void clasificarLexemas(ArrayList<String> lexemas, ArrayList<Integer> linea){
        manejoErrores.errores = new ArrayList<>();
        tablaSimbolos.tabla = new ArrayList<>();
        tablaSimbolos.numeroID = 1;
        for (int i = 0; i < lexemas.size(); i++) {
            estadoActual = 0;
            for (int j = 0; j < lexemas.get(i).length(); j++) {
                auxiliar = j;
                estadoActual = automata.getEstadoSiguiente(estadoActual, lexemas.get(i).charAt(j));
                if (automata.error()) {
                    manejoErrores.addErrorLexico(estadoActual, lexemas.get(i), lexemas.get(i).charAt(j), linea.get(i));
                    estadoActual = -1;
                    break;
                }
            }
            if (!automata.error()) {
                if(!automata.estadoFinal(estadoActual)){
                    manejoErrores.addErrorLexico(estadoActual, lexemas.get(i), lexemas.get(i).charAt(auxiliar), linea.get(i));
                    estadoActual = -1;
                }
            }
            tablaSimbolos.agregarElemento(lexemas.get(i), obtenerTipoToken(estadoActual), linea.get(i));
        }        
    }
    
    /**
     * Method to get the token type of the classified lexeme
     * @param estado - The last state the automata reached in the classificaton of the lexeme
     * @return  int - The token type which it has been classified
     */
    private int obtenerTipoToken(int estado) {
        switch (estado) {
            case 1:
                clasificado = 7;        //Delimitador
                break;
            case 2:
                clasificado = 3;        //Operador de asignación
                break;
            case 3:
            case 5:
                clasificado = 5;        //Operador relacional
                break;
            case 6:
                clasificado = 4;        //Operador aritmético
                break;
            case 17:
            case 24:
            case 28:
            case 35:
            case 37:
            case 39:
            case 43:
            case 54:
                clasificado = 1;        //Palabra reservada
                break;
            case 7:
            case 9:
                clasificado = 9;        //Constante
                break;
            case 14:
                clasificado = 8;        //Cadena
                break;
            case 10:
            case 11:
            case 12:
                clasificado = 2;        //Identificador
                break;
            default:
                clasificado = 0;        //Error
                break;
        }
        return clasificado;
    }   
    
    /**
     * Method to recover the errors found during the analysis 
     * @return String - All the errors of the analysis
     */
    public String getListaErrores(){
        listaErrores = "";
        manejoErrores.getErrores().forEach((error) -> {
            lineaError = error.split(",");
            switch (lineaError[0]){
                case "100":
                    listaErrores += "Error " + lineaError[0] + ": Caracter desconocido - \"" + lineaError[2] + "\" no pertenece a la definición del lenguaje (Línea " + lineaError[3] + ")\n";
                    break;
                case "101":
                    listaErrores += "Error " + lineaError[0] + ": Construcción incorrecra de identificador \"" + lineaError[1] + "\" (Línea " + lineaError[3] + ")\n";
                    break;
                case "102":
                    listaErrores += "Error " + lineaError[0] + ": Construcción incorrecra de cadena \"" + lineaError[1] + "\" (Línea " + lineaError[3] + ")\n";        
                    break;
                case "103":
                    listaErrores += "Error " + lineaError[0] + ": Construcción incorrecra de constante \"" + lineaError[1] + "\" (Línea " + lineaError[3] + ")\n";
                    break;
                case "104":
                    listaErrores += "Error " + lineaError[0] + ": Palabra reservada no definida - Elemento \"" + lineaError[1] + "\" no reconocido (Línea " + lineaError[3] + ")\n";
                    break;
                case "105":
                    listaErrores += "Error " + lineaError[0] + ": Uso ilegal de caracter - Elemento \"" + lineaError[2] + "\" no reconocido (Línea " + lineaError[3] + ")\n";
                    break;
            }
        });
        return listaErrores;
    }    
}
