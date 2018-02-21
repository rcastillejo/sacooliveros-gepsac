/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;

/**
 *
 * @author Ricardo
 */
public class Engines {

    private static final String RULES_CONFIG = "SEEE.CLP";
    private static final String FACTS_CONFIG = "EE.dat";

    public static Engine create() {
        return create(RULES_CONFIG, FACTS_CONFIG);
    }

    public static Engine create(String rulesConfig, String factsConfig) {
        Engine instancia;
        try {
            instancia = new DataBaseEngine(); //new ClipsEngine();
            instancia.loadRules(rulesConfig);
            instancia.loadFacts(factsConfig);
            return instancia;
        }catch (ValidatorException e) {
            throw e;
        }catch (ExpertoServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, Experto.Error.Mensaje.CARGAR_REGLAS_ACOSO_ESCOLAR, e);
        }
    }

}
