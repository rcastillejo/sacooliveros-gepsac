/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.dao;

import com.novatronic.estandares.helper.ResourceHelper;
import com.novatronic.pscabas.core.util.SCAConstants;
import com.pe.nova.components.bd.BDConnectionFactory;
import com.pe.nova.components.bd.exception.BDException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author rcastillejo
 */
public class StoredProcedureDAOFactory extends DAOFactory {

//    private final CoreStoredProcedure instancia;
    private static final String driver = SCAConstants.DRIVER_NAME_POSTGRES;
    private static final String pckg = "bcamovil";
    private static final String dsJndi = "java:/jdbc/scabas";

    public void createCompBdDao(String config) throws BDException, IOException {
        BDConnectionFactory connection;
        // BaseDao dao;
        Properties param;

//        instancia = new CoreStoredProcedure(driver, "", dsJndi);
        //instancia.
        //instancia= new CoreStoredProcedure(driver, dsJndi);
    }

    private static Properties readConfig(String configfile) throws IOException {
        Properties config = new Properties();
        URL url = ResourceHelper.find(configfile);
        config.load(url.openStream());
        return config;
    }

//    @Override
//    public TransaccionDAO getTransaccionDAO() {
////        return new TransaccionSpDAO(instancia);
//    }
    @Override
    public TransaccionDAO getTransaccionDAO() {

        return new TransaccionSpDAO();
    }

}
