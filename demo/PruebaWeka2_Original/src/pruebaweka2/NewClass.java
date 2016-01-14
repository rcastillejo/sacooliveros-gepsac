/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweka2;

import java.util.Collections;
import java.util.List;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.FastVector;
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
            DataSource ds ;
            //ds = new DataSource(path + "entrenamiento.arff");
            //Instances train = ds.getDataSet();
            Instances train = InstancesFromDatabase.getInstanceDataFromDatabase("select * from desertor", "desertor");
            
            train.setClassIndex(train.numAttributes() - 1);

            //train.deleteAttributeAt(0);
            System.out.println("train:["+train+"]");
            int numClasses = train.numClasses();

            for (int i = 0; i < numClasses; i++) {
                System.out.println("class value [" + i + "]=" + train.classAttribute().value(i) + "");
            }

            //Instance of NN
            MultilayerPerceptron mlp = new MultilayerPerceptron();
            mlp.setOptions(weka.core.Utils.splitOptions(mpOptions));
            mlp.buildClassifier(train);
             
 
            /*ds = new DataSource(path + "predecir.arff"); 
            Instances datapredict = ds.getDataSet();*/
            Instances datapredict;// = InstancesFromDatabase.getInstanceDataFromDatabase("select * from evaluado", "desertor");
            
            FastVector fvWekaAttributes = new FastVector();
            //fvWekaAttributes.addElement(new Attribute("codigo"));
            /*int numAttrPredict = train.numAttributes();
            for (int i = 0; i<numAttrPredict; i++) {
                fvWekaAttributes.addElement(train.attribute(i));
            }*/
            List<Attribute> attrs = Collections.list(train.enumerateAttributes());
            for (Attribute attr : attrs) {
                fvWekaAttributes.addElement(attr);
            }
            fvWekaAttributes.addElement(train.classAttribute());
            
            datapredict = new Instances("desertor", fvWekaAttributes, 0);
            System.out.println("datapredict1:["+datapredict+"]"); 
            
            Instance ins = new Instance(14);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(0),1);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(1),35);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(2),"M");
            ins.setValue((Attribute)fvWekaAttributes.elementAt(3),"TECNICO");
            ins.setValue((Attribute)fvWekaAttributes.elementAt(4),800);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(5),3);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(6),11);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(7),2800);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(8),"BLOQUEADO");
            ins.setValue((Attribute)fvWekaAttributes.elementAt(9),"BLOQUEADO");
            ins.setValue((Attribute)fvWekaAttributes.elementAt(10),0);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(11),200);
            ins.setValue((Attribute)fvWekaAttributes.elementAt(12),6);
            datapredict.add(ins);
            
            System.out.println("datapredict2:["+datapredict+"]");
            datapredict.setClassIndex(datapredict.numAttributes() - 1);
            //datapredict.deleteAttributeAt(0);
            

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
