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
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaFactory;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.ResultadoInferencia;
import java.text.MessageFormat;
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

    /**
     * Funcionalidades del Caso Uso Evaluar Nuevos Alumnos (Postulante)
     */
    /**
     * {@inheritDoc }
     *
     * @param evaluacion {@inheritDoc }
     * @return {@inheritDoc }
     */
    @Override
    public EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacion) {

        Alumno alumno = evaluacion.getAlumno();

        try {
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionPostulanteDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionPostulanteDAO();
            Instancia instancia = InstanciaFactory.create();

            //Cargar alumnos evaluados
            Instances alumnosEvaluados = instancia.getTrainInstances();

            if (alumnosEvaluados.numInstances() == 0) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_ALUMNO_EVALUADOS);
            }

            //Cargar alumno a evaluar
            alumnoDao.cargarCodificacionAlumno(alumno);
            log.debug("Alumno con los datos completados [{}]", new Object[]{alumno});
            Instances alumnoEvaluar = instancia.getPredicInstances(alumno,
                    Collections.list(alumnosEvaluados.enumerateAttributes()),
                    alumnosEvaluados.classAttribute());

            //Entrenar perceptron multicapa
            Classifier clasificador = instancia.train(alumnosEvaluados);

            //Obtener Prediccion de cada perfil evaluado
            List<PerfilEvaluado> perfilesEvaluado = instancia.predict(clasificador, alumnosEvaluados, alumnoEvaluar);

            evaluacion.setPerfiles(perfilesEvaluado);
            evaluacion.setEstado(Estado.REGISTRADO);

            //Grabar alumno postulante a evaluar
            alumnoDao.grabarPostulante(alumno);
            log.debug("Alumno postulante grabado[{}]", alumno);

            //Grabar perfiles evaluados
            evaluacionDao.ingresar(evaluacion);
            log.debug("Evaluacion grabado[{}]", alumno);

            log.info("Evaluacion de Postulante, resultado [perfiles={}]", new Object[]{perfilesEvaluado});

            return evaluacion;

        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.EVALUAR, e, alumno.getCodigo());
        }
    }

    /*public String evaluarRespuestaAcosoEscolar(List<EvaluacionAcosoEscolar> evaluacionesAcosoEscolar) {
        //Cargar las reglas y preguntas de acoso escolar para cada perfil
        Engine engine = EngineFactory.create();
        for (EvaluacionAcosoEscolar evaluacionAcosoEscolar : evaluacionesAcosoEscolar) {
            evaluarRespuestaAcosoEscolar(evaluacionAcosoEscolar, engine);
        }
        return Mensaje.EVALUAR_ALUMNO_POSTULANTE;
    }*/
    /**
     * Funcionalidades del Caso Uso Evaluar Respuesta Acoso Escolar
     */
    /**
     *
     * @param estado
     * @return
     * @throws ExpertoServiceException
     */
    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolar(com.sacooliveros.gepsac.model.comun.Estado estado) throws ExpertoServiceException {

        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            List<EvaluacionAcosoEscolar> evaluaciones = evaluacionDao.listarEvaluacionPorEstado(estado.getCodigo());

            if (evaluaciones == null || evaluaciones.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_EVALUACION_ACOSO_ESCOLAR, estado.getCodigo());
            }

            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{estado, evaluaciones.size()});

            return evaluaciones;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e, estado.getCodigo());
        }
    }

    @Override
    public String evaluarRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar, Engine engine) throws ExpertoServiceException {

        try {
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            /**
             * 4.1.4.	El sistema carga las preguntas de la evaluación
             */
            List<PreguntaEvaluacion> preguntas = evaluacionAcosoEscolar.getPreguntas();

            /**
             * 4.1.5.	El sistema evalúa las respuestas de una evaluación de
             * acuerdo a las reglas.
             */
            ResultadoInferencia resultado = (ResultadoInferencia) engine.evaluate(preguntas);

            /**
             * 4.1.6.	El sistema determina el perfil de acoso escolar de una
             * evaluación.
             */
            String perfil = resultado.getConclusion();

            evaluacionAcosoEscolar.setPerfil(perfil);

            /**
             * 4.1.7.	El sistema graba la evaluación con el perfil en estado
             * Evaluado.
             */
            evaluacionDao.actualizar(evaluacionAcosoEscolar);

            /**
             * 4.1.8.	El sistema actualiza el perfil del alumno evaluado
             */
            Alumno alumnoEvaluado = evaluacionAcosoEscolar.getAlumno();
            alumnoEvaluado.setPerfil(evaluacionAcosoEscolar.getPerfil());
            alumnoDao.actualizar(alumnoEvaluado);

            log.debug("Evaluacion de acoso escolar, resultado [perfil={}]", new Object[]{perfil});

            /**
             * 4.1.9.	El sistema muestra el mensaje [Evaluación realizada
             * satisfactoriamente] en los registros del sistema.
             */
            return MessageFormat.format(Mensaje.EVALUAR_ACOSO_ESCOLAR, new Object[]{evaluacionAcosoEscolar.getCodigo()});
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.EVALUAR_RESPUESTA_ACOSO_ESCOLAR, e, evaluacionAcosoEscolar.getCodigo());
        }
    }

}
