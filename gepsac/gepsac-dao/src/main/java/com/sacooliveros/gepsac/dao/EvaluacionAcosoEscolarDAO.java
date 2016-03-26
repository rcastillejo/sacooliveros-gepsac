/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
import com.sacooliveros.gepsac.model.experto.Regla;
import java.util.List;

/**
 *
 * @author rcastillejo
 */
public interface EvaluacionAcosoEscolarDAO extends BaseDao<EvaluacionAcosoEscolar> {

    /*List<EstrategiaActividad> listarActividad(String id);
     List<Indicador> listarIndicador(String estrategiaId, String actividadId);
    void ingresarPostulante(Alumno model);

    void actualizarPostulante(Alumno model);
    
    void grabarPostulante(Alumno model);

    Alumno obtenerPostulante(String id);*/
     String getCodigo();
    
     void ingresar(EvaluacionAcosoEscolar evaluacion, List<PreguntaEvaluacionAlternativa> preguntasAlternativas);
     
     List<EvaluacionAcosoEscolar> listarEvaluacionPorEstado(String codigoEstado);
     List<EvaluacionAcosoEscolar> listarEvaluacionEvaluadoResuelto();
     
     List<PreguntaEvaluacion> listarPreguntaEvaluacion(String codigoEvaluacion);
     
     List<PreguntaEvaluacionAlternativa> listarPreguntaEvaluacionAlternativa(String codigoEvaluacion,String codigoPregunta);
     
     List<PreguntaEvaluacion> listarPreguntaAfirmativa(String codigoEvaluacion);
     
     void actualizarRespuestaEvaluacion(EvaluacionAcosoEscolar evaluacion, List<Regla> reglasActivas);
     
     void registrarRespuestaEvaluacion(EvaluacionAcosoEscolar evaluacion);
     
     EvaluacionAcosoEscolar obtenerDesdePlantillaVigente();
     
     List<PreguntaEvaluacionAlternativa> obtenerPreguntaDesdePlantilla(String codigoPlantilla);
}
