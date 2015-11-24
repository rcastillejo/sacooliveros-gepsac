/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna.instance;

import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public interface Instancia<S> {

    Instances getTrainInstances();
    
    Instances getPredicInstances(S source);
}
