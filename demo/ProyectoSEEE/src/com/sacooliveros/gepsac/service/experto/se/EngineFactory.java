/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EngineFactory {

    public static Engine create(String rulesConfig, String factsConfig) {
        Engine instancia;
        try {
            instancia = new ClipsEngine();
            instancia.loadRules(rulesConfig);
            instancia.loadFacts(factsConfig);
            return instancia;
        } catch (Exception e) {
            //throw new ExpertoServiceException("Error al crear la instancia", e);
            throw new RuntimeException(e);
        }
    }

    public static String evaluar(List<Pregunta> preguntasResultas) {
        Engine engine = create("SEEE.CLP", "EE.dat");
        
        ResultadoInferencia resultado = (ResultadoInferencia) engine.evaluate(preguntasResultas);
        System.out.println("Resultado evaluacion:"+resultado);
        return resultado != null ? resultado.getConclusion() : null;
    }
}
