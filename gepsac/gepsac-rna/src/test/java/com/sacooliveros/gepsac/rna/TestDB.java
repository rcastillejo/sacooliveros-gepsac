/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna;

import com.sacooliveros.gepsac.rna.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public class TestDB {

    public static void main(String[] args) {

        Instances train = InstancesFromDatabase.getInstanceDataFromDatabase("select * from desertor", "desertor");

        Instances predicted = InstancesFromDatabase.getInstanceDataFromDatabase("select * from evaluado", "desertor");
         
        /*System.out.println("Imprimiendo train");
         printInstaces(train);*/
        System.out.println("Imprimiendo predicted");
        printInstaces(predicted);

        analyse(train, predicted);
    }

    private static List<Desertor> getDesertores() {
        List<Desertor> desertores = new ArrayList<>();

        desertores.add(new Desertor(1, 41, "M", "SECUNDARIA", 6900, "0", 28, 11500, "ABIERTO", "ABIERTO", 0, 600, 17, null));
        desertores.add(new Desertor(2, 35, "M", "TECNICO", 800, "3", 11, 2800, "BLOQUEADO", "BLOQUEADO", 0, 200, 6, null));

        return desertores;
    }

    private static void printInstaces(Instances data) {
        List<Instance> instances = Collections.list((Enumeration<Instance>) data.enumerateInstances());
        for (Instance instance : instances) {
            List<Attribute> attributes = Collections.list((Enumeration<Attribute>) instance.enumerateAttributes());
            System.out.println("instace[");
            for (Attribute attribute : attributes) {
                System.out.println("attribute:" + attribute);
            }
            System.out.println("class attribute:" + instance.classAttribute());
            System.out.println("]");
        }
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

            System.out.println("Imprimiendo train clasificado");

            printInstaces(train);

            Evaluation eTest = new Evaluation(train);
            eTest.evaluateModel(mlp, train);

            datapredict.setClassIndex(datapredict.numAttributes() - 1);
            datapredict.deleteAttributeAt(0);

            //Instances predicteddata = new Instances(datapredict);
            for (int i = 0; i < datapredict.numInstances(); i++) {

                Instance newInst = datapredict.instance(i);
                double[] distribution = mlp.distributionForInstance(newInst);
                double pred = mlp.classifyInstance(newInst);
                int predInt = (int) pred;//Math.round(pred);
                String predString = train.classAttribute().value(predInt);
                System.out.println("cliente[" + i + "] distribution[positive="
                        + (Math.round(distribution[0] * 100.0) / 100.0) + ",negative=" + (Math.round(distribution[1] * 100.0) / 100.0) + "] pred[" + pred + "] predInt[" + predInt + "] desertor[" + predString + "]");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
