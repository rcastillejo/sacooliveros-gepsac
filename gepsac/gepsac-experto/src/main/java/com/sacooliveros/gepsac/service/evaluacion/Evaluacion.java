/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.evaluacion;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface Evaluacion {

    interface Mensaje {

        String EVALUAR_ALUMNO_POSTULANTE = "La evaluación fue realizada con éxito [{0}]";
        String EVALUAR_ACOSO_ESCOLAR = "Evaluación realizada satisfactoriamente [{0}]";
        String RESOLVER_ACOSO_ESCOLAR = "Evaluación fue finalizada con éxito [{0}]";

        String REGISTRO_SOLICITUD_PSICOLOGICA = "Solicitud Psicológica Registrado Satisfactoriamente [{0}]";
        String VERIFICAR_SOLICITUD_PSICOLOGICA = "Solicitud Psicológica Paso se actulizo al estado [{0}]";
        String EDITAR_SOLICITUD_PSICOLOGICA = "Solicitud Psicológica Editado Satisfactoriamente [{0}]";
        String ELIMINAR_SOLICITUD_PSICOLOGICA = "Solicitud Psicológica Eliminado Satisfactoriamente [{0}]";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "ERR099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  evaluación [{0}]";
            String LISTAR = "No se encuentra planes";
            String EVALUAR = "Error al evaluar alumno postulante [{0}]";
            String NO_EXISTE_SOLICITUD_PSICOLOGICA = "No existen solicitudes psicologicas";
            String NO_EXISTE_SOLICITUD_PSICOLOGICA_USUARIO = "No existen solicitudes psicologicas registradas por el usuario";
            String NO_EXISTE_EVALUACION_ACOSO_ESCOLAR = "No existen evaluaciones de acoso escolar [{0}]";
            String LISTAR_EVALUACIONES_ACOSO_ESCOLAR = "Error al consultar evaluaciones de acoso escolar";
            String EVALUAR_RESPUESTA_ACOSO_ESCOLAR = "Error al evaluar las respuesta de acoso escolar [{0}]";
            String GENERAR_EXPLICACION_ACOSO_ESCOLAR = "Error al generar explicacion de las respuesta de acoso escolar [{0}]";
            String CARGAR_REGLAS_ACOSO_ESCOLAR = "No se pudo cargar las reglas de acoso escolar";
            String RESOLVER_ACOSO_ESCOLAR = "No se pudo registrar las respuestas";
            String RESOLVER_PREGUNTA_ACOSO_ESCOLAR = "Debe seleccionar una respuesta a la pregunta Nro {0}";
            String NO_ELIMINAR_SOLICITUD_PSICOLOGICA = "La Solicitud no se encuentra disponible";
            String NO_EDITAR_SOLICITUD_PSICOLOGICA = "La Solicitud no se encuentra disponible";

            String REGISTRAR_SOLICITUD_PSICOLOGICA = "Error al registrar la solicitud psicologica [{0}]";
            String ELIMINAR_SOLICITUD_PSICOLOGICA = "Error al eliminar la solicitud psicologica [{0}]";
            String EDITAR_SOLICITUD_PSICOLOGICA = "Error al editar la solicitud psicologica [{0}]";

        }
    }

    List<SolicitudPsicologica> listarSolicitudPsicologica() throws ExpertoServiceException;

    List<SolicitudPsicologica> listarSolicitudPsicologica(String codigoUsuario) throws ExpertoServiceException;

    String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) throws ExpertoServiceException;

    String editarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) throws ExpertoServiceException;

    String eliminarSolicitudPsicologica(String codigoSolicitudPsicologica) throws ExpertoServiceException;

    SolicitudPsicologica obtenerEditarSolicitudPsicologica(String codigoSolicitudPsicologica) throws ExpertoServiceException;

    SolicitudPsicologica consultarSolicitudPsicologica(String codigoSolicitudPsicologica) throws ExpertoServiceException;

    String verificarSolicitudPsicologicaPendiente(SolicitudPsicologica solicitudPsicologica) throws ExpertoServiceException;

    String resolverAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar) throws ExpertoServiceException;

}
