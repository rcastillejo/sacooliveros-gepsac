/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.instance;

import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public interface Instancia<S,P> {

    Instances getTrainInstances();
    
    Instances getPredicInstances(S source);
    
    Classifier train(Instances dataEntrenar);
    
    List<P> predict(Classifier clasificador, Instances dataEntrenado, Instances dataEvaluar);
}
