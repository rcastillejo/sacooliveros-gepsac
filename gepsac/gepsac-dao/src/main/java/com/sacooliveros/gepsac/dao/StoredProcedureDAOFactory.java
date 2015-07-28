/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;
 
import com.sacooliveros.gepsac.dao.exception.DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author rcastillejo
 */
public class StoredProcedureDAOFactory extends DAOFactory {

//    private final CoreStoredProcedure instancia; 

    public void createCompBdDao(String config) throws DAOException  {
        
        // BaseDao dao;
        Properties param;

//        instancia = new CoreStoredProcedure(driver, "", dsJndi);
        //instancia.
        //instancia= new CoreStoredProcedure(driver, dsJndi);
    } 

//    @Override
//    public PlanEstrategicoDAO getTransaccionDAO() {
////        return new TransaccionSpDAO(instancia);
//    }
    @Override
    public PlanEstrategicoDAO getPlanEstrategicoDAO() {

        return null;
    }

}
