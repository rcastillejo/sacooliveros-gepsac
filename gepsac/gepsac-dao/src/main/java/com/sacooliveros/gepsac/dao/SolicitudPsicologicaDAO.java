/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;

/**
 *
 * @author rcastillejo
 */
public interface SolicitudPsicologicaDAO extends BaseDao<SolicitudPsicologica> {

    //void registrarSolicitudPsicologica(SolicitudPsicologica model);

    //void actualizarSolicitudPsicologica(SolicitudPsicologica model);
    
    void grabarSolicitudPsicologica(SolicitudPsicologica model);

    //SolicitudPsicologica obtenerSolicitudPsicologica(String id);
    
    //void eliminarSolicitudPsicologica(SolicitudPsicologica model);
}
