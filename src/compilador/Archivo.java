/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jorge
 */
public class Archivo {
    /**
     * Local variables
     */
    Analizador analizador;
    JFileChooser fChooser;
    BufferedReader br;   
    BufferedWriter bw;
    FileWriter fw;
    FileNameExtensionFilter filtro;
    File file;
    String cadena, linea, nombreArchivo;
    int accion;
    boolean guardar, crear;
    
    /**
     * Method to set "null" the File type variable that stores the path of the file with which it's working 
     */
    public void nuevoArchivo(){
        file = null;
    }
    
    /**
     * Method to open a .txt file selected. The program store the name of the file open
     * @return String - Text of the file open
     */
    public String abrirArchivo(){
        file = null;
        cadena = "";
        fChooser = new JFileChooser(); 
        fChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES ); 
        if (fChooser.showSaveDialog(analizador) == JFileChooser.APPROVE_OPTION) {
            file = fChooser.getSelectedFile(); 
            try{
                br = new BufferedReader(new FileReader(file));
                linea = (String)br.readLine();
                while(linea != null){
                    cadena += linea + "\n";
                    linea = (String)br.readLine();
                }
                br.close();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "No se pudo leer el archivo" + ex.getMessage());
            }
        }
        return cadena;
    }
    
    /**
     * Method to save text in a .txt file. If the program is working with a document, is overwritten, otherwise a new file is created 
     * @param codigo - The text to save in the file
     * @return boolean - Indicates whether the file was saved
     */
    public boolean guardarArchivo(String codigo) {
        if(file == null){
            guardar = crearArchivo(codigo);
        }else{
            try {
                accion = JOptionPane.showOptionDialog(null, "¿Realmente desea modificar el archivo " + file.getName() + "? \n", 
                        "Archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Si", "No"}, "No");
                if(accion == 0){
                    file.delete();
                    bw = new BufferedWriter(new FileWriter(file));
                    String [] lineas = codigo.split("\n");
                    for (String linea1 : lineas) {
                        bw.write(linea1);
                        bw.newLine();
                    }
                    bw.close();
                    guardar = true;
                }else{
                    guardar = false;
                }    
            } catch (IOException e) {
            }
        }
        return guardar;
    }
    
    /**
     * Method to create a new .txt file. If the name of the file already exist, is possible to replace the document
     * @param codigo - The text to save in the created file
     * @return bollean - Indicates whether the file was created
     */
    public boolean crearArchivo(String codigo){
        fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(0);
        filtro = new FileNameExtensionFilter("txt", "TXT");
        fChooser.setFileFilter(filtro);
        fChooser.setDialogTitle("Guardar archivo");
        fChooser.setSelectedFile(new File(""));
        if (fChooser.showSaveDialog(analizador) == JFileChooser.APPROVE_OPTION) {
            nombreArchivo = fChooser.getSelectedFile().toString();
            file = new File(nombreArchivo + ".txt");
            try {
                if (file.exists()) {
                    accion=JOptionPane.showOptionDialog(null, "Ya existe un archivo llamado " + file.getName() + "\n\n ¿Desea reemplazar el archivo?", 
                            "Archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Si", "No"}, "No");
                    if(accion == 0){
                        file.delete();
                        bw = new BufferedWriter(new FileWriter(file));
                        String [] lineas = codigo.split("\n");
                        for (String linea1 : lineas) {
                            bw.write(linea1);
                            bw.newLine();
                        }                        
                        bw.close();
                        crear = true;
                    }else{
                        file = null;
                        crear = false;
                    }
                }else{
                    bw = new BufferedWriter(new FileWriter(file));
                    String [] lineas = codigo.split("\n");
                    for (String linea1 : lineas) {
                        bw.write(linea1);
                        bw.newLine();
                    }
                    bw.close();
                    crear = true;
                }
            } catch (HeadlessException | IOException ex) {
            }                         
        }else{
            crear = false;
        }
        return crear;
    }
}
