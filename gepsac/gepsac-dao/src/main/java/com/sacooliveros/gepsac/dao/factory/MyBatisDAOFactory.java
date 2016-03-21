/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.factory;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.EstrategiaDAO;
import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.mybatis.session.SessionFactory;
import com.sacooliveros.gepsac.dao.myibatis.AlumnoMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.EstrategiaMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.EvaluacionAcosoEscolarMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.EvaluacionPostulanteMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.PlanMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.ReglaMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.SolicitudPsicologicaMyIbatisDAO;

/**
 *
 * @author rcastillejo
 */
public class MyBatisDAOFactory extends SessionFactory implements DAOFactory {

    public MyBatisDAOFactory() {
    }

    public MyBatisDAOFactory(String resource) {
        super(resource);
    }
    
    @Override
    public PlanDAO getPlanEstrategicoDAO() {
        return new PlanMyIbatisDAO(getSqlSessionFactory());
    }

    @Override
    public EstrategiaDAO getEstrategiaDAO() {
        return new EstrategiaMyIbatisDAO(getSqlSessionFactory());
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new AlumnoMyIbatisDAO(getSqlSessionFactory());
    }

    @Override
    public EvaluacionPostulanteDAO getEvaluacionPostulanteDAO() {
        return new EvaluacionPostulanteMyIbatisDAO(getSqlSessionFactory());
    }

    @Override
    public EvaluacionAcosoEscolarDAO getEvaluacionAcosoEscolarDAO() {
        return new EvaluacionAcosoEscolarMyIbatisDAO(getSqlSessionFactory());
    }
    
    @Override
    public SolicitudPsicologicaDAO getSolicitudPsicologicaDAO() { 
        return new SolicitudPsicologicaMyIbatisDAO(getSqlSessionFactory());
    }
    
    @Override
    public ReglaDAO getReglaDAO() { 
        return new ReglaMyIbatisDAO(getSqlSessionFactory());
    }
    
}
