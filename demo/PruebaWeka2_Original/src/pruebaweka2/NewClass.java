/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Ricardo
 */
public class NewClass {

    public static void main(String[] args) {
        String path = "D:\\UPC\\PI2\\Sistema_Experto\\Weka Ejemplo\\Modelos_BD2\\";

        String mpOptions = "-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a";

        try {

            //Reading training arff file
            //DataSource ds = new DataSource(path + "iris.arff");
            DataSource ds = new DataSource(path + "entrenamiento.arff");
            /*FileReader trainreader = new FileReader(path + "Data Entrenamiento-bkp.csv");
             Instances train = new Instances(trainreader);*/
            Instances train = ds.getDataSet();
            
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
             

            //ds = new DataSource(path + "iris-unknown.arff");
            ds = new DataSource(path + "predecir.arff");
            /*
             Instances datapredict = new Instances(
             new BufferedReader(
             new FileReader(path + "Data predecir.csv")));*/
            Instances datapredict = ds.getDataSet();
            datapredict.setClassIndex(datapredict.numAttributes() - 1);
            datapredict.deleteAttributeAt(0);

            for (int i = 0; i < datapredict.numClasses(); i++) {
                System.out.println("class value [" + i + "]=" + datapredict.classAttribute().value(i) + "");
            }

            //Instances predicteddata = new Instances(datapredict);

            for (int i = 0; i < datapredict.numInstances(); i++) { 

                Instance newInst = datapredict.instance(i);
                double pred = mlp.classifyInstance(newInst);
                int predInt = (int) Math.round(pred);
                String predString = train.classAttribute().value(predInt);
                System.out.println("cliente["+i+"] pred["+pred+"] predInt["+predInt+"] desertor["+predString+"]");
                
            }
            /*
             for (int i = 0; i < datapredict.numInstances(); i++) {
             double clsLabel = mlp.classifyInstance(datapredict.instance(i));
             predicteddata.instance(i).setClassValue(clsLabel);
                
             for (int j = 0; j < datapredict.numClasses(); j++) {
             System.out.println("class value ["+i+"]="+datapredict.classAttribute().value(i)+"");
             }
                
             }*/

            /*BufferedWriter writer = new BufferedWriter(
             new FileWriter(path + "Data predecir resultado.csv"));
             writer.write(predicteddata.toString());
             writer.newLine();
             writer.flush();
             writer.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
