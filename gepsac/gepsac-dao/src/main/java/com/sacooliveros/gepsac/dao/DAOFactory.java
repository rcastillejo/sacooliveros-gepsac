/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.dao.factory.MyBatisDAOFactory;
import com.sacooliveros.gepsac.dao.factory.MockDAOFactory;
import com.sacooliveros.gepsac.dao.exception.DAOException;

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
}
