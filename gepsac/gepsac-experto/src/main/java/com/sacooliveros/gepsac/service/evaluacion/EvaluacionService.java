/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.evaluacion;

import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class EvaluacionService implements Evaluacion {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionService.class);
    private static final Logger emailLogger = LoggerFactory.getLogger("EMAIL");
    private static final String MOTIVO_AGRESION_ESCOLAR = "AE";
//    private static final String MOTIVO_COMPORTAMIENTO_SOSPECHOSO="CS";
//    private static final String MOTIVO_BAJO_RENDIMIENTO="BR";
    private static final int CANTIDAD_SOLICITUD_ALUMNO_INVOLUCRADO_MAXIMO_ANUAL = 1;

    @Override
    public List<SolicitudPsicologica> listarSolicitudPsicologica() throws ExpertoServiceException {
        try {
            SolicitudPsicologicaDAO solicitudPsicologicaDao = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
            List<SolicitudPsicologica> solicitudes = solicitudPsicologicaDao.listar();
            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_SOLICITUD_PSICOLOGICA);
            }
            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{null, solicitudes.size()});
            return solicitudes;
        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e);
        }
    }

    @Override
    public String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) throws ExpertoServiceException {
        try {
            String mensaje;
            SolicitudPsicologicaDAO solicitudPsicologicaDAO = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
            validarSolicitudPsicologica(solicitudPsicologica);
            //Grabar solicitud psicologica
            solicitudPsicologicaDAO.grabarSolicitudPsicologica(solicitudPsicologica);
            if (solicitudPsicologica.getMotivo().equals(MOTIVO_AGRESION_ESCOLAR)) {
                validarAlmunosInvolucrados(solicitudPsicologica.getAlumnoInvolucrado());
            }
            log.debug("Alumno postulante grabado[{}]", solicitudPsicologica);

            mensaje = "Solicitud Psicológica Registrado Satisfactoriamente";
            return mensaje;

        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Experto.Error.Mensaje.REGISTRAR_SOLICITUD_PSICOLOGICA, e, solicitudPsicologica.getCodigo());
        }
    }

    private void validarAlmunosInvolucrados(List<Alumno> alumnoInvolucrado) {
        for (Alumno alumno : alumnoInvolucrado) {
            validarAlumnoInvolucrado(alumno);
        }

    }

    private void validarAlumnoInvolucrado(Alumno alumnoInvolucrado) {
        SolicitudPsicologicaDAO solicitudPsicologicaDao = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
        int numeroVeces = 0;
        numeroVeces = solicitudPsicologicaDao.cantidadSolicitudPsicologicaAlumnoInvolucrado(alumnoInvolucrado.getCodigo());
        if (numeroVeces > CANTIDAD_SOLICITUD_ALUMNO_INVOLUCRADO_MAXIMO_ANUAL) {
            //identifica que ya tiene mas de una solicitud por agresion en el anio actual---->SE ENVIA UNA NOTIFICACION (ALERTA)
            emailLogger.info("Se requerie resolver la siguiente evaluación, mediante el siguiente enlace <br/> <a href='http://www.google.com'>Evaluacion</a>");
        }
    }

    private void validarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) {
        if (solicitudPsicologica.getSolicitante() == null || 
                solicitudPsicologica.getSolicitante().getCodigo() == null || solicitudPsicologica.getSolicitante().getCodigo().isEmpty()) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar el solicitante de la solicitud psicologica");
        }
        if (solicitudPsicologica.getMotivo() == null || solicitudPsicologica.getMotivo().isEmpty()) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar el motivo de la solicitud psicologica");
        }
        if (solicitudPsicologica.getDescripcion() == null || solicitudPsicologica.getDescripcion().isEmpty()) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar la descripcion de la solicitud psicologica");
        }
        if (solicitudPsicologica.getAlumno() == null || solicitudPsicologica.getAlumno().getCodigo() == null) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar un alumno");
        }
        if (solicitudPsicologica.getAlumnoInvolucrado().isEmpty()) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar al menos un alumno involucrado");
        }
        if (solicitudPsicologica.getFechaSolicitud() == null) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, "Se debe ingresar el solicitante de la solicitud psicologica");
        }
    }

    @Override
    public String resolverAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar) throws ExpertoServiceException {

        try {
            EvaluacionAcosoEscolarDAO evaluacionDao = SingletonDAOFactory.getDAOFactory().getEvaluacionAcosoEscolarDAO();

            validateRespuestaAcosoEscolar(evaluacionAcosoEscolar);

            evaluacionAcosoEscolar.setFechaResuelto(new Date());
            evaluacionAcosoEscolar.setCodigoEstado(Estado.EvaluacionAcosoEscolar.RESUELTO);

            evaluacionDao.registrarRespuestaEvaluacion(evaluacionAcosoEscolar);

            log.info("El sistema graba la evaluación en estado 'Resuelto'");
            return MessageFormat.format(Mensaje.RESOLVER_ACOSO_ESCOLAR, new Object[]{evaluacionAcosoEscolar.getCodigo()});
        } catch (ValidatorException e) {
            throw e;
        } catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.RESOLVER_ACOSO_ESCOLAR, e, evaluacionAcosoEscolar.getCodigo());
        }
    }

    private void validateRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar) {
        List<PreguntaEvaluacion> preguntas = evaluacionAcosoEscolar.getPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            PreguntaEvaluacion pregunta = preguntas.get(i);
            if (pregunta.getRespuesta() == null || pregunta.getRespuesta().isEmpty()) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.RESOLVER_PREGUNTA_ACOSO_ESCOLAR, i + 1);
            }
        }
    }

}
