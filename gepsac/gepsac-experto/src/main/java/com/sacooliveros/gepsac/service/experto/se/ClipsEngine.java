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
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class ClipsEngine implements Engine<PreguntaEvaluacion, ResultadoInferencia> {

    private static final Logger log = LoggerFactory.getLogger(ClipsEngine.class);
    private final Environment clips;

    public ClipsEngine() {
        try {
            clips = new Environment();
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No se pudo cargar el entorno", e);
        }
    }

    @Override
    public void loadRules(String config) {
        try {
            clips.load(config);
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No cargaron los reglas", e);
        }
    }

    @Override
    public void loadFacts(String config) {
        boolean loaded;
        try {
            loaded = clips.loadFacts(config);
            log.debug("Hechos cargados [" + loaded + "]");
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No se cargaron los hechos", e);
        }
        if (!loaded) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No se cargaron los hechos");
        }
    }

    /**
     *
     * @param preguntasResueltas
     * @return
     */
    @Override
    public ResultadoInferencia evaluate(List<PreguntaEvaluacion> preguntasResueltas) {
        ResultadoInferencia inferencia = inferir();
        int orden = 0;
        while (!inferencia.esConclusion()) {
            orden++;

            /**
             * Obtiene la respuesta a evaluar
             */
            PreguntaEvaluacion preguntaResuelta = obtenerRespuesta(preguntasResueltas, inferencia.getNombre());
            preguntaResuelta.setOrdenEvaluacion(orden);

            /**
             * Evalúa la respuesta de acuerdo a las reglas.
             */
            log.debug("evaluando respuesta [" + preguntaResuelta + "]");
            clips.eval("(assert (opcion " + preguntaResuelta.getRespuesta() + "))");
            inferencia = inferir();
        }

        return inferencia;
    }

    private PreguntaEvaluacion obtenerRespuesta(List<PreguntaEvaluacion> preguntasResultas, String alias) {
        for (PreguntaEvaluacion preguntasResulta : preguntasResultas) {
            Pregunta pregunta = preguntasResulta.getPregunta();
            if (pregunta.getAlias().equals(alias)) {
                return preguntasResulta;
            }
        }
        return null;
    }

    /**
     * Evalua una respuesta de una evaluación, para determinar una conclusion
     *
     * @return Resultado de la evaluacion, el tipo determina si llego a una
     * conclusion o necesita evaluar mas preguntas.
     */
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
            log.debug("resultado inferencia[" + resultado + "]");

            if (resultado.esConclusion()) {
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
