/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.factory;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.ConfigDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.EstrategiaDAO;
import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.dao.PlanMockDAO;
import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;

/**
 *
 * @author rcastillejo
 */
public class MockDAOFactory implements DAOFactory {

    private static MockDAOFactory instance;
    private final PlanMockDAO planEstrategicoDao;

    public static MockDAOFactory getInstance() {
        if (instance == null) {
            instance = new MockDAOFactory();
        }
        return instance;
    }

    public MockDAOFactory() {
        planEstrategicoDao = new PlanMockDAO();
    }

    @Override
    public PlanDAO getPlanEstrategicoDAO() {
        return null;//planEstrategicoDao;
    }

    @Override
    public EstrategiaDAO getEstrategiaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EvaluacionPostulanteDAO getEvaluacionPostulanteDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EvaluacionAcosoEscolarDAO getEvaluacionAcosoEscolarDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SolicitudPsicologicaDAO getSolicitudPsicologicaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReglaDAO getReglaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConfigDAO getConfigDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        instance = null;
    }
}
