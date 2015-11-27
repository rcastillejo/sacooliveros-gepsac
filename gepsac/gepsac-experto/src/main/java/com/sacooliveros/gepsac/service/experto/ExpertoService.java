/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ServiceException;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaQueryFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 *
 * @author Ricardo
 */
public class ExpertoService implements Experto {

    private static final Logger log = LoggerFactory.getLogger(ExpertoService.class);

    @Override
    public EvaluacionPostulante evaluarAlumno(Alumno alumno) {
        
        try {
            AlumnoDAO alumnoDao = DAOFactory.getDAOFactory().getAlumnoDAO();
            Instancia instancia = InstanciaQueryFactory.create();

            //Grabar alumno postulante a evaluar
            alumnoDao.grabarPostulante(alumno);
            
            //Cargar alumnos evaluados
            Instances alumnosEvaluados = instancia.getTrainInstances();

            //Cargar alumno a evaluar
            Instances alumnoEvaluar = instancia.getPredicInstances(alumno);

            //Entrenar perceptron multicapa
            Classifier clasificador = instancia.train(alumnosEvaluados);

            //Obtener Prediccion de cada perfil evaluado
            List<PerfilEvaluado> perfilEvaluado = instancia.predict(clasificador, alumnosEvaluados, alumnoEvaluar);
            log.info("Perfiles evaluados [{}]", new Object[]{perfilEvaluado});

            EvaluacionPostulante evaluacion = new EvaluacionPostulante();
            evaluacion.setAlumno(alumno);
            evaluacion.setPerfiles(perfilEvaluado);
            return evaluacion;

        } catch (Exception e) {
            throw new ServiceException("Error al evaluar alumno postulante{" + alumno.getCodigo() + "}", e);
        }
    }

}
