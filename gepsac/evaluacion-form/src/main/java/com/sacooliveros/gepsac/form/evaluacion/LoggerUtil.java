/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.form.evaluacion;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;

/**
 *
 * @author rcastillejo
 */
public class LoggerUtil {

    public static void error(Logger logger, String accion, String modulo, HttpServletRequest request, Exception e) {
        Calendar calendar = Calendar.getInstance();
        String usuario = RequestUtil.getUsernameLogin(request);
        SimpleDateFormat sdf = new SimpleDateFormat(Config.DATE_FORMAT);
        SimpleDateFormat stf = new SimpleDateFormat(Config.TIME_FORMAT);
        logger.error(
                MessageFormat.format("accion={0}, modulo={1}, fecha={2}, hora={3}, usuario={4}, mensaje={5}",
                        new Object[]{accion, modulo, sdf.format(calendar.getTime()), stf.format(calendar.getTime()), usuario, e.getMessage()}),
                e);
    }

}
