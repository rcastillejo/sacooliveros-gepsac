/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import java.util.ArrayList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 *
 * @author DENNYZ
 */
public class Clasificador {

    public Classifier entrenarClasificador(Classifier clasificador, String opciones, Instances data) throws Exception {
        String[] options = weka.core.Utils.splitOptions(opciones);
        clasificador.setOptions(options);
        clasificador.buildClassifier(data);
        System.out.println("YA SE ENTRENO ...");
        return clasificador;
    }

    public void validarClasificador(Classifier classifier, Instances train, Instances test) throws Exception {
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);
        System.out.println("YA SE VALIDO...");
        System.out.println(eval.toSummaryString("\nResultados\n======\n", true));
        System.out.println(eval.toMatrixString("\nmatriz de resultados\n======\n"));
    }

    public void predecir(Instances pred, Classifier cls, Instances train) {
        for (int i = 0; i < pred.numInstances(); i++) {
            double targetIndex;
            int identificador;
            try {
                targetIndex = cls.classifyInstance(pred.instance(i));
                identificador = i + 1;
                /*System.out.print("ID: " + identificador);
                 System.out.print(", actual: " + pred.classAttribute().value((int) pred.instance(i).classValue()));
                 System.out.println(", predicted: " + pred.classAttribute().value((int) targetIndex));
                 System.out.println(">>> "+identificador+" = ["+targetIndex+"]");*/
                if (targetIndex < 0.5) {

                    System.out.println("El cliente " + identificador + " es desertor [" + targetIndex + "]");

                } else {
                    System.out.println("El cliente " + identificador + " NO es desertor [" + targetIndex + "]");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
