/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.listener;

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
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc;
        
        log.info("Configurando Aplicacion ...");  
        
        sc = sce.getServletContext();
        
        
        log.info("Aplicacion configurada");        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Liberando recursos de la Aplicacion ...");  
        
        log.info("Recusos liberados de la Aplicacion");        
       
    }
}
