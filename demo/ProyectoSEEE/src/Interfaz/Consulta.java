/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import CLIPSJNI.*;
import com.sacooliveros.gepsac.service.experto.se.EngineFactory;
import com.sacooliveros.gepsac.service.experto.se.Pregunta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Unknowing
 */
public class Consulta extends javax.swing.JFrame {
    private String conclusion;
    private List<Pregunta> preguntas;
    private Environment clips;
    /**
     * Creates new form Ventana
     */

    private static Consulta instancia;

    public static Consulta getInstance() {
        if(instancia == null){
            instancia = new Consulta();
        }
        return instancia;
    }

    private Consulta() {
        this.setResizable(false);
        preguntas = new ArrayList<Pregunta>();
        System.out.println("Cargando Enviroment ...");
        
        clips = new Environment();
        System.out.println("Cargo Enviroment");
        clips.load("SEEE.CLP");
        System.out.println("Cargo SEEE.clp");
        initComponents();
        init();
        /*
        clips.run();
        try {
            String evaluar = "(find-all-facts ((?x pregunta-respuesta )) TRUE)";
            PrimitiveValue value = clips.eval(evaluar);
            String nombre = value.get(0).getFactSlot("nombre").toString();
            String texto = value.get(0).getFactSlot("texto").toString();
            System.out.println(texto);
            Pregunta pregunta = new Pregunta();
            pregunta.setAlias(nombre);
            pregunta.setEnunciado(texto);
            preguntas.add(pregunta);

            jLabel1.setText(pregunta.getEnunciado());
            jLabel3.setText("");
        } catch (Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    private void init(){        
        clips.run();
        try {
            String evaluar = "(find-all-facts ((?x pregunta-respuesta )) TRUE)";
            PrimitiveValue value = clips.eval(evaluar);
            String nombre = value.get(0).getFactSlot("nombre").toString();
            String texto = value.get(0).getFactSlot("texto").toString();
            System.out.println(texto);
            Pregunta pregunta = new Pregunta();
            pregunta.setAlias(nombre);
            pregunta.setEnunciado(texto);
            preguntas.add(pregunta);

            jLabel1.setText(pregunta.getEnunciado());
            jLabel3.setText("");
        } catch (Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clear(){
        conclusion=null;
        preguntas.clear();
        clips.reset();
    }
    
    private void reset(){
        clear();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        btnContinuar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("jLabel1");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("SI");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("NO");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        btnContinuar.setFont(new java.awt.Font("Vani", 0, 11)); // NOI18N
        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/continuarFicha.png"))); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.setPreferredSize(new java.awt.Dimension(137, 55));
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jLabel2.setText("Sistema Experto: Deteccion Enfermedades Exantematicas");

        jLabel3.setText("jLabel3");

        btnSalir.setFont(new java.awt.Font("Vani", 0, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/exitFicha.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(137, 55));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jRadioButton1)
                        .addGap(103, 103, 103)
                        .addComponent(jRadioButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        try {
            Pregunta pregunta = preguntas.get(preguntas.size() - 1);
            if (jRadioButton1.isSelected() == true) {
                clips.eval("(assert (opcion si))");
                System.out.println("si");
                pregunta.setRespuesta("si");
            }

            if (jRadioButton2.isSelected() == true) {
                clips.eval("(assert (opcion no))");
                System.out.println("no");
                pregunta.setRespuesta("no");
            }

            clips.run();

            String evaluar = "(find-all-facts ((?x pregunta-respuesta )) TRUE)";
            PrimitiveValue value = clips.eval(evaluar);
            String alias = value.get(0).getFactSlot("nombre").toString();
            System.out.println("nombre:" + alias);
            String tipo = value.get(0).getFactSlot("tipo").toString();
            System.out.println(tipo);
            String texto = value.get(0).getFactSlot("texto").toString();
            System.out.println(texto);

            if (tipo.equals("decision")) {
                Pregunta sgtePregunta = new Pregunta();
                sgtePregunta.setAlias(alias);
                sgtePregunta.setEnunciado(texto);
                preguntas.add(sgtePregunta);

                jLabel1.setText(sgtePregunta.getEnunciado());
                jLabel3.setText("");

            } else {
                if (tipo.equals("respuesta")) {
                    conclusion = texto;
                    Datos.setDiagnostico(texto);
                    String nombre, ap, am, edad, sexo, dni, diagnostico;

                    nombre = Datos.getNombre();
                    ap = Datos.getAp();
                    am = Datos.getAm();
                    dni = Datos.getDni();
                    edad = String.valueOf(Datos.getEdad());
                    sexo = Datos.getSexo();
                    diagnostico = texto;

                    System.out.println("  LISTO");

                    Reporte r = new Reporte();
                    r.set(nombre, ap, am, dni, sexo, edad, diagnostico);
                    r.setVisible(true);
                    
                    String resultado = EngineFactory.evaluar(preguntas);
                    
                    System.out.println(">>conclusion="+conclusion+",resultado Enginje="+resultado);
                    System.out.println(">>RESULTADO="+(conclusion.equals(resultado)));
                    
                    this.dispose();

                    //clips.eval("(assert (opcion no))");
                    
                    reset();
                    
                } else {
                    this.dispose();
                }

            }
            buttonGroup1.clearSelection();
        } catch (Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
