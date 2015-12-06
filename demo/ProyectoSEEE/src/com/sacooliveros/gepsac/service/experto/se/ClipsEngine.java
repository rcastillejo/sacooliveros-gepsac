/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class ClipsEngine implements Engine<Pregunta, ResultadoInferencia> {

    private final Environment clips;

    public ClipsEngine() {
        try {
            clips = new Environment();
            System.out.println("VERSION CLIPS JNDI  ["+Environment.getCLIPSJNIVersion()+"]");
            System.out.println("VERSION CLIPS       ["+Environment.getCLIPSVersion()+"]");
            System.out.println("VERSION             ["+Environment.getVersion()+"]");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar el entorno", e);
        }
    }

    @Override
    public void loadRules(String config) {
        try {
            clips.load(config);
        } catch (Exception e) {
            throw new RuntimeException("No se cargaron los reglas", e);
        }
    }

    @Override
    public void loadFacts(String config) {
        boolean loaded;
        try {
            loaded = clips.loadFacts(config);
            System.out.println("Hechos cargados [" + loaded + "]");
        } catch (Exception e) {
            throw new RuntimeException("No se cargaron los hechos", e);
        }
        if (!loaded) {
            throw new RuntimeException("No se cargaron los hechos");
        }
    }

    @Override
    public ResultadoInferencia evaluate(List<Pregunta> preguntasResultas) {
        ResultadoInferencia inferencia = inferir();

        while (!inferencia.finalizo()) {
            String respuesta = obtenerRespuesta(preguntasResultas, inferencia.getNombre());
            System.out.println("evaluando respuesta [" + respuesta + "]");
            clips.eval("(assert (opcion " + respuesta + "))");
            inferencia = inferir();
        }

        return inferencia;
    }

    private String obtenerRespuesta(List<Pregunta> preguntasResultas, String alias) {
        Pregunta pregunta;
        for (Pregunta preguntasResulta : preguntasResultas) {
            if (preguntasResulta.getAlias().equals(alias)) {
                pregunta = preguntasResulta;
                return pregunta.getRespuesta();
            }
        }
        return null;
    }

    private ResultadoInferencia inferir() {

        clips.run();

        String evaluar = "(find-all-facts ((?x pregunta-respuesta )) TRUE)";
        PrimitiveValue value = clips.eval(evaluar);
        try {
            String alias = value.get(0).getFactSlot("nombre").toString();
            String tipo = value.get(0).getFactSlot("tipo").toString();

            ResultadoInferencia resultado = new ResultadoInferencia();
            resultado.setNombre(alias);
            resultado.setTipo(tipo);
            System.out.println("resultado inferencia[" + resultado + "]");

            if (resultado.finalizo()) {
                String respuesta = value.get(0).getFactSlot("texto").toString();
                resultado.setConclusion(respuesta);
            }
            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el primer enunciado", e);
        }
    }

    @Override
    public void reset() {
        if (clips != null) {
            clips.reset();
        }
    }

}
