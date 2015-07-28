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
public class MockDAOFactory extends DAOFactory {

    private static MockDAOFactory instance;
    private final PlanEstrategicoMockDAO planEstrategicoDao;

    public static MockDAOFactory getInstance() {
        if (instance == null) {
            instance = new MockDAOFactory();
        }
        return instance;
    }

    public MockDAOFactory() {
        planEstrategicoDao = new PlanEstrategicoMockDAO();
    }

    @Override
    public PlanEstrategicoDAO getPlanEstrategicoDAO() {
        return planEstrategicoDao;
    }

}
