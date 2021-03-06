/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.evaluacion.SolicitudAlumno;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import java.util.List;

/**
 *
 * @author rcastillejo
 */
public interface SolicitudPsicologicaDAO extends BaseDao<SolicitudPsicologica> {

    //void registrarSolicitudPsicologica(SolicitudPsicologica model);

    //void actualizarSolicitudPsicologica(SolicitudPsicologica model);
    void actualizarEstado(SolicitudPsicologica model);
    
    List<SolicitudPsicologica> listarPorEstado(String codigoEstado);
    List<SolicitudPsicologica> listarPendiente(int maxMinutes);
    
    List<SolicitudAlumno> listarAlumno(String codigoSolicitud);
    
    //void grabarSolicitudPsicologica(SolicitudPsicologica model);

    int cantidadSolicitudPsicologicaAlumnoInvolucrado(String alumnoInvolucradoCodigo);
    //SolicitudPsicologica obtenerSolicitudPsicologica(String id);
    
    //void eliminarSolicitudPsicologica(SolicitudPsicologica model);
}
