/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.bean.EvaluadorBean;
import com.sacooliveros.gepsac.evaluador.config.Configuration;
import com.sacooliveros.gepsac.evaluador.util.Identificador;
import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class TimerTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TimerTask.class);
    private final Identificador idf;
    private final BlockingQueue<EvaluadorBean> colaEvaluacion;
    private final Experto experto;
    private final long waitMilis;

    public TimerTask(Configuration configuration, BlockingQueue<EvaluadorBean> colaEvaluacion) {
        this.idf = Identificador.getInstance(configuration.getBrokerName());
        this.colaEvaluacion = colaEvaluacion;
        this.experto = new ExpertoService();
        this.waitMilis = configuration.getTimeForThreads();
    }

    @Override
    public void run() {
        Estado estado = new Estado();
        estado.setCodigo(Experto.Estado.REGISTRADO);

        while (Boolean.TRUE) {

            log.debug("Inciando consulta de acoso escolar en estado[{}]", estado);

            try {
                EvaluadorBean evaluacionModel = new EvaluadorBean();
                evaluacionModel.setId(idf.getCode());

                List<EvaluacionAcosoEscolar> evaluaciones = experto.listarEvaluacionAcosoEscolar(estado);
                evaluacionModel.setEvaluaciones(evaluaciones);

                boolean insertaOk = colaEvaluacion.offer(evaluacionModel);
                if (insertaOk) {
                    log.error("No se ingreso las solicitudes a evaluar [{}]", evaluaciones.size());
                } else {
                    log.info("Se solicita la evaluacion de solicitudes [{}]", evaluaciones.size());
                }
            } catch (ExpertoServiceException e) {
                log.error(e.getMessage(), e);
            } catch (Exception e) {
                log.error("No existen evaluaciones de acoso escolar", e);
            } finally {
                try {
                    log.debug("Esperando a consultar [" + waitMilis + "] ...");
                    Thread.sleep(waitMilis);
                } catch (Exception e) {
                }
            }
        }
    }

}
