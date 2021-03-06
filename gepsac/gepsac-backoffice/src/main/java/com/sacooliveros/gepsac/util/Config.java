/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.util;

/**
 *
 * @author Ricardo
 */
public interface Config {

    /**
     * Tiempo limite en milisegundos que se espera una respuesta del Web Service
     */
    long TIMEOUT = 15000L;
    String DATE_FORMAT = "dd-MM-YYYY";
    String TIME_FORMAT = "HH:mm:s";
    
    String USERNAME_SESSION = "username";
    String USER_SESSION = "user";
    
    String USERNAME_DEMO = "gepsac-user";
}
