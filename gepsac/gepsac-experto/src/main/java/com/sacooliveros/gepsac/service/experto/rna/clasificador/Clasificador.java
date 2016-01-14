/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.clasificador;

/**
 *
 * @author Ricardo
 */
public enum Clasificador {

    /**
     *
     */
    MLP("weka.classifiers.functions.MultilayerPerceptron", "-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a");

    private final String classname;
    private final String options;

    Clasificador(String classname, String options) {
        this.classname = classname;
        this.options = options;
    }

    public String getClassname() {
        return classname;
    }

    public String getOptions() {
        return options;
    }

}
