/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.ConfigDAO;
import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.exception.ForeignKeyException;
import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import com.sacooliveros.gepsac.model.util.State;
import com.sacooliveros.gepsac.model.util.StateUtil;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import com.sacooliveros.gepsac.service.experto.rna.instance.Instancia;
import com.sacooliveros.gepsac.service.experto.rna.instance.InstanciaFactory;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.ResultadoInferencia;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
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
            ConfigDAO configDao = SingletonDAOFactory.getDAOFactory().getConfigDAO();
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionPostulanteDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionPostulanteDAO();
            Instancia instancia = InstanciaFactory.create();

            //Cargar alumnos evaluados
            Instances alumnosEvaluados = instancia.getTrainInstances();
            int cantAlumnosEvaluados = alumnosEvaluados.numInstances();
            String minimoEvaluados = configDao.obtener(Config.MINIMO_ALUMNO_EVALUADOS);
            int alumnosEvaluadosMinimo = Integer.parseInt(minimoEvaluados);
            log.debug("Alumno evaluados [cantidad={}, minimoRequerido={}]", new Object[]{cantAlumnosEvaluados, alumnosEvaluadosMinimo});
            
            if (cantAlumnosEvaluados < alumnosEvaluadosMinimo) {
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
            evaluacion.setCodigoEstado(Estado.EvaluacionPostulante.REGISTRADO);

            //Grabar alumno postulante a evaluar
            alumnoDao.grabarPostulante(alumno);
            log.debug("Alumno postulante grabado[{}]", alumno);

            //Grabar perfiles evaluados
            evaluacionDao.ingresar(evaluacion);
            log.debug("Evaluacion grabado[{}]", alumno);

            log.info("Evaluacion de Postulante, resultado [perfiles={}]", new Object[]{perfilesEvaluado});

            return evaluacion;

        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw e;
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
     * @param codigoEstado
     * @return
     * @throws ExpertoServiceException
     */
    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolar(String codigoEstado) throws ExpertoServiceException {

        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            List<EvaluacionAcosoEscolar> evaluaciones = evaluacionDao.listarEvaluacionPorEstado(codigoEstado);

            if (evaluaciones == null || evaluaciones.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_EVALUACION_ACOSO_ESCOLAR, StateUtil.getDescription(codigoEstado));
            }

            for (EvaluacionAcosoEscolar evaluacion : evaluaciones) {
                List<PreguntaEvaluacion> preguntas = evaluacionDao.listarPreguntaEvaluacion(evaluacion.getCodigo());
                evaluacion.setPreguntas(preguntas);
                log.trace("Preguntas resueltas [codigo={}, preguntas={}]", new Object[]{evaluacion.getCodigo(), preguntas});
            }

            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{codigoEstado, evaluaciones.size()});

            return evaluaciones;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e, codigoEstado);
        }
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluadoResuelto() throws ExpertoServiceException {

        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            List<EvaluacionAcosoEscolar> evaluaciones = evaluacionDao.listarEvaluacionEvaluadoResuelto();

            if (evaluaciones == null || evaluaciones.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_EVALUACION_ACOSO_ESCOLAR_EVALUADO_RESUELTO);
            }

            log.info("Listado de evaluaciones obtenidas [tamanio={}]", new Object[]{evaluaciones.size()});

            return evaluaciones;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR_EVALUADO_RESUELTO, e);
        }
    }

    @Override
    public String evaluarRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacion, Engine engine) throws ExpertoServiceException {

        try {
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();
            ReglaDAO reglaDAO = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            EvaluacionAcosoEscolar evaluacionAcosoEscolar = consultarResultadoAcosoEscolar(evaluacion.getCodigo());

            /**
             * 4.1.4.	El sistema carga las preguntas de la evaluación
             */
            log.info("[{}] El sistema carga las preguntas de la evaluacion", evaluacionAcosoEscolar.getCodigo());
            List<PreguntaEvaluacion> preguntas = evaluacionAcosoEscolar.getPreguntas();

            /**
             * 4.1.5.	El sistema evalúa las respuestas de una evaluación de
             * acuerdo a las reglas.
             */
            log.info("[{}] El sistema determina el perfil de acoso escolar de una evaluacion", evaluacionAcosoEscolar.getCodigo());
            ResultadoInferencia resultado = (ResultadoInferencia) engine.evaluate(preguntas);

            /**
             * 4.1.6.	El sistema determina el perfil de acoso escolar de una
             * evaluación.
             */
            String perfilResultado = resultado.getConclusion();
            Perfil perfil = new Perfil();

            if (perfilResultado != null) {
                perfil.setCodigo(perfilResultado);
                log.info("[{}] El sistema concluyo el perfil '" + perfilResultado + "'", evaluacionAcosoEscolar.getCodigo());
            } else {
                log.info("[{}] El sistema no encontro perfil", evaluacionAcosoEscolar.getCodigo());
            }
            evaluacionAcosoEscolar.setPerfil(perfil);

            /**
             * 4.1.7.	El sistema graba la evaluación con el perfil en estado
             * Evaluado.
             */
            evaluacionAcosoEscolar.setCodigoEstado(Estado.EvaluacionAcosoEscolar.EVALUADO);

            log.info("[{}] El sistema graba la evaluación con el perfil en estado 'Evaluado'", evaluacionAcosoEscolar.getCodigo());
            evaluacionDao.actualizarRespuestaEvaluacion(evaluacionAcosoEscolar, resultado.getReglasActivas());

            /**
             * 4.1.8.	El sistema actualiza el perfil del alumno evaluado
             */
            Alumno alumnoEvaluado = evaluacionAcosoEscolar.getAlumno();
            alumnoEvaluado.setPerfil(evaluacionAcosoEscolar.getPerfil());
            alumnoEvaluado.setCodigoEstado(Estado.Alumno.EVALUADO);

            log.info("[{}] El sistema actualiza el perfil del alumno evaluado", evaluacionAcosoEscolar.getCodigo());
            alumnoDao.actualizarEstadoAlumnoEvaluado(alumnoEvaluado);

            log.debug("[{}] Evaluacion de acoso escolar, resultado [perfil={}]", new Object[]{evaluacionAcosoEscolar.getCodigo(), perfil});

            /**
             * 4.1.9.	El sistema muestra el mensaje [Evaluación realizada
             * satisfactoriamente] en los registros del sistema.
             */
            return MessageFormat.format(Mensaje.EVALUAR_ACOSO_ESCOLAR, new Object[]{evaluacionAcosoEscolar.getCodigo()});
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.EVALUAR_RESPUESTA_ACOSO_ESCOLAR, e, evaluacion.getCodigo());
        }
    }

    @Override
    public String verificarSolicitudPsicologica(EvaluacionAcosoEscolar evaluacionAcosoEscolar) throws ExpertoServiceException {
        log.debug("[{}] Evaluacion de acoso escolar, proviene de una solicitud [{}]", new Object[]{evaluacionAcosoEscolar.getCodigo(), evaluacionAcosoEscolar.getCodigoSolicitud()});
        SolicitudPsicologicaDAO solicitudPsicologicaDAO = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
        EvaluacionAcosoEscolarDAO evaluacionDAO = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();
        String codigoSolicitud = evaluacionAcosoEscolar.getCodigoSolicitud();
        
        List<EvaluacionAcosoEscolar> evaluacionesPorSolicitud = evaluacionDAO.listarEvaluacionPorSolicitud(codigoSolicitud);
        
        for (EvaluacionAcosoEscolar evaluacion : evaluacionesPorSolicitud) {
            if(!evaluacion.getEstado().getCodigo().equals(State.EvaluacionAcosoEscolar.EVALUADO)){
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.SOLICITUD_CON_EVALUACIONES_PENDIENTES_EVALUAR);
            }
        }
        
        SolicitudPsicologica solicitudPsicologica = new SolicitudPsicologica();
        solicitudPsicologica.setCodigo(codigoSolicitud);
        solicitudPsicologica.setCodigoEstado(State.SolicitudPsicologica.POR_ATENDER);
        log.debug("[{}] Se actualizara la solicitud en 'por atender' [{}]", new Object[]{evaluacionAcosoEscolar.getCodigo(), evaluacionAcosoEscolar.getCodigoSolicitud()});
        solicitudPsicologicaDAO.actualizarEstado(solicitudPsicologica);

        return MessageFormat.format(Mensaje.SOLICITUD_POR_ATENDER, new Object[]{solicitudPsicologica.getCodigo()});
    }

    @Override
    public ExplicacionResultado generarExplicacionResultado(String codigoEvaluacion) throws ExpertoServiceException {
        ExplicacionResultado resultado;

        try {
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            EvaluacionAcosoEscolar evaluacionAcosoEscolar = evaluacionDao.obtener(codigoEvaluacion);

            List<String> premisas = reglaDao.listarExplicacionPremisas(codigoEvaluacion);
            List<String> codigoReglas = reglaDao.listarExplicacionReglas(codigoEvaluacion);

            resultado = new ExplicacionResultado();
            resultado.setAlumno(evaluacionAcosoEscolar.getAlumno());
            resultado.setPerfil(evaluacionAcosoEscolar.getPerfil());
            resultado.setPremisas(premisas);

            List<Regla> reglas = new ArrayList<Regla>();
            for (String codigoRegla : codigoReglas) {
                Regla regla = obtenerRegla(codigoRegla);
                reglas.add(regla);
            }

            resultado.setReglas(reglas);

            return resultado;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.GENERAR_EXPLICACION_ACOSO_ESCOLAR, e, codigoEvaluacion);
        }
    }

    @Override
    public EvaluacionAcosoEscolar consultarResultadoAcosoEscolar(String codigoEvaluacion) throws ExpertoServiceException {
        EvaluacionAcosoEscolar resultado;

        try {
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            EvaluacionAcosoEscolar evaluacionAcosoEscolar = evaluacionDao.obtener(codigoEvaluacion);

            List<PreguntaEvaluacion> preguntas = evaluacionDao.listarPreguntaEvaluacion(evaluacionAcosoEscolar.getCodigo());

            for (PreguntaEvaluacion pregunta : preguntas) {
                String codigoPregunta = pregunta.getPregunta().getCodigo();
                log.debug("Preguntas alternativas [codigoPregunta={}, codigoEvaluacion={}]", new Object[]{codigoPregunta, codigoEvaluacion});
                List<PreguntaEvaluacionAlternativa> alternativas = evaluacionDao.listarPreguntaEvaluacionAlternativa(codigoEvaluacion, codigoPregunta);
                pregunta.setAlternativas(alternativas);
                log.debug("Preguntas alternativas [codigoPregunta={}, codigoEvaluacion={}, alternativas={}]", new Object[]{codigoPregunta, codigoEvaluacion, alternativas.size()});
            }

            evaluacionAcosoEscolar.setPreguntas(preguntas);

            return evaluacionAcosoEscolar;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_RESULTADO_ACOSO_ESCOLAR, e, codigoEvaluacion);
        }
    }

    @Override
    public List<Regla> listarRegla() throws ExpertoServiceException {

        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            List<Regla> listado = reglaDao.listar();

            if (listado == null || listado.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_REGLAS_ACOSO_ESCOLAR);
            }

            for (Regla regla : listado) {
                List<PreguntaRegla> preguntas = reglaDao.listarPreguntaRegla(regla.getCodigo());
                regla.setPreguntas(preguntas);
                log.trace("Preguntas [codigo={}, preguntas={}]", new Object[]{regla.getCodigo(), preguntas});
            }

            log.info("Listado de evaluaciones obtenidas [tamanio={}]", new Object[]{listado.size()});

            return listado;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_REGLAS_ACOSO_ESCOLAR, e);
        }
    }

    @Override
    public Regla obtenerRegla(String codigoRegla) throws ExpertoServiceException {

        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            Regla regla = reglaDao.obtener(codigoRegla);

            if (regla == null) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_REGLA_ACOSO_ESCOLAR);
            }

            List<PreguntaRegla> preguntas = reglaDao.listarPreguntaRegla(regla.getCodigo());

            if (preguntas == null || preguntas.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_REGLAS_ACOSO_ESCOLAR);
            }

            regla.setPreguntas(preguntas);

            log.info("Regla obtenida regla={},preguntas={}]", new Object[]{regla, preguntas.size()});

            return regla;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.OBTENER_REGLA_ACOSO_ESCOLAR, e);
        }
    }

    private boolean perfilRepetido(Perfil perfil, Perfil perfilAComparar) {
        return perfilAComparar.getCodigo().equals(perfil.getCodigo());
    }

    private boolean condicionRepetida(TreeSet<String> preguntasRegla, TreeSet<String> preguntasReglaAComparar) {
        return preguntasRegla.containsAll(preguntasReglaAComparar) || preguntasReglaAComparar.containsAll(preguntasRegla);
    }

    private void validarPreguntaRepetida(Regla regla) {
        List<PreguntaRegla> preguntasRegla = regla.getPreguntas();

        for (PreguntaRegla pregunta : preguntasRegla) {
            int cantRepetidos = 0;
            for (PreguntaRegla preguntaAComparar : preguntasRegla) {
                if (pregunta.getPregunta().getCodigo().equals(preguntaAComparar.getPregunta().getCodigo())) {
                    cantRepetidos++;
                }
            }
            if (cantRepetidos > 1) {
                /**
                 * 4.3.1.	Preguntas repetidas en una Regla
                 */
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.PREGUNTAS_REPETIDAS_REGLA);
            }
        }
    }

    private void validarReglaRepetida(Regla reglaVerificar, Regla regla) {
        /**
         * Valida si la condicion coincide
         */
        if (condicionRepetida(reglaVerificar.getSetCodigoPreguntas(), regla.getSetCodigoPreguntas())) {
            throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.REGLA_REPETIDA, reglaVerificar.getCodigo());
        }
    }

    @Override
    public String agregarRegla(Regla regla) throws ExpertoServiceException {

        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            log.info("El sistema valida la seleccion de al menos una pregunta");
            if (regla.getPreguntas() == null || regla.getPreguntas().isEmpty()) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.PREGUNTA_REQUERIDO);
            }

            /**
             * 4.2.1.4.	El sistema valida la definición de la regla
             */
            log.info("El sistema valida la repeticion de una pregunta");
            validarPreguntaRepetida(regla);

            /**
             * El sistema valida que seleccione un perfil
             */
            if (regla.getPerfil() == null || regla.getPerfil().getCodigo() == null) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.PERFIL_REQUERIDO);
            }

            /**
             * 4.3.2.	Regla repetida
             */
            log.info("El sistema valida la repeticion de una regla");
            List<Regla> reglas = reglaDao.listar();
            for (Regla reglaVerificar : reglas) {
                List<PreguntaRegla> reglaPreguntas = reglaDao.listarPreguntaRegla(reglaVerificar.getCodigo());
                reglaVerificar.setPreguntas(reglaPreguntas);
                validarReglaRepetida(reglaVerificar, regla);
            }

            /**
             * 4.2.1.5.	El sistema graba las reglas
             */
            log.info("El sistema graba las reglas");
            reglaDao.ingresar(regla);
            log.info("El sistema grabo las reglas");

            /**
             * 4.2.1.6.	El sistema muestra mensaje “La regla se agregó
             * satisfactoriamente” con el listado de reglas actualizado.
             */
            return MessageFormat.format(Mensaje.AGREGAR_REGLA, new Object[]{regla.getCodigo()});
        } catch (ValidatorException e) {
            throw e;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.MANTENIMIENTO_REGLA, e, regla.getCodigo());
        }
    }

    @Override
    public String actualizarRegla(Regla regla) throws ExpertoServiceException {
        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            log.info("El sistema valida la seleccion de al menos una pregunta");
            if (regla.getPreguntas() == null || regla.getPreguntas().isEmpty()) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.PREGUNTA_REQUERIDO);
            }

            /**
             * 4.2.1.4.	El sistema valida la definición de la regla
             */
            validarPreguntaRepetida(regla);

            /**
             * El sistema valida que seleccione un perfil
             */
            if (regla.getPerfil() == null || regla.getPerfil().getCodigo() == null) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.PERFIL_REQUERIDO);
            }

            /**
             * 4.3.2.	Regla repetida
             */
            log.info("El sistema valida la repeticion de una regla");
            List<Regla> reglas = reglaDao.listar();
            for (Regla reglaVerificar : reglas) {
                if (!reglaVerificar.getCodigo().equals(regla.getCodigo())) {
                    List<PreguntaRegla> reglaPreguntas = reglaDao.listarPreguntaRegla(reglaVerificar.getCodigo());
                    reglaVerificar.setPreguntas(reglaPreguntas);
                    validarReglaRepetida(reglaVerificar, regla);
                }
            }

            String msg;
            try {
                /**
                 * 4.2.2.8.	El sistema graba los cambios de la regla
                 */
                reglaDao.actualizar(regla);
                log.info("El sistema grabo los cambios de la regla");

                /**
                 * 4.2.1.6.	El sistema muestra mensaje “La Regla se actualizó
                 * satisfactoriamente” con el listado de reglas actualizado.
                 */
                msg = MessageFormat.format(Mensaje.MODIFICAR_REGLA, new Object[]{regla.getCodigo()});
            } catch (ForeignKeyException e) {
                log.warn("El sistema deshabilitara la regla y crea una con codigo diferente, debido a que se encontraba en uso");
                reglaDao.deshabilitar(regla);
                reglaDao.ingresar(regla);
                msg = MessageFormat.format(Mensaje.DESHABILITAR_MODIFICAR_REGLA, new Object[]{regla.getCodigo()});
            }

            return msg;
        } catch (ValidatorException e) {
            throw e;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.MANTENIMIENTO_REGLA, e, regla.getCodigo());
        }
    }

    @Override
    public String eliminarRegla(String codigoRegla) throws ExpertoServiceException {
        ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();
        try {

            /**
             * 4.2.3.4.	El sistema elimina el registro de la regla
             */
            Regla regla = reglaDao.obtener(codigoRegla);

            if (regla == null) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.MANTENIMIENTO_REGLA);
            }
            String msg;
            try {
                /**
                 * 4.3.6.1.	si la regla a eliminar se encuentra utilizada, el
                 * sistema deshabilita la regla
                 */
                log.info("El sistema eliminara la regla");
                reglaDao.eliminar(regla);
                msg = MessageFormat.format(Mensaje.ELIMINAR_REGLA, new Object[]{regla.getCodigo()});
            } catch (ForeignKeyException e) {
                log.warn("El sistema deshabilitara la regla debido a que se encontraba en uso");
                reglaDao.deshabilitar(regla);
                msg = MessageFormat.format(Mensaje.DESHABILITAR_REGLA, new Object[]{regla.getCodigo()});
            }

            return msg;
        } catch (ValidatorException e) {
            throw e;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.MANTENIMIENTO_REGLA, e, codigoRegla);
        }
    }

    @Override
    public List<Pregunta> listarPregunta() throws ExpertoServiceException {
        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            List<Pregunta> listado = reglaDao.listarPregunta();

            if (listado == null || listado.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_PREGUNTA);
            }
            log.info("Listado  obtenidas [tamanio={}]", new Object[]{listado.size()});

            return listado;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_PREGUNTAS, e);
        }
    }

    @Override
    public List<Perfil> listarPerfil() throws ExpertoServiceException {
        try {
            ReglaDAO reglaDao = SingletonDAOFactory.getDAOFactory().getReglaDAO();

            List<Perfil> listado = reglaDao.listarPerfil();

            if (listado == null || listado.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_PERFIL);
            }
            log.info("Listado  obtenidas [tamanio={}]", new Object[]{listado.size()});

            return listado;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_PERFILES, e);
        }
    }

}
