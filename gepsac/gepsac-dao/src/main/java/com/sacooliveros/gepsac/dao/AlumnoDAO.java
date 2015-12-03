/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.experto.Alumno;

/**
 *
 * @author rcastillejo
 */
public interface AlumnoDAO extends BaseDao<Alumno> {

    /*List<EstrategiaActividad> listarActividad(String id);
     List<Indicador> listarIndicador(String estrategiaId, String actividadId);*/
    void ingresarPostulante(Alumno model);

    void actualizarPostulante(Alumno model);
    
    void grabarPostulante(Alumno model);

    Alumno obtenerPostulante(String id);
    
    
    void cargarCodificacionAlumno(Alumno alumno);
    
}
