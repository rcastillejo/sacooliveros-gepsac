/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ricardo
 */
public class StateUtil {

    private final static Map<String, String> states;

    static {
        states = new HashMap<String, String>();
        states.put(State.EvaluacionAcosoEscolar.POR_RESOLVER, "Por Resolver");
        states.put(State.EvaluacionAcosoEscolar.EVALUADO, "Evaluado");
        states.put(State.EvaluacionAcosoEscolar.RESUELTO, "Resuelto");
    }

    public static String getDescription(String codigo) {
        return states.containsKey(codigo) ? states.get(codigo) : codigo;
    }
}
