/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.util;

import com.sacooliveros.gepsac.model.comun.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marco
 */
public final class ActionUtil {

    public static Logger logger = LoggerFactory.getLogger(ActionUtil.class);

    private ActionUtil() {
    }

    public static void enviarJson(String json, HttpServletResponse response) {
        try {
            response.setContentType(Constantes.CONTENT_TYPE);
            response.setHeader(Constantes.HEADER_PRO_CACHE,
                    Constantes.HEADER_CACHE);
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String obtenerNombreUsuarioLogeado(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        String username = (String) httpSession.getAttribute(Config.USERNAME_SESSION);
        return username == null ? Config.USERNAME_DEMO : username;
    }

    public static Usuario obtenerUsuarioLogeado(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        Usuario user = (Usuario) httpSession.getAttribute(Config.USER_SESSION);
        if (user == null) {
            user = new Usuario(Config.USERNAME_DEMO, "Jose", "Perez");
            httpSession.setAttribute(Config.USER_SESSION, user);
        }
        return user;
    }

}
