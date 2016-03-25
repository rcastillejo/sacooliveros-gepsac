/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.evaluacion;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.comun.Usuario;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudAlumno;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.util.State;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
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
    private static final int MOTIVO_AGRESION_ESCOLAR = 1;
//    private static final String MOTIVO_COMPORTAMIENTO_SOSPECHOSO="CS";
//    private static final String MOTIVO_BAJO_RENDIMIENTO="BR";
    private static final int CANTIDAD_SOLICITUD_ALUMNO_INVOLUCRADO_MAXIMO_ANUAL = 1;

    @Override
    public List<SolicitudPsicologica> listarSolicitudPsicologica() throws ExpertoServiceException {
        try {
            SolicitudPsicologicaDAO solicitudPsicologicaDao = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
            List<SolicitudPsicologica> solicitudes = solicitudPsicologicaDao.listar();
            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_SOLICITUD_PSICOLOGICA);
            }
            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{null, solicitudes.size()});
            return solicitudes;
        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e);
        }
    }

    @Override
    public List<SolicitudPsicologica> listarSolicitudPsicologica(String codigoUsuario) throws ExpertoServiceException {
        try {
            List<SolicitudPsicologica> solicitudes = listarSolicitudPsicologica();
            for (SolicitudPsicologica solicitud : solicitudes) {
                Usuario solicitante = solicitud.getSolicitante();
                if (!solicitante.getCodigo().equals(codigoUsuario)) {
                    solicitudes.remove(solicitud);
                }
            }
            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_SOLICITUD_PSICOLOGICA_USUARIO);
            }
            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{null, solicitudes.size()});
            return solicitudes;
        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e);
        }
    }

    
    private String getCodigoDocumento(String codigo) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        return codigo + sdf.format(new Date());
    }
    
    @Override
    public String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) throws ExpertoServiceException {
        try {
            String mensaje;
            SolicitudPsicologicaDAO solicitudPsicologicaDAO = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
            AlumnoDAO alumnoDao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();

            solicitudPsicologica.determinarAlumnos();

            log.debug("Validando datos de la solicitud [{}]", solicitudPsicologica);
            validarSolicitudPsicologica(solicitudPsicologica);
            
            //Grabar alumnos Involucrados
            
            for (SolicitudAlumno solicitudAlumno : solicitudPsicologica.getAlumnoInvolucrado()) {
                Alumno alumno = solicitudAlumno.getAlumno();
                alumno.setCodigoEstado(State.Alumno.REGISTRADO);
                alumnoDao.cargarCodificacionAlumno(alumno); 
                alumnoDao.grabarEvaluado(alumno);
            }
            

            solicitudPsicologica.setFechaSolicitud(new Date());            
            String codigo = getCodigoDocumento("SP");
            solicitudPsicologica.setCodigo(codigo);
            solicitudPsicologica.setCodigoEstado(State.SolicitudPsicologica.PENDIENTE);
            //Grabar solicitud psicologica
            solicitudPsicologicaDAO.grabarSolicitudPsicologica(solicitudPsicologica);
            if (solicitudPsicologica.getMotivo() == MOTIVO_AGRESION_ESCOLAR) {
                validarAlmunosInvolucrados(solicitudPsicologica.getAlumnoInvolucrado());
            }
            log.debug("Alumno postulante grabado[{}]", solicitudPsicologica);

            mensaje = "Solicitud Psicológica Registrado Satisfactoriamente";
            return mensaje;

        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Experto.Error.Mensaje.REGISTRAR_SOLICITUD_PSICOLOGICA, e, solicitudPsicologica.getCodigo());
        }
    }

    private void validarAlmunosInvolucrados(List<SolicitudAlumno> alumnoInvolucrado) {
        for (SolicitudAlumno alumno : alumnoInvolucrado) {
            validarAlumnoInvolucrado(alumno.getAlumno());
        }

    }

    private void validarAlumnoInvolucrado(Alumno alumnoInvolucrado) {
        SolicitudPsicologicaDAO solicitudPsicologicaDao = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
        int numeroVeces = 0;
        numeroVeces = solicitudPsicologicaDao.cantidadSolicitudPsicologicaAlumnoInvolucrado(alumnoInvolucrado.getCodigo());
        if (numeroVeces > CANTIDAD_SOLICITUD_ALUMNO_INVOLUCRADO_MAXIMO_ANUAL) {
            //identifica que ya tiene mas de una solicitud por agresion en el anio actual---->SE ENVIA UNA NOTIFICACION (ALERTA)
            log.info("[{}] Se requerie que el alumno resuelva una evaluacion de acoso escolar", alumnoInvolucrado.getCodigo());
        }
    }

    private void validarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) {
        if (solicitudPsicologica.getSolicitante() == null
                || solicitudPsicologica.getSolicitante().getCodigo() == null || solicitudPsicologica.getSolicitante().getCodigo().isEmpty()) {
            throw new ValidatorException(Error.Codigo.GENERAL, "Se debe ingresar el solicitante de la solicitud psicologica");
        }
        if (solicitudPsicologica.getMotivo() <= 0) {
            throw new ValidatorException(Error.Codigo.GENERAL, "Se debe seleccionar el motivo de la solicitud psicologica");
        }
        if (solicitudPsicologica.getDescripcion() == null || solicitudPsicologica.getDescripcion().isEmpty()) {
            throw new ValidatorException(Error.Codigo.GENERAL, "Se debe ingresar la descripcion de la solicitud psicologica");
        }
        if (!solicitudPsicologica.existeAlumnoDirigido()) {
            throw new ValidatorException(Error.Codigo.GENERAL, "Se debe ingresar un alumno");
        }
        if (solicitudPsicologica.getMotivo() == MOTIVO_AGRESION_ESCOLAR
                && !solicitudPsicologica.existeAlumnosInvolucrados()) {
            throw new ValidatorException(Error.Codigo.GENERAL, "Se debe ingresar al menos un alumno involucrado");
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
            List<PreguntaEvaluacionAlternativa> alternativas = pregunta.getAlternativas();
            int seleccionados = 0;

            for (PreguntaEvaluacionAlternativa alternativa : alternativas) {
                if (alternativa.isSeleccionado()) {
                    seleccionados++;
                }
            }

            if (seleccionados == 0) {
                throw new ValidatorException(Error.Codigo.GENERAL, Error.Mensaje.RESOLVER_PREGUNTA_ACOSO_ESCOLAR, i + 1);
            }
        }
    }

}
