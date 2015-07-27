/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.listener;

import com.novatronic.sca.dao.DAOFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author msalvatierra
 */
public class ReporteAppListener implements ServletContextListener {

    public static Logger log = LoggerFactory.getLogger(ReporteAppListener.class);
    private static final String WHICH_FACTORY_PARAM = "com.novatronic.sca.dao.factory";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String whichFac;
        ServletContext sc;
        
        log.info("Configurando Aplicacion ...");  
        
        sc = sce.getServletContext();

        whichFac = sc.getInitParameter(WHICH_FACTORY_PARAM);
        log.debug("Configurando DaoFactory[{}] ...", whichFac);
        DAOFactory.init(Integer.parseInt(whichFac));      
        log.info("Configurado DaoFactory");
        
        log.info("Aplicacion configurada");        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Liberando recursos de la Aplicacion ...");  
        
        log.info("Recusos liberados de la Aplicacion");        
       
    }
}
