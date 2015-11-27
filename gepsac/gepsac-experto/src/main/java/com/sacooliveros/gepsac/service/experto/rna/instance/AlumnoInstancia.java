/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.instance;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ServiceException;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.Clasificador;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.ClasificadorFactory;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 *
 * @author Ricardo
 */
public class AlumnoInstancia implements Instancia<Alumno, PerfilEvaluado> {

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
        Instances instanciasEntrenadas = getInstances(SQL_TRAIN);
        int numClasses = instanciasEntrenadas.numClasses();
        for (int i = 0; i < numClasses; i++) {
            log.debug("Clasificacion obtenida [" + i + "={}]", instanciasEntrenadas.classAttribute().value(i));
        }
        return getInstances(SQL_TRAIN);
    }

    @Override
    public Instances getPredicInstances(Alumno alumno) {
        String sql = SQL_PREDIC + alumno.getCodigo() + '\'';//MessageFormat.format(SQL_PREDIC, new Object[]{alumno.getCodigo()});
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

    @Override
    public List<PerfilEvaluado> predict(Classifier clasificador, Instances dataEntrenado, Instances dataEvaluar) {
        try {
            return obtenerPredicciones(clasificador, dataEntrenado, dataEvaluar);
        } catch (Exception e) {
            throw new ServiceException("Error al predecir alumno", e);
        }
    }

    private List<PerfilEvaluado> obtenerPredicciones(Classifier clasificador, Instances alumnosEvaluados, Instances alumnoEvaluar) throws Exception {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        //Obtener Prediccion
        for (int i = 0; i < alumnoEvaluar.numInstances(); i++) {
            Instance newInst = alumnoEvaluar.instance(i);
            double[] probabilidad = clasificador.distributionForInstance(newInst);
            double prediccion = clasificador.classifyInstance(newInst);
            int prediccionPerfil = (int) prediccion;

            log.trace("Prediccion [{}], probabilidad [{}]", new Object[]{prediccion, probabilidad});

            predicciones = obtenerProbabilidadPorPerfil(probabilidad, prediccionPerfil, alumnosEvaluados);

            break;
        }

        return predicciones;
    }

    private List<PerfilEvaluado> obtenerProbabilidadPorPerfil(double[] probabilidadPerfil, int predicionPerfil, Instances alumnosEvaluados) {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        for (int i = 0; i < probabilidadPerfil.length; i++) {
            PerfilEvaluado perfil = new PerfilEvaluado();
            double prediccion = probabilidadPerfil[i];
            String codigoPerfil = alumnosEvaluados.classAttribute().value((int) prediccion);
            perfil.setPerfil(codigoPerfil);
            perfil.setProbabilidad((Math.round(prediccion * 100.0) / 100.0));//Redondeado 2 decimales
            if (i == predicionPerfil) {
                perfil.seleccionar();
            }
            log.trace("Perfil evaluado [{}]", perfil);
            predicciones.add(perfil);
        }

        return predicciones;

    }

    @Override
    public Classifier train(Instances dataEntrenar) {
        try {
            Classifier clasificador = ClasificadorFactory.create(Clasificador.MLP);
            clasificador.buildClassifier(dataEntrenar);
            return clasificador;
        } catch (Exception e) {
            throw new ServiceException("Error al predecir alumno", e);
        }
    }

}
