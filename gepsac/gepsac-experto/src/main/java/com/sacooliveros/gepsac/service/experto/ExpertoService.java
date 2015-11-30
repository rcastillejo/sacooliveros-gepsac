/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaFactory;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.ResultadoInferencia;
import java.util.Collections;
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
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionPostulanteDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionPostulanteDAO();
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

            evaluacion.setPerfiles(perfilesEvaluado);
            evaluacion.setEstado(Estado.REGISTRADO);

            evaluacionDao.ingresar(evaluacion);

            log.info("Evaluacion de Postulante, resultado [perfiles={}]", new Object[]{perfilesEvaluado});

            return evaluacion;

        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.REGISTRAR, e, alumno.getCodigo());
        }
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolar(com.sacooliveros.gepsac.model.comun.Estado estado) throws ExpertoServiceException {
        ;
        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            List<EvaluacionAcosoEscolar> evaluaciones = evaluacionDao.listarEvaluacionPorEstado(estado.getCodigo());

            if (evaluaciones == null || evaluaciones.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EV_ACOSO_ESCOLAR, estado.getCodigo());
            }

            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{estado, evaluaciones.size()});

            return evaluaciones;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.REGISTRAR, e, estado.getCodigo());
        }
    }

    @Override
    public EvaluacionAcosoEscolar evaluarRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar, Engine engine) throws ExpertoServiceException {

        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            List<PreguntaEvaluacion> preguntas = evaluacionAcosoEscolar.getPreguntas();
            ResultadoInferencia resultado = (ResultadoInferencia) engine.evaluate(preguntas);

            String perfil = resultado.getConclusion();

            evaluacionAcosoEscolar.setPerfil(perfil);

            evaluacionDao.actualizar(evaluacionAcosoEscolar);

            log.info("Evaluacion de acoso escolar, resultado [perfil={}]", new Object[]{perfil});
            return evaluacionAcosoEscolar;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.REGISTRAR, e, evaluacionAcosoEscolar.getCodigo());
        }
    }

}
