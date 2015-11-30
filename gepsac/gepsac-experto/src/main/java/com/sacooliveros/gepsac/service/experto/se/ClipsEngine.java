/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class ClipsEngine implements Engine<PreguntaEvaluacion, ResultadoInferencia> {

    private final Environment clips;

    public ClipsEngine() {
        try {
            clips = new Environment();
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
    public ResultadoInferencia evaluate(List<PreguntaEvaluacion> preguntasResueltas) {
        ResultadoInferencia inferencia = inferir();

        while (!inferencia.finalizo()) {
            String respuesta = obtenerRespuesta(preguntasResueltas, inferencia.getNombre());
            System.out.println("evaluando respuesta [" + respuesta + "]");
            clips.eval("(assert (opcion " + respuesta + "))");
            inferencia = inferir();
        }

        return inferencia;
    }

    private String obtenerRespuesta(List<PreguntaEvaluacion> preguntasResultas, String alias) {
        for (PreguntaEvaluacion preguntasResulta : preguntasResultas) {
            Pregunta pregunta = preguntasResulta.getPregunta();
            if (pregunta.getAlias().equals(alias)) {
                return preguntasResulta.getRespuesta();
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
