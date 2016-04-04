/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.model.experto.Regla;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.ResultadoInferencia;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface Experto {
    interface Config{
        String MINIMO_ALUMNO_EVALUADOS = "service.cus22.evaluadoMinimo";
    }
    
    interface Mensaje {

        String EVALUAR_ALUMNO_POSTULANTE = "La evaluación fue realizada con éxito [{0}]";
        String EVALUAR_ACOSO_ESCOLAR = "Evaluación realizada satisfactoriamente [{0}]";
        String SOLICITUD_POR_ATENDER = "La Solicitud Psicológica se encuentra por atender [{0}]";
        String CONFIGURAR = "Los cambios se grabaron con éxito [{0}]";
        String PROGRAMAR = "La programación fue satisfactoria [{0}]";
        String AGREGAR_REGLA = "El registro se realizo con exito";
        String MODIFICAR_REGLA = "La modificacion se realizo con exito";
        String ELIMINAR_REGLA = "La eliminacion se realizo con exito";
        String DESHABILITAR_MODIFICAR_REGLA = "Se creó una nueva regla con los cambios solicitados, debido a que se encontraba en uso";
        String DESHABILITAR_REGLA = "La regla ha sido deshabilitada, debido a que se encontraba en uso";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "ERR099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  evaluación [{0}]";
            String LISTAR = "No se encuentra planes";
            String EVALUAR = "Error al evaluar alumno postulante [{0}]";
            String NO_EXISTE_ALUMNO_EVALUADOS = "No existe suficiente información de alumnos evaluados";
            String NO_EXISTE_EVALUACION_ACOSO_ESCOLAR = "No existen evaluaciones de acoso escolar [{0}]";
            String NO_EXISTE_EVALUACION_ACOSO_ESCOLAR_EVALUADO_RESUELTO = "No existen evaluaciones de acoso escolar resuelto o evaluadas";
            
            String SOLICITUD_CON_EVALUACIONES_PENDIENTES_EVALUAR = "Existen Evaluaciones de la Solicitud Psicológica pendientes de Evaluar";
            String LISTAR_EVALUACIONES_ACOSO_ESCOLAR = "Error al consultar evaluaciones de acoso escolar";
            String LISTAR_EVALUACIONES_ACOSO_ESCOLAR_EVALUADO_RESUELTO = "Error al consultar evaluaciones de acoso escolar";
            String OBTENER_REGLA_ACOSO_ESCOLAR = "Error al consultar regla de acoso escolar";
            String LISTAR_REGLAS_ACOSO_ESCOLAR = "Error al consultar reglas de acoso escolar";
            String LISTAR_PREGUNTAS = "Error al consultar preguntas";
            String LISTAR_PERFILES = "Error al consultar perfiles";
            String EVALUAR_RESPUESTA_ACOSO_ESCOLAR = "Error al evaluar las respuesta de acoso escolar [{0}]";
            String GENERAR_EXPLICACION_ACOSO_ESCOLAR = "Error al generar explicacion de las respuesta de acoso escolar [{0}]";
            String CARGAR_REGLAS_ACOSO_ESCOLAR = "No se pudo cargar las reglas de acoso escolar";
            String CONSULTAR_RESULTADO_ACOSO_ESCOLAR = "Error al consultar resultado de la evaluacion de de acoso escolar [{0}]";
            
            String NO_EXISTE_REGLAS_ACOSO_ESCOLAR = "No existen reglas de acoso escolar";
            String NO_EXISTE_REGLA_ACOSO_ESCOLAR = "No existen regla de acoso escolar";
            String PREGUNTAS_REPETIDAS_REGLA = "Preguntas repetidas en una condicion";
            String REGLA_REPETIDA = "La Regla coincide con Nro {0}";
            String PERFIL_REQUERIDO = "Debe seleccionar un perfil";
            String PREGUNTA_REQUERIDO = "Debe seleccionar al menos una pregunta";
            String MANTENIMIENTO_REGLA = "Error al realizar la operación";
            String NO_EXISTE_PREGUNTA = "No existen preguntas";
            String NO_EXISTE_PERFIL = "No existen perfiles";
        }
    }

    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionAlumno) throws ExpertoServiceException;

    //String evaluarRespuestaAcosoEscolar(List<EvaluacionAcosoEscolar> evaluacionesAcosoEscolar);
    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolar(String codigoEstado) throws ExpertoServiceException;
    
    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluadoResuelto() throws ExpertoServiceException;

    String evaluarRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar, Engine<Pregunta, ResultadoInferencia> engine) throws ExpertoServiceException;

    String verificarSolicitudPsicologica(EvaluacionAcosoEscolar evaluacionAcosoEscolar) throws ExpertoServiceException;

    ExplicacionResultado generarExplicacionResultado(String codigoEvaluacion) throws ExpertoServiceException;

    EvaluacionAcosoEscolar consultarResultadoAcosoEscolar(String codigoEvaluacion) throws ExpertoServiceException;

    List<Perfil> listarPerfil() throws ExpertoServiceException;

    List<Regla> listarRegla() throws ExpertoServiceException;

    Regla obtenerRegla(String codigoRegla) throws ExpertoServiceException;

    String agregarRegla(Regla regla) throws ExpertoServiceException;

    String actualizarRegla(Regla regla) throws ExpertoServiceException;

    String eliminarRegla(String codigoRegla) throws ExpertoServiceException;
    
    List<Pregunta> listarPregunta() throws ExpertoServiceException;
}
