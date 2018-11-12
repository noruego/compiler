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
public class Alfabeto {
    /**
     * Local variables
     */
    ArrayList<String> minusculas = new ArrayList<>();
    ArrayList<String> mayusculas = new ArrayList<>();
    ArrayList<String> numeros = new ArrayList<>();
    ArrayList<String> delimitadores = new ArrayList<>();
    ArrayList<String> operadores = new ArrayList<>();
    ArrayList<String> especiales = new ArrayList<>();
    
    /**
     * Construtor - It defines the arrays and set all the elements of each one
     */
    public Alfabeto(){
        minusculas.add('a' + "");
        minusculas.add('b' + "");
        minusculas.add('c' + "");
        minusculas.add('d' + "");
        minusculas.add('e' + "");
        minusculas.add('f' + "");
        minusculas.add('g' + "");
        minusculas.add('h' + "");
        minusculas.add('i' + "");
        minusculas.add('j' + "");
        minusculas.add('k' + "");
        minusculas.add('l' + "");
        minusculas.add('m' + "");
        minusculas.add('n' + "");
        minusculas.add('o' + "");
        minusculas.add('p' + "");
        minusculas.add('q' + "");
        minusculas.add('r' + "");
        minusculas.add('s' + "");
        minusculas.add('t' + "");
        minusculas.add('u' + "");
        minusculas.add('v' + "");
        minusculas.add('w' + "");
        minusculas.add('x' + "");
        minusculas.add('y' + "");
        minusculas.add('z' + "");
        mayusculas.add('A' + "");
        mayusculas.add('B' + "");
        mayusculas.add('C' + "");
        mayusculas.add('D' + "");
        mayusculas.add('E' + "");
        mayusculas.add('F' + "");
        mayusculas.add('G' + "");
        mayusculas.add('H' + "");
        mayusculas.add('I' + "");
        mayusculas.add('J' + "");
        mayusculas.add('K' + "");
        mayusculas.add('L' + "");
        mayusculas.add('M' + "");
        mayusculas.add('N' + "");
        mayusculas.add('O' + "");
        mayusculas.add('P' + "");
        mayusculas.add('Q' + "");
        mayusculas.add('R' + "");
        mayusculas.add('S' + "");
        mayusculas.add('T' + "");
        mayusculas.add('U' + "");
        mayusculas.add('V' + "");
        mayusculas.add('W' + "");
        mayusculas.add('X' + "");
        mayusculas.add('Y' + "");
        mayusculas.add('Z' + "");
        numeros.add('0' + "");
        numeros.add('1' + "");
        numeros.add('2' + "");
        numeros.add('3' + "");
        numeros.add('4' + "");
        numeros.add('5' + "");
        numeros.add('6' + "");
        numeros.add('7' + "");
        numeros.add('8' + "");
        numeros.add('9' + "");
        delimitadores.add('{' + "");
        delimitadores.add('}' + "");
        delimitadores.add('(' + "");
        delimitadores.add(')' + "");
        delimitadores.add(';' + "");
        delimitadores.add(',' + "");
        delimitadores.add(':' + "");
        operadores.add('+' + "");
        operadores.add('-' + "");
        operadores.add('*' + "");
        operadores.add('/' + "");
        operadores.add('=' + "");
        operadores.add('<' + "");
        operadores.add('>' + "");
        operadores.add('!' + "");
        especiales.add(' ' + "");
        especiales.add('.' + "");
        especiales.add('\'' + "");
        especiales.add('_' + "");
        especiales.add('¡' + "");
        especiales.add('!' + "");
        especiales.add('¿' + "");
        especiales.add('?' + "");
        especiales.add('#' + "");
        especiales.add('$' + "");
        especiales.add('%' + "");
        especiales.add('&' + "");
        especiales.add('@' + "");
    }
}
