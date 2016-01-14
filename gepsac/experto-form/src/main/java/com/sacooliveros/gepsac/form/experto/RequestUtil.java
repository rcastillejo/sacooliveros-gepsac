/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.form.experto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricardo
 */
public class RequestUtil {
    
    public static String getUsernameLogin(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        String username = (String) httpSession.getAttribute(Config.USERNAME_SESSION);
        return username == null ? Config.USERNAME_DEMO : username;
    }
}
