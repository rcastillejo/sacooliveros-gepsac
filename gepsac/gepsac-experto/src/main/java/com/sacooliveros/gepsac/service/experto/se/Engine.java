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
public interface Engine<F, P> {

    /**
     * Cargar el conjunto de reglas (base de conocimieno)
     *
     * @param ruleConfig Archivo de configuracion de reglas (base de
     * conocimiento)
     */
    void loadRules(String ruleConfig);

    /**
     * Cargar el conjunto de hechos (base de hechos)
     *
     * @param factConfig Archivo de configuracion de preguntas (base de hechos)
     */
    void loadFacts(String factConfig);

    /**
     * Ejecutar el motor de inferencia en base a la aplicacion de las reglas
     * sobre los hechos presentes
     *
     * @param facts hHechos presentados para la inferencia.
     * @return Conclusiones obtenidos por la inferencia de las reglas y hechos
     */
    P evaluate(List<F> facts);
    
    void reset();
}
