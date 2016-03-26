/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class DataBaseEngine implements Engine<PreguntaEvaluacion, ResultadoInferencia> {

    private static final double LIMIT_PERFIL_ACTIVE = 0.5;
    private static final Logger log = LoggerFactory.getLogger(DataBaseEngine.class);
    private final Environment clips;
    private List<Regla> reglas;

    public DataBaseEngine() {
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
            ReglaDAO reglaDAO = SingletonDAOFactory.getDAOFactory().getReglaDAO();
            reglas = reglaDAO.listarReglaActiva();
            for (Regla regla : reglas) {
                for (String codigoPregunta : regla.getSetCodigoPreguntas()) {
                    log.debug("Reglas Cargada [{}] = pregunta [{}]", new Object[]{regla.getCodigo(), codigoPregunta});
                }
            }
            log.debug("Reglas Cargadas [{}]", reglas == null ? 0 : reglas.size());
        } catch (Exception e) {
            throw new ExpertoServiceException(Experto.Error.Codigo.GENERAL, "No cargaron los reglas", e);
        }
    }

    @Override
    public void loadFacts(String config) {

    }

    /**
     * Buscar la pregunta de las evaluaciones mediante codigo de la pregunta
     *
     * @param codigoPregunta
     * @param preguntasResueltas
     * @return
     */
    public PreguntaEvaluacion buscarPreguntaEvaluacion(String codigoPregunta, List<PreguntaEvaluacion> preguntasResueltas) {
        for (PreguntaEvaluacion preguntasResuelta : preguntasResueltas) {

            if (preguntasResuelta.getPregunta().getCodigo().equals(codigoPregunta)) {
                //Pregunta encontrada
                return preguntasResuelta;
            }
        }
        return null;
    }

    public boolean preguntaAfirmativa(PreguntaEvaluacion preguntasResuelta) {
        return preguntasResuelta.getAternativaSeleccionada().getAlternativa().getSecuencia() == 1;
    }

    public String obtenerCodigoPerfil(int reglasActivadas, Map<String, Integer> perfiles) {
        String codigoPerfil = "P0000";
        for (Map.Entry<String, Integer> entry : perfiles.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            double porcentaje = value / (reglasActivadas * 1.00);
            log.info("Porcentaje del perfil [{}={}]", new Object[]{key, porcentaje});
            if (porcentaje > LIMIT_PERFIL_ACTIVE) {
                codigoPerfil = key;
            }
        }
        return codigoPerfil;
    }

    /**
     * Evalua una respuesta de una evaluación, para determinar una conclusion
     *
     * @return Resultado de la evaluacion, el tipo determina si llego a una
     * conclusion o necesita evaluar mas preguntas.
     */
    public ResultadoInferencia inferir(List<Regla> reglasActivadas) {
        ResultadoInferencia resultado = new ResultadoInferencia();
        Map<String, Integer> perfiles = new HashMap<String, Integer>();

        for (Regla reglasActivada : reglasActivadas) {
            String codigoPerfil = reglasActivada.getPerfil().getCodigo();
            int veces = perfiles.get(codigoPerfil) == null ? 0 : perfiles.get(codigoPerfil);
            veces++;
            perfiles.put(codigoPerfil, veces);
        }

        String codigoPerfilObtenido = obtenerCodigoPerfil(reglasActivadas.size(), perfiles);
        resultado.setConclusion(codigoPerfilObtenido);
        return resultado;
    }

    /**
     *
     * @param preguntasResueltas
     * @return
     */
    @Override
    public ResultadoInferencia evaluate(List<PreguntaEvaluacion> preguntasResueltas) {
        List<Regla> reglasActivadas = new ArrayList<Regla>();
        //Revisando el cumplimiento de cada Regla
        for (Regla regla : reglas) {
            boolean cumple = Boolean.TRUE;
            //Evaluando cada pregunta
            log.debug("Evaluando preguntas de la regla [" + regla.getCodigo() + "] [" + regla.getPreguntas().size() + "]");
            for (PreguntaRegla preguntasRegla : regla.getPreguntas()) {
                if (cumple) {
                    //Buscando la pregunta de la evaluacion
                    PreguntaEvaluacion preguntasResuelta = buscarPreguntaEvaluacion(preguntasRegla.getCodigoPregunta(), preguntasResueltas);
                    log.debug("Evaluando regla [" + regla.getCodigo() + "], respuesta obtenida  [" + preguntasResuelta.getAternativaSeleccionada() + "]");
                    cumple = cumple && preguntaAfirmativa(preguntasResuelta);
                }
            }
            log.debug("Evaluando regla [" + regla.getCodigo() + "], resultado del cumplimiento [" + cumple + "]");
            if (cumple) {
                reglasActivadas.add(regla);
            }
        }

        ResultadoInferencia inferencia = inferir(reglasActivadas);

        return inferencia;
    }

    @Override
    public void reset() {
        if (clips != null) {
            clips.reset();
        }
    }

}
