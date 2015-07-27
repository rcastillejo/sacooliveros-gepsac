/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.dao;

import com.novatronic.sca.dao.exception.DAOException;
import com.pe.nova.components.bd.exception.BDException;
import java.io.IOException;

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

    public static DAOFactory getDAOFactory() throws IOException, BDException {
        switch (whichFactory) {
            case SP:
                return new StoredProcedureDAOFactory();
            case MOCK:
                return new MockDAOFactory();
            default:
                return new StoredProcedureDAOFactory();
        }
    }

    public abstract TransaccionDAO getTransaccionDAO();
}
