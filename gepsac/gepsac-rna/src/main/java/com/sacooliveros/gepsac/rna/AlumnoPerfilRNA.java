/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.rna.clasificador.Clasificador;
import com.sacooliveros.gepsac.rna.clasificador.ClasificadorFactory;
import com.sacooliveros.gepsac.rna.instance.Instancia;
import com.sacooliveros.gepsac.rna.instance.InstanciaQueryFactory;
import java.util.ArrayList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public class AlumnoPerfilRNA {

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

            clasificador = ClasificadorFactory.create(Clasificador.MLP);
            //Entrenar perceptron multicapa
            clasificador.buildClassifier(alumnosEvaluados);

            perfilEvaluado = obtenerPredicciones(clasificador, alumnosEvaluados, alumnoEvaluar);

            return perfilEvaluado;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<PerfilEvaluado> obtenerPredicciones(Classifier clasificador, Instances alumnosEvaluados, Instances alumnoEvaluar) throws Exception {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        //Obtener Prediccion
        //for (int i = 0; i < alumnoEvaluar.numInstances(); i++) {
            Instance newInst = alumnoEvaluar.instance(0);
            double[] probabilidad = clasificador.distributionForInstance(newInst);
            double prediccion = clasificador.classifyInstance(newInst);
            int prediccionPerfil = (int) prediccion;
             
            System.out.println("alumno distribution[positive="
                    + (Math.round(probabilidad[0] * 100.0) / 100.0) + ",negative=" + (Math.round(probabilidad[1] * 100.0) / 100.0) + "] pred[" + prediccion + "]");

            predicciones = obtenerProbabilidadPorPerfil(probabilidad, prediccionPerfil, alumnosEvaluados);

        //    break;
        //}

        return predicciones;
    }

    private List<PerfilEvaluado> obtenerProbabilidadPorPerfil(double[] probabilidadPerfil, int predicionPerfil, Instances alumnosEvaluados) {
        List<PerfilEvaluado> predicciones;

        predicciones = new ArrayList<PerfilEvaluado>();

        for (int j = 0; j < probabilidadPerfil.length; j++) {
            PerfilEvaluado perfil = new PerfilEvaluado();
            double prediccion = probabilidadPerfil[j];
            String codigoPerfil = alumnosEvaluados.classAttribute().value((int) prediccion);
            perfil.setPerfil(codigoPerfil);
            perfil.setProbabilidad((Math.round(prediccion * 100.0) / 100.0));//Redondeado 2 decimales
            if (j == predicionPerfil) {
                perfil.seleccionar();
            }
            predicciones.add(perfil);
        }

        return predicciones;

    }

}
