/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.util;

import com.sacooliveros.gepsac.evaluador.Server;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class ResourceHelper {

    private static final Logger log = LoggerFactory.getLogger(ResourceHelper.class);

    public static Properties loadConfig(String configname) {
        Properties config;
        try {
            InputStream filePropiedadesBroker = Server.class.getClassLoader().getResourceAsStream(configname);
            config = new Properties();
            config.load(filePropiedadesBroker);
            filePropiedadesBroker.close();
            return config;
        } catch (Exception e) {
            throw new RuntimeException("No se ha encontrado el archivo de propiedades: " + e.getMessage() + ". Realizaremos down.", e);
        }
    }

}
