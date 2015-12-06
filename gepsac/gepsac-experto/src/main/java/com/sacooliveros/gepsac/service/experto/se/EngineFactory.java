/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

/**
 *
 * @author Ricardo
 */
public class EngineFactory {

    private static final String RULES_CONFIG = "SEEE.CLP";
    private static final String FACTS_CONFIG = "EE.dat";

    public static Engine create() {
        return create(RULES_CONFIG, FACTS_CONFIG);
    }

    public static Engine create(String rulesConfig, String factsConfig) {
        Engine instancia;
        instancia = new ClipsEngine();
        instancia.loadRules(rulesConfig);
        //instancia.loadFacts(factsConfig);
        return instancia;
    }

}
