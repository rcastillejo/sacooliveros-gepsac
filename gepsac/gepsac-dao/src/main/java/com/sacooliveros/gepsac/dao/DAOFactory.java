/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.dao.exception.DAOException;

/**
 *
 * @author rcastillejo
 */
public abstract class DAOFactory {

    public static final int SP = 1;
    public static final int MOCK = 2;

    private static int whichFactory;

    public static void init(String whichFac) {
        try {
            init(Integer.parseInt(whichFac));
        } catch (Exception e) {
            throw new DAOException("Error al identificar el factory: " + whichFac, e);
        }
    }

    public static void init(int whichFac) {
        whichFactory = whichFac;
    }

    public static DAOFactory getDAOFactory() throws DAOException {
        switch (whichFactory) {
            case SP:
                return new StoredProcedureDAOFactory();
            case MOCK:
                return MockDAOFactory.getInstance();
            default:
                return new StoredProcedureDAOFactory();
        }
    }

    public abstract PlanEstrategicoDAO getPlanEstrategicoDAO();
}
