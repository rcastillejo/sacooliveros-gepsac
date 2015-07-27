/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.listener;

import com.novatronic.pscabas.core.socket.ServerSocketSCA;
import com.novatronic.pscabas.core.util.ConstantsLoad;
import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author msalvatierra
 */
public class SocketListener implements ServletContextListener {

    public static Logger logger = org.apache.log4j.Logger.getLogger(SocketListener.class);
    private ServerSocketSCA socket;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String basedir = System.getenv("sixconfig");
        //String basedir = System.getenv("SIXCFG");
        String scaDir = basedir + File.separatorChar + "SIXSCA";
        logger.info("Agregando SCA Dir=[{}]"+ scaDir);
      
        logger.info("contextInitialized........");
        ConstantsLoad.load();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Deteniendo el sockeListener........");
        socket.close();
       
    }
}
