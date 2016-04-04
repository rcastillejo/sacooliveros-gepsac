/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

/**
 *
 * @author rcastillejo
 */
public interface DAOFactory {
    
    void destroy();

    PlanDAO getPlanEstrategicoDAO();

    EstrategiaDAO getEstrategiaDAO();

    AlumnoDAO getAlumnoDAO();

    EvaluacionPostulanteDAO getEvaluacionPostulanteDAO();

    EvaluacionAcosoEscolarDAO getEvaluacionAcosoEscolarDAO();
    
    SolicitudPsicologicaDAO getSolicitudPsicologicaDAO();
    
    ReglaDAO getReglaDAO();
    
    ConfigDAO getConfigDAO();
}
