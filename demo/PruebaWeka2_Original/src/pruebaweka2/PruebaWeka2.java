/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 *
 * @author DENNYZ
 */
public class PruebaWeka2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "D:\\UPC\\PI2\\Sistema_Experto\\Weka Ejemplo\\Modelos_BD2\\";
        Util c = new Util();
        Clasificador clasificador = new Clasificador();
        
        try {
            System.out.println("****************************");
            System.out.println("Obtener data mediante ruta");
            Instances train = c.obtenerData(path+"Data Entrenamiento-bkp.csv");
            Instances valid = c.obtenerData(path+"Data Validacion.csv");
            Instances prede = c.obtenerData(path+"Data predecir.csv");
            System.out.println("****************************\n");

            System.out.println("****************************");
            System.out.println("Entrenamiento con metodo generico");

            String mpOptions = "-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a";
            String smoOptions = "-C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 1.0\"";
            String rfOptions = "-I 100 -K 0 -S 1";
            Classifier rfG = new RandomForest(); 
            Classifier smoG = new SMO(); 
            Classifier mpG = new MultilayerPerceptron(); 

            System.out.println("\tRandom Forest");
            clasificador.entrenarClasificador(rfG, rfOptions, train);
            clasificador.validarClasificador(rfG, train, valid);
            System.out.println("\tMaquina de Soporte Vectorial");
            clasificador.entrenarClasificador(smoG, smoOptions, train);
            clasificador.validarClasificador(smoG, train, valid);
            System.out.println("\tRedes Neuronales Perceptron Multicapa");
            clasificador.entrenarClasificador(mpG, mpOptions, train);
            clasificador.validarClasificador(mpG, train, valid);
            
            System.out.println("****************************");
            
            c.guardarModelo(path+"mpG", mpG);
            
            Classifier mpG2;
            
            mpG2=c.cargarModelo(path+"mpG");
            clasificador.predecir(prede, mpG2, train);

            //clasificador.predecir(prede, mpG);
        } catch (Exception ex) {
            Logger.getLogger(PruebaWeka2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
