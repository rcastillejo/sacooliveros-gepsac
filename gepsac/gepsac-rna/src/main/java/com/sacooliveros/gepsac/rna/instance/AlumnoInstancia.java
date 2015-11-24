/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna.instance;

import com.sacooliveros.gepsac.model.experto.Alumno;
import java.text.MessageFormat;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 *
 * @author Ricardo
 */
public class AlumnoInstancia implements Instancia<Alumno> {

    private static final String SQL_TRAIN = "";
    private static final String SQL_PREDIC = "";
    private static final String RELATION_NAME = "alumno";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private final InstanceQuery query;

    public AlumnoInstancia() throws Exception {
        query = new InstanceQuery();
        query.setUsername(USERNAME);
        query.setPassword(PASSWORD);
    }

    @Override
    public Instances getTrainInstances() {
        return getInstances(SQL_TRAIN);
    }

    @Override
    public Instances getPredicInstances(Alumno alumno) {
        return getInstances(MessageFormat.format(SQL_PREDIC, alumno.getCodigo()));
    }

    private Instances getInstances(String sql) {
        try {
            query.setQuery(sql);
            Instances data = query.retrieveInstances();
            data.setRelationName(RELATION_NAME);

            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
