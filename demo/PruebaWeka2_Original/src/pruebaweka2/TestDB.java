/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Ricardo
 */
public class TestDB {
    
    public static void main(String[] args) {
        
        Instances train = InstancesFromDatabase.getInstanceDataFromDatabase("select * from desertor", "desertor");
        Instances predicted = InstancesFromDatabase.getInstanceDataFromDatabase("select * from evaluado", "desertor");
        
        analyse(train, predicted);
    }
    
    private static void analyse(Instances train, Instances datapredict) {
        String mpOptions = "-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a";

        try {
 
            train.setClassIndex(train.numAttributes() - 1);
            train.deleteAttributeAt(0);
            int numClasses = train.numClasses();

            for (int i = 0; i < numClasses; i++) {
                System.out.println("class value [" + i + "]=" + train.classAttribute().value(i) + "");
            }

            //Instance of NN
            MultilayerPerceptron mlp = new MultilayerPerceptron();
            mlp.setOptions(weka.core.Utils.splitOptions(mpOptions));
            mlp.buildClassifier(train);

            datapredict.setClassIndex(datapredict.numAttributes() - 1);
            datapredict.deleteAttributeAt(0);
 
            //Instances predicteddata = new Instances(datapredict);
            for (int i = 0; i < datapredict.numInstances(); i++) {

                Instance newInst = datapredict.instance(i);
                double pred = mlp.classifyInstance(newInst);
                int predInt = (int) pred;//Math.round(pred);
                String predString = train.classAttribute().value(predInt);
                System.out.println("cliente[" + i + "] pred[" + pred + "] predInt[" + predInt + "] desertor[" + predString + "]");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
