/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.factory.MockDAOFactory;
import com.sacooliveros.gepsac.dao.factory.MyBatisDAOFactory;

/**
 *
 * @author Ricardo
 */
public class SingletonDAOFactory {

    public static final int MY_IBATIS = 1;
    public static final int MOCK = 2;
    private static DAOFactory factory;

    public static void init(String whichFac) {
        try {
            init(Integer.parseInt(whichFac));
        } catch (Exception e) {
            throw new DAOException("Error al identificar el factory: " + whichFac, e);
        }
    }

    public static void init(int whichFac) throws DAOException {
        switch (whichFac) {
            case MY_IBATIS:
                factory = new MyBatisDAOFactory();
                break;
            case MOCK:
                factory = MockDAOFactory.getInstance();
                break;
            default:
                throw new DAOException("Fabrica DAO invalido [" + whichFac + "]");
        }
    }

    public static DAOFactory getDAOFactory() {
        return factory;
    }

    public static void destroy() throws DAOException {
        if(factory != null){
            factory.destroy();
        }
    }
}
