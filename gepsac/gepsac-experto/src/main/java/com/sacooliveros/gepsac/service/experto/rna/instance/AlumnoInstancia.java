/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.instance;

import com.sacooliveros.gepsac.model.experto.Alumno;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 *
 * @author Ricardo
 */
public class AlumnoInstancia implements Instancia<Alumno> {
    private static final Logger log = LoggerFactory.getLogger(AlumnoInstancia.class);
    
    private static final String SQL_TRAIN = "Select cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil from tp_alumno_evaluado";
    private static final String SQL_PREDIC = "Select cod_alumno,genero,edad,contextura,altura,tipo_familia,orden_nacimiento,num_hnos,nivel_escolar,grado_escolar,promedio_escolar,nro_cambio_colegio,religion,nacionalidad,distrito,provincia,departamento,cod_perfil from tp_alumno_postulante where cod_alumno='";
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
        String sql = SQL_PREDIC+alumno.getCodigo()+'\'';//MessageFormat.format(SQL_PREDIC, new Object[]{alumno.getCodigo()});
        return getInstances(sql);
    }

    private Instances getInstances(String sql) {
        try {
            log.debug("Obteniendo instancias sql[{}]", sql);
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
