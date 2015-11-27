/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ServiceException;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.Clasificador;
import com.sacooliveros.gepsac.service.experto.rna.clasificador.ClasificadorFactory;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaQueryFactory;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public class AlumnoPerfilRNA {

    private static final Logger log = LoggerFactory.getLogger(AlumnoPerfilRNA.class);

    private final Alumno alumno;

    public AlumnoPerfilRNA(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<PerfilEvaluado> clasificar() {
        Instancia instancia;
        Classifier clasificador;
        List<PerfilEvaluado> perfilEvaluado;

        instancia = InstanciaQueryFactory.create();

        try {

            //Cargar alumnos evaluados
            Instances alumnosEvaluados = instancia.getTrainInstances();

            //Cargar alumno a evaluar
            Instances alumnoEvaluar = instancia.getPredicInstances(alumno);

            //Entrenar perceptron multicapa
            clasificador = ClasificadorFactory.create(Clasificador.MLP);
            clasificador.buildClassifier(alumnosEvaluados);

            //Obtener Prediccion de cada perfil evaluado
            perfilEvaluado = obtenerPredicciones(clasificador, alumnosEvaluados, alumnoEvaluar);
            log.info("Perfiles evaluados [{}]", new Object[]{perfilEvaluado});

            return perfilEvaluado;
        } catch (Exception e) {
            throw new ServiceException("Error al ejecuta red neuronal", e);
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

}
