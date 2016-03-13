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
            log.debug("Enviroment cargado [{}]", clips);
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No se pudo cargar el entorno", e);
        }
    }

    @Override
    public void loadRules(String config) {
        try {
            clips.load(config);
            log.debug("Reglas Cargadas [{}]");
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
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No cargaron los reglas", e);
        }
        if (!loaded) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No cargaron los reglas");
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
            log.debug("obteniendo respuesta [" + inferencia.getPregunta() + "] preguntas[" + preguntasResueltas + "]");
            PreguntaEvaluacion preguntaResuelta = obtenerRespuesta(preguntasResueltas, inferencia.getPregunta());
            log.debug("respuesta obtenida [" + preguntaResuelta + "]");
            preguntaResuelta.setOrdenEvaluacion(orden);
            preguntaResuelta.setRegla(inferencia.getNombre());

            /**
             * Evalúa la respuesta de acuerdo a las reglas.
             */
            log.debug("evaluando respuesta [" + preguntaResuelta + "]");
            clips.eval("(assert (opcion " + preguntaResuelta.getRespuesta().toLowerCase() + "))");
            inferencia = inferir();
        }

        return inferencia;
    }

    private PreguntaEvaluacion obtenerRespuesta(List<PreguntaEvaluacion> preguntasResueltas, String codigo) {
        PreguntaEvaluacion preguntaEncontrada = null;
        for (PreguntaEvaluacion preguntasResulta : preguntasResueltas) {
            log.trace("Evaluando codigo[{}] con preguntasResulta [{}] ", preguntaEncontrada);
            Pregunta pregunta = preguntasResulta.getPregunta();
            if (pregunta.getCodigo().equals(codigo)) {
                preguntaEncontrada = preguntasResulta;
                break;
            }
        }
        return preguntaEncontrada;
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
            log.debug("Resultado find-all-facts [nombre={}]", value.get(0).getFactSlot("nombre"));
            log.debug("Resultado find-all-facts [tipo={}]", value.get(0).getFactSlot("tipo"));
            log.debug("Resultado find-all-facts [texto={}]", value.get(0).getFactSlot("texto"));
            String nombre = value.get(0).getFactSlot("nombre").toString();
            String tipo = value.get(0).getFactSlot("tipo").toString();
            String texto = value.get(0).getFactSlot("texto").toString();

            ResultadoInferencia resultado = new ResultadoInferencia();
            resultado.setNombre(nombre);
            resultado.setTipo(tipo);

            if (resultado.esConclusion()) {
                if(!texto.equals("nil")){
                    resultado.setConclusion(texto);                    
                }
            } else {
                resultado.setPregunta(texto);
            }
            log.debug("resultado inferencia[" + resultado + "]");
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
