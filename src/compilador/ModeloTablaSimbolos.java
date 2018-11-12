/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jorge
 */
public class ModeloTablaSimbolos extends AbstractTableModel{
    
    ArrayList<Fila> filas;
    ArrayList<String> columnasTabla;
    
    public ModeloTablaSimbolos(ArrayList<Fila> filas){
        columnasTabla = new ArrayList<>();
        columnasTabla.add("ID");
        columnasTabla.add("Lexema");
        columnasTabla.add("Token");
        columnasTabla.add("Tipo de dato");
        columnasTabla.add("Valor");
        columnasTabla.add("Línea");
        this.filas=filas;
    }
    
    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return columnasTabla.size();
    }

    @Override
    public String getColumnName(int column){
        return columnasTabla.get(column);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Fila fila=filas.get(rowIndex);
        switch(columnIndex){
            case 0:
            return fila.getIdToken();

            case 1:
            return fila.getLexema();

            case 2:
            return fila.getToken();
            
            case 3:
            return "";
            
            case 4:
            return "";
            
            case 5:
            return fila.getLinea();
        }
        return fila;
    }

    public ArrayList<Fila> getFilas() {
        return filas;
    }
}
