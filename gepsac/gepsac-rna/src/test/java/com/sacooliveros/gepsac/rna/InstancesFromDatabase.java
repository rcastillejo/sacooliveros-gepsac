/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna;

import weka.core.Instances;
import weka.experiment.DatabaseUtils;
import weka.experiment.InstanceQuery;

/**
 *
 * @author Ricardo
 */
public class InstancesFromDatabase {

    public static Instances getInstanceDataFromDatabase(String pSql, String pInstanceRelationName) {
        try {
            DatabaseUtils utils = new DatabaseUtils();

            InstanceQuery query = new InstanceQuery();

            query.setUsername("postgres");
            query.setPassword("postgres");
            query.setQuery(pSql);

            Instances data = query.retrieveInstances();
            data.setRelationName(pInstanceRelationName);

            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
