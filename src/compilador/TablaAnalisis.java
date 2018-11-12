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
public class TablaAnalisis {
    HashMap<String, ArrayList<Entrada>> tablaAnalisis;
    ArrayList<Entrada> reglas;
    ArrayList<String> noTerminales;
    String regla;
    String terminal;
    boolean error;
    
    public TablaAnalisis() {
        tablaAnalisis = new HashMap<>();
        noTerminales = new ArrayList<>();
    }

    public void generarTabla(){
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Execute", "Execute Parentesis"));
        tablaAnalisis.put("Main", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("(", "( ) Bloque"));
        tablaAnalisis.put("Parentesis", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("{", "{ Instrucciones }"));
        tablaAnalisis.put("Bloque", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Write", "Instruccion Instrucciones"));
        reglas.add(new Entrada("String", "Instruccion Instrucciones"));
        reglas.add(new Entrada("Numeric", "Instruccion Instrucciones"));
        reglas.add(new Entrada("Boolean", "Instruccion Instrucciones"));
        reglas.add(new Entrada("If", "Instruccion Instrucciones"));
        reglas.add(new Entrada("For", "Instruccion Instrucciones"));
        reglas.add(new Entrada("While", "Instruccion Instrucciones"));
        reglas.add(new Entrada("Id", "Instruccion Instrucciones"));
        reglas.add(new Entrada("}", "λ"));
        tablaAnalisis.put("Instrucciones", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Write", "Escritura ;"));
        reglas.add(new Entrada("String", "Declaracion ;"));
        reglas.add(new Entrada("Numeric", "Declaracion ;"));
        reglas.add(new Entrada("Boolean", "Declaracion ;"));
        reglas.add(new Entrada("If", "Control"));
        reglas.add(new Entrada("For", "Control"));
        reglas.add(new Entrada("While", "Control"));
        reglas.add(new Entrada("Id", "Id Asignacion ;"));
        tablaAnalisis.put("Instruccion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("String", "Tipo Id Instancia"));
        reglas.add(new Entrada("Numeric", "Tipo Id Instancia"));
        reglas.add(new Entrada("Boolean", "Tipo Id Instancia"));
        tablaAnalisis.put("Declaracion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("=", "Asignacion"));
        reglas.add(new Entrada(";", "λ"));
        tablaAnalisis.put("Instancia", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("=", "= Valor"));
        tablaAnalisis.put("Asignacion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Write", "Write Parametros"));
        tablaAnalisis.put("Escritura", reglas);

        reglas = new ArrayList<>();
        reglas.add(new Entrada("Read", "Read Parametros"));
        tablaAnalisis.put("Lectura", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("If", "Si"));
        reglas.add(new Entrada("For", "Para"));
        reglas.add(new Entrada("While", "Mientras"));
        tablaAnalisis.put("Control", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("String", "String"));
        reglas.add(new Entrada("Numeric", "Numeric"));
        reglas.add(new Entrada("Boolean", "Boolean"));
        tablaAnalisis.put("Tipo", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("True", "True"));
        reglas.add(new Entrada("False", "False"));
        tablaAnalisis.put("Booleano", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("<", "<"));
        reglas.add(new Entrada(">", ">"));
        reglas.add(new Entrada("<=", "<="));
        reglas.add(new Entrada(">=", ">="));
        reglas.add(new Entrada("==", "=="));
        reglas.add(new Entrada("!=", "!="));
        tablaAnalisis.put("Relacional", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("+", "+"));
        reglas.add(new Entrada("-", "-"));
        reglas.add(new Entrada("*", "*"));
        reglas.add(new Entrada("/", "/"));
        tablaAnalisis.put("Aritmetico", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("And", "And"));
        reglas.add(new Entrada("Or", "Or"));
        tablaAnalisis.put("Logico", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Read", "Lectura"));
        reglas.add(new Entrada("True", "Termino"));
        reglas.add(new Entrada("False", "Termino"));
        reglas.add(new Entrada("Null", "Termino"));
        reglas.add(new Entrada("Id", "Termino"));
        reglas.add(new Entrada("Numero", "Termino"));
        reglas.add(new Entrada("Cadena", "Termino"));
        reglas.add(new Entrada("(", "Termino"));
        tablaAnalisis.put("Valor", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("True", "Booleano"));
        reglas.add(new Entrada("False", "Booleano"));
        reglas.add(new Entrada("Null", "Null"));
        reglas.add(new Entrada("Id", "Operacion"));
        reglas.add(new Entrada("Numero", "Operacion"));
        reglas.add(new Entrada("Cadena", "Cadena"));
        reglas.add(new Entrada("(", "Operacion"));
        tablaAnalisis.put("Termino", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Id", "Id"));
        reglas.add(new Entrada("Numero", "Numero"));
        tablaAnalisis.put("Operando", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("(", "( Contenido )"));
        tablaAnalisis.put("Parametros", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("True", "Termino Concat"));
        reglas.add(new Entrada("False", "Termino Concat"));
        reglas.add(new Entrada("Null", "Termino Concat"));
        reglas.add(new Entrada("Id", "Termino Concat"));
        reglas.add(new Entrada("Numero", "Termino Concat"));
        reglas.add(new Entrada("Cadena", "Termino Concat"));
        reglas.add(new Entrada("(", "Termino Concat"));
        tablaAnalisis.put("Contenido", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada(",", ", Termino Concat"));
        reglas.add(new Entrada(")", "λ"));
        tablaAnalisis.put("Concat", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Id", "Inicio Opera"));
        reglas.add(new Entrada("Numero", "Inicio Opera"));
        reglas.add(new Entrada("(", "Inicio Opera"));
        tablaAnalisis.put("Operacion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Id", "Operando"));
        reglas.add(new Entrada("Numero", "Operando"));
        reglas.add(new Entrada("(", "( Operacion )"));
        tablaAnalisis.put("Inicio", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("And", "λ"));
        reglas.add(new Entrada("Or", "λ"));
        reglas.add(new Entrada(")", "λ"));
        reglas.add(new Entrada(";", "λ"));
        reglas.add(new Entrada(",", "λ"));
        reglas.add(new Entrada(":", "λ"));
        reglas.add(new Entrada("+", "Aritmetico Inicio Opera"));
        reglas.add(new Entrada("-", "Aritmetico Inicio Opera"));
        reglas.add(new Entrada("*", "Aritmetico Inicio Opera"));
        reglas.add(new Entrada("/", "Aritmetico Inicio Opera"));
        reglas.add(new Entrada("<", "λ"));
        reglas.add(new Entrada(">", "λ"));
        reglas.add(new Entrada("<=", "λ"));
        reglas.add(new Entrada(">=", "λ"));
        reglas.add(new Entrada("==", "λ"));
        reglas.add(new Entrada("!=", "λ"));
        tablaAnalisis.put("Opera", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("True", "Exp ExpLog"));
        reglas.add(new Entrada("False", "Exp ExpLog"));
        reglas.add(new Entrada("Null", "Exp ExpLog"));
        reglas.add(new Entrada("Id", "Exp ExpLog"));
        reglas.add(new Entrada("Numero", "Exp ExpLog"));
        reglas.add(new Entrada("Cadena", "Exp ExpLog"));
        reglas.add(new Entrada("(", "Exp ExpLog"));
        tablaAnalisis.put("Expresion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("True", "Termino ExpRel"));
        reglas.add(new Entrada("False", "Termino ExpRel"));
        reglas.add(new Entrada("Null", "Termino ExpRel"));
        reglas.add(new Entrada("Id", "Termino ExpRel"));
        reglas.add(new Entrada("Numero", "Termino ExpRel"));
        reglas.add(new Entrada("Cadena", "Termino ExpRel"));
        reglas.add(new Entrada("(", "Termino ExpRel"));
        tablaAnalisis.put("Exp", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("<", "Relacional Termino"));
        reglas.add(new Entrada(">", "Relacional Termino"));
        reglas.add(new Entrada("<=", "Relacional Termino"));
        reglas.add(new Entrada(">=", "Relacional Termino"));
        reglas.add(new Entrada("==", "Relacional Termino"));
        reglas.add(new Entrada("!=", "Relacional Termino"));
        tablaAnalisis.put("ExpRel", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("And", "Logico Exp ExpLog"));
        reglas.add(new Entrada("Or", "Logico Exp ExpLog"));
        reglas.add(new Entrada(")", "λ"));
        reglas.add(new Entrada(":", "λ"));
        tablaAnalisis.put("ExpLog", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("If", "If Cuerpo BloqueSi"));
        tablaAnalisis.put("Si", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("(", "( Expresion )"));
        tablaAnalisis.put("Cuerpo", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("{", "Bloque Sino"));
        tablaAnalisis.put("BloqueSi", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Write", "λ"));
        reglas.add(new Entrada("String", "λ"));
        reglas.add(new Entrada("Numeric", "λ"));
        reglas.add(new Entrada("Boolean", "λ"));
        reglas.add(new Entrada("If", "λ"));
        reglas.add(new Entrada("Else", "Else Bloque"));
        reglas.add(new Entrada("For", "λ"));
        reglas.add(new Entrada("While", "λ"));
        reglas.add(new Entrada("Id", "λ"));
        reglas.add(new Entrada("}", "λ"));
        tablaAnalisis.put("Sino", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("For", "For CuerpoPara Bloque"));
        tablaAnalisis.put("Para", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("(", "( Estructura )"));
        tablaAnalisis.put("CuerpoPara", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("String", "Definicion Condicion Incremento"));
        reglas.add(new Entrada("Numeric", "Definicion Condicion Incremento"));
        reglas.add(new Entrada("Boolean", "Definicion Condicion Incremento"));
        reglas.add(new Entrada("Id", "Definicion Condicion Incremento"));
        tablaAnalisis.put("Estructura", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("String", "Tipo Id Asignacion"));
        reglas.add(new Entrada("Numeric", "Tipo Id Asignacion"));
        reglas.add(new Entrada("Boolean", "Tipo Id Asignacion"));
        reglas.add(new Entrada("Id", "Id Asignacion"));
        tablaAnalisis.put("Definicion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada(":", ": Expresion :"));
        tablaAnalisis.put("Condicion", reglas);
        
        reglas = new ArrayList<>();
        reglas.add(new Entrada("Inc", "Inc Id"));
        tablaAnalisis.put("Incremento", reglas);
        
        reglas = new ArrayList<>(); 
        reglas.add(new Entrada("While", "While Cuerpo Bloque"));
        tablaAnalisis.put("Mientras", reglas);        
    }
    
    public String getRegla(String terminal, String noTerminal){
        error = true;
        regla = "";
        for (String llave : tablaAnalisis.keySet()) {
            if(llave.equals(noTerminal)){
                for (Entrada entrada : tablaAnalisis.get(llave)) {
                    if(entrada.getTerminal().equals(terminal)){
                        regla = entrada.getRegla();
                        this.terminal = terminal;
                        error = false;
                    }
                }
            }
        }
        return regla;
    }
    
    public boolean getError(){
        return error;
    }

    public String getTerminal() {
        return terminal;
    }
}
