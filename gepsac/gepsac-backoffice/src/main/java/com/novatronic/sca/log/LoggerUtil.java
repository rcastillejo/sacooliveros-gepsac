/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.log;

import com.novatronic.sca.util.Config;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.slf4j.Logger;

/**
 *
 * @author rcastillejo
 */
public class LoggerUtil {

    public static void error(Logger logger, String accion, String modulo, Calendar calendar, String usuario, String mensaje, Throwable e) {
        SimpleDateFormat sdf = new SimpleDateFormat(Config.DATE_FORMAT);
        SimpleDateFormat stf = new SimpleDateFormat(Config.TIME_FORMAT);
        logger.error("[acción={}, módulo={}, fecha={}, hora={}, usuario={}], {}",
                new Object[]{accion, modulo, sdf.format(calendar.getTime()), stf.format(calendar.getTime()), usuario, mensaje},
                e);
    }

}
