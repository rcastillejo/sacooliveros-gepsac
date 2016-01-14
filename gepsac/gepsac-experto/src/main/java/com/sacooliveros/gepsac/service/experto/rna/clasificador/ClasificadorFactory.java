/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.clasificador;

import weka.classifiers.Classifier;
import weka.core.Utils;

/**
 *
 * @author Ricardo
 */
public class ClasificadorFactory {

    public static Classifier create(Clasificador tipo) {
        String[] options;
        Classifier clasificador;

        try {
            options = Utils.splitOptions(tipo.getOptions());
            clasificador = Classifier.forName(tipo.getClassname(), options);//new MultilayerPerceptron();
            return clasificador;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
