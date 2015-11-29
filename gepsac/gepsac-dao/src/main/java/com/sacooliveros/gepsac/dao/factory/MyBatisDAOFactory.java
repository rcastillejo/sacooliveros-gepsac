/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.factory;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.EstrategiaDAO;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.dao.myibatis.AlumnoMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.EstrategiaMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.EvaluacionPostulanteMyIbatisDAO;
import com.sacooliveros.gepsac.dao.myibatis.PlanMyIbatisDAO;

/**
 *
 * @author rcastillejo
 */
public class MyBatisDAOFactory extends DAOFactory {

    @Override
    public PlanDAO getPlanEstrategicoDAO() {
        return new PlanMyIbatisDAO();
    }

    @Override
    public EstrategiaDAO getEstrategiaDAO() {
        return new EstrategiaMyIbatisDAO();
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new AlumnoMyIbatisDAO();
    }

    @Override
    public EvaluacionPostulanteDAO getEvaluacionPostulanteDAO() {
        return new EvaluacionPostulanteMyIbatisDAO();
    }

}
