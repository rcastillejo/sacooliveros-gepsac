/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaFactory;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
    public EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacion) {
        
        Alumno alumno = evaluacion.getAlumno();
        
        try {
            AlumnoDAO alumnoDao = DAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionPostulanteDAO evaluacionDao = DAOFactory.getDAOFactory().getEvaluacionPostulanteDAO();
            Instancia instancia = InstanciaFactory.create();
            
            //Grabar alumno postulante a evaluar
            alumnoDao.grabarPostulante(alumno);

            //Cargar alumnos evaluados
            Instances alumnosEvaluados = instancia.getTrainInstances();

            //Cargar alumno a evaluar
            Instances alumnoEvaluar = instancia.getPredicInstances(alumno,
                    Collections.list(alumnosEvaluados.enumerateAttributes()),
                    alumnosEvaluados.classAttribute());

            //Entrenar perceptron multicapa
            Classifier clasificador = instancia.train(alumnosEvaluados);

            //Obtener Prediccion de cada perfil evaluado
            List<PerfilEvaluado> perfilesEvaluado = instancia.predict(clasificador, alumnosEvaluados, alumnoEvaluar);
            log.info("Perfiles evaluados [{}]", new Object[]{perfilesEvaluado});

            evaluacion.setPerfiles(perfilesEvaluado);
            evaluacion.setEstado(Estado.REGISTRADO);
            
            evaluacionDao.ingresar(evaluacion);
            
            return evaluacion;

        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.REGISTRAR, e, alumno.getCodigo());
        }
    }

}
