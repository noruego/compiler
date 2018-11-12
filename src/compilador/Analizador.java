/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jorge
 */
public class Analizador extends JFrame implements ActionListener, KeyListener{
    /**
     * Local variables
     */
    
    Archivo archivo;
    Preproceso preproceso;
    AnalizadorLexico aLexico;
    AnalizadorSintactico aSintactico;
    ModeloTablaSimbolos tablaSimbolos;
    JPanel pPrincipal, pCodigo, pSalida, pAuxiliar, pMostrar, pBoton;
    JTable tSimbolos;
    JMenuBar menu;
    JMenu mArchivo, mAnalisis, mAyuda, mDocumentacion;
    JMenuItem iNuevo, iAbrir, iGuardar, iSalir, iLexico, iSintactico, iSemantico, iAnalizar, iAcercaDe, iManual, iDocLexico, iDocSintactico, iDocSemantico; 
    JScrollPane spCodigo, spSalida, spMostrar;
    JTextArea taCodigo, taLinea, taSalida;
    JButton bCerrar;
    ArrayList<String> lexemas;
    ArrayList<Integer> numLinea;
    String auxiliarCodigo, auxiliarSalida;
    int numeroLinea = 1, accion;
    boolean cambios = false;
    
    /**
     * Constructor - It defines the properties of the frame at the moment it starts and the instances of the other classes are created
     */
    public Analizador(){
        archivo = new Archivo();
        preproceso = new Preproceso();
        aLexico = new AnalizadorLexico();
        aSintactico = new AnalizadorSintactico();
        menu();
        panelPrincipal();
        panelAuxiliar();
        setJMenuBar(menu);
        setLayout(new BorderLayout());
        add(pPrincipal, BorderLayout.CENTER);
        Image icon = new ImageIcon("Icono.png").getImage();
        setIconImage(icon);
        setTitle("Analizador");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Method to create the menu components  
     */
    public void menu(){
        menu = new JMenuBar();
        mArchivo = new JMenu("Archivo");
        mAnalisis = new JMenu("Análisis");
        mAyuda = new JMenu("Ayuda");
        mDocumentacion = new JMenu("Documentación");
        
        iNuevo = new JMenuItem("Nuevo", new ImageIcon("Nuevo.png"));
        iNuevo.addActionListener(this);
        iAbrir = new JMenuItem("Abrir", new ImageIcon("Abrir.png"));
        iAbrir.addActionListener(this);
        iGuardar = new JMenuItem("Guardar", new ImageIcon("Guardar.png"));
        iGuardar.addActionListener(this);
        iSalir = new JMenuItem("Salir");
        iSalir.addActionListener(this);
        iLexico = new JMenuItem("Léxico");
        iLexico.addActionListener(this);
        iSintactico = new JMenuItem("Sintáctico");
        iSintactico.setEnabled(false);
        iSintactico.addActionListener(this);
        iSemantico = new JMenuItem("Semántico");
        iSemantico.setEnabled(false);
        iSemantico.addActionListener(this);
        iAnalizar = new JMenuItem("Analizar");
        iAnalizar.addActionListener(this);
        iAcercaDe = new JMenuItem("Acerca de");       
        iManual = new JMenuItem("Manual de uso");
        iDocLexico = new JMenuItem("Análisis léxico");
        iDocSintactico = new JMenuItem("Análisis sintáctico");
        iDocSintactico.setEnabled(false);
        iDocSemantico = new JMenuItem("Análisis semántico");
        iDocSemantico.setEnabled(false);
        
        mArchivo.add(iNuevo);
        mArchivo.add(iAbrir);
        mArchivo.add(iGuardar);
        mArchivo.add(iSalir);
        mAnalisis.add(iLexico);
        mAnalisis.add(iSintactico);
        mAnalisis.add(iSemantico);
        mAnalisis.add(iAnalizar);
        mAyuda.add(iAcercaDe);
        mAyuda.add(iManual);
        mAyuda.add(mDocumentacion);
        mDocumentacion.add(iDocLexico);
        mDocumentacion.add(iDocSintactico);
        mDocumentacion.add(iDocSemantico);
        
        menu.add(mArchivo);
        menu.add(mAnalisis);
        menu.add(mAyuda);
    }
    
    /**
     * Method to create the components of the main panel. These are the code panel and the output panel
     */
    public void panelPrincipal(){
        pPrincipal = new JPanel();
        pCodigo = new JPanel();
        pSalida = new JPanel();
        
        taLinea = new JTextArea(30, 3);
        taLinea.append(numeroLinea + "");
        taLinea.setEditable(false);
        taCodigo = new JTextArea(30, 70);
        taCodigo.addKeyListener(this);
        taSalida = new JTextArea(7, 74);
        taSalida.setEditable(false);
        
        spCodigo = new JScrollPane(pCodigo);
        spSalida = new JScrollPane(taSalida);
        
        pCodigo.add(taLinea);
        pCodigo.add(taCodigo);
        
        pSalida.setLayout(new BorderLayout());
        pSalida.add(new JLabel("  Salida: "), BorderLayout.CENTER); 
        pSalida.add(spSalida, BorderLayout.SOUTH); 
        
        pPrincipal.setLayout(new BorderLayout());
        
        pPrincipal.add(spCodigo, BorderLayout.CENTER);
        pPrincipal.add(pSalida, BorderLayout.SOUTH);
    }
    
    /**
     * Method to create the components of the auxiliar panel. These are the symbols table
     */
    public void panelAuxiliar(){
        pMostrar=new JPanel();
        pBoton=new JPanel();
        pAuxiliar=new JPanel();
        
        bCerrar=new JButton("Cerrar");
        bCerrar.addActionListener(this);
        
        spMostrar=new JScrollPane();
        
        pAuxiliar.setLayout(new BorderLayout());
        
        pMostrar.add(spMostrar);
        pBoton.add(bCerrar);
        pAuxiliar.add(pMostrar, BorderLayout.CENTER);
        pAuxiliar.add(pBoton, BorderLayout.SOUTH);
    }
    
    /**
     * 
     */
    public void abrirPanelAuxiliar(){
        
        auxiliarCodigo = taCodigo.getText();
        taCodigo.setText("");
        taLinea.setText("");
        taSalida.setText("");
        spMostrar.setPreferredSize(new Dimension(435, 485));
        remove(pAuxiliar);
        add(pAuxiliar, BorderLayout.EAST);
        validate();
        repaint();
        pack();
        setLocationRelativeTo(null);
        tablaSimbolos=new ModeloTablaSimbolos(aLexico.tablaSimbolos.tabla);
        tSimbolos=new JTable(tablaSimbolos);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tSimbolos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tSimbolos.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tSimbolos.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tSimbolos.getColumnModel().getColumn(2).setCellRenderer(renderer);
        tSimbolos.getColumnModel().getColumn(5).setCellRenderer(renderer);
        spMostrar.setViewportView(tSimbolos);            
        taCodigo.setText(auxiliarCodigo);
        contarFilas();
    }
    
    /**
     * 
     */
    public void cerrarPanelAuxiliar(){
        auxiliarCodigo = taCodigo.getText();
        auxiliarSalida = taSalida.getText();
        taCodigo.setText("");
        taLinea.setText("");
        taSalida.setText("");
        remove(pAuxiliar);
        repaint();
        pack();
        setLocationRelativeTo(null);
        taCodigo.setText(auxiliarCodigo);
        contarFilas();
        taSalida.setText(auxiliarSalida);    
    }
    
    /**
     * Method to count the number of lines when an type KeyListener event occurs and that is displayed in the code panel
     */
    public void contarFilas(){
        int filas=taCodigo.getLineCount();
        taLinea.setText("1");
        for(int i = 2; i <= filas; i++){
            taLinea.setText(taLinea.getText() + "\n" + i);
        }
    }
    
    /**
     * Method to clean the code panel and set a new file
     */     
    public void nuevoArchivo(){
        taCodigo.setText("");
        archivo.nuevoArchivo();       
        contarFilas();
    }
    
    /**
     * Method to clean the code panel and open a new file. The content of the file is displayed in the code panel
     */
    public void abrirArchivo(){
        taCodigo.setText("");
        taCodigo.setText(archivo.abrirArchivo());
        contarFilas();
    }
    
    /**
     * Method to save the content of the code panel in a file
     * @return boolean - Indicates whether the file was saved 
     */
    public  boolean guardarArchivo(){
        return archivo.guardarArchivo(taCodigo.getText());
    }
    
    /**
     * Method to open a file from the computer from a given path
     * @param archivo - Path of the file
     */
    public void abrirDocumentacion(String archivo){
        try {
            File ruta = new File (archivo);
            Desktop.getDesktop().open(ruta);
        }catch (IOException ex) {
        }
    }
    
    public static void main(String [] args) throws Exception{ 
        UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        Analizador analizador = new Analizador();
        analizador.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        /**
         * The option "Nuevo" is selected
         */
        if(command.equals("Nuevo")){
            if(cambios == true){
                accion=JOptionPane.showOptionDialog(null,"\n        El archivo ha sido modificado \n ¿Desea guardar el archivo antes de salir? \n\n", 
                        "Archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Si", "No"}, "No");
                if(accion == 0){
                    if(guardarArchivo()){
                        nuevoArchivo();
                        cambios = false;
                    }
                }else{
                    nuevoArchivo();
                    cambios = false;
                }                
            }else{
                nuevoArchivo();
                cambios = false;
            }            
        }
        /**
         * The option "Guardar" is selectef
         */
        if(command.equals("Guardar")){
            if(cambios == true){
                cambios = !guardarArchivo();
            }            
        }
        /**
         * The option "Abrir" is selected
         */
        if(command.equals("Abrir")){
            if(cambios == true){
                accion = JOptionPane.showOptionDialog(null,"\n        El archivo ha sido modificado \n ¿Desea guardar el archivo antes de salir? \n\n", 
                        "Archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Si", "No"}, "No");
                if(accion == 0){
                    if(guardarArchivo()){
                        abrirArchivo();
                        cambios = false;
                    }
                }else{
                    abrirArchivo();
                    cambios = false;
                }                
            }else{
                abrirArchivo();
                cambios = false;
            }
        }
        /**
         * The option "Salir" is selected
         */
        if(command.equals("Salir")){
            if(cambios == true){
                accion = JOptionPane.showOptionDialog(null,"\n      Hay cambios que no se guardaron \n ¿Desea guardar el archivo antes de salir? \n\n", 
                        "Archivo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"Si", "No"}, "No");
                if(accion == 0){
                    cambios = !guardarArchivo();
                }else{
                    System.exit(0);
                }                
            }else{
                System.exit(0);
            }            
        }
        if(command.equals("Léxico")){
            preproceso.setInstrucciones(taCodigo.getText());
            preproceso.setLexemas();
            aLexico.clasificarLexemas(preproceso.getLexemas(), preproceso.getNumLinea());
            abrirPanelAuxiliar();
            if(aLexico.getListaErrores().equals("")){
                taSalida.setText("Análisis léxico correcto");
                iSintactico.setEnabled(true);
            } else
                taSalida.setText(aLexico.getListaErrores());
        }
        if(command.equals("Sintáctico")){
            aSintactico.analizarEstructuras(aLexico.tablaSimbolos.getTabla());
            if(aSintactico.getError().equals("")){
                taSalida.setText("Análisis sintáctico correcto");
                //iSemantico.setEnabled(true);
            } else
                taSalida.setText(aSintactico.getError());
        }
        if(command.equals("Semántico")){
                    
        }
        if(command.equals("Acerca de")){
            abrirDocumentacion("AcercaDe.pdf");        
        }
        if(command.equals("Manual de uso")){
            abrirDocumentacion("ManualDeUso.pdf");
        }
        if(command.equals("Análisis léxico")){
            abrirDocumentacion("DocumentaciónLéxico.pdf");
        }
        if(command.equals("Análisis sintáctico")){
            abrirDocumentacion("DocumentaciónSintáctico.pdf");
        }
        if(command.equals("Análisis semántico")){
            abrirDocumentacion("DocumentaciónSemántico.pdf");
        }
        if(command.equals("Cerrar")){
            cerrarPanelAuxiliar();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        cambios = true;
        taSalida.setText("");
        iSintactico.setEnabled(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.isControlDown() || e.getKeyCode()==10 || e.getKeyCode()==8 || e.getKeyCode()==127){
            contarFilas();            
        }
    }   
}
