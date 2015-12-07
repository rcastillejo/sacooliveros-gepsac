/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.evaluador.config.Configuration;
import com.sacooliveros.gepsac.evaluador.util.Identificador;
import com.sacooliveros.gepsac.model.comun.Estado;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.EngineFactory;
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
    private final BlockingQueue<Mensaje> colaEvaluacion;
    private final Experto experto;
    private final long waitMilis;

    public TimerTask(Configuration configuration, BlockingQueue<Mensaje> colaEvaluacion) {
        this.idf = Identificador.getInstance(configuration.getBrokerName());
        this.colaEvaluacion = colaEvaluacion;
        this.experto = new ExpertoService();
        this.waitMilis = configuration.getTimeForThreads();
    }

    @Override
    public void run() {

        while (Boolean.TRUE) {

            try {

                /**
                 * 4.1.2.	El sistema busca los registros de las evaluaciones en
                 * estado “Registrado”.
                 */
                log.info("El sistema busca los registros de las evaliaciones en estado 'Registrado'");
                List<EvaluacionAcosoEscolar> evaluaciones = buscaEvaluacionesAcosoEscolar();

                /**
                 * Se crea el mensaje para cada evaluacion
                 */
                for (EvaluacionAcosoEscolar evaluacion : evaluaciones) {
                    enviarMensaje(evaluacion);
                }

            } catch (ExpertoServiceException e) {
                log.error(e.getMessage(), e);
            } catch (Exception e) {
                log.error("Error desconocido en el sistema", e);
            } finally {
                try {
                    log.debug("Esperando a consultar [" + waitMilis + "] ...");
                    Thread.sleep(waitMilis);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Busca los registros de las evaluaciones en estado Registrado
     *
     * @return Evaluaciones en estado Registrado
     */
    private List<EvaluacionAcosoEscolar> buscaEvaluacionesAcosoEscolar() {
        return experto.listarEvaluacionAcosoEscolar(Estado.EvaluacionAcosoEscolar.REGISTRADO);
    }

    private void enviarMensaje(EvaluacionAcosoEscolar evaluacion) {
        Mensaje evaluacionModel = new Mensaje();
        evaluacionModel.setId(idf.getCode());
        evaluacionModel.setEvaluacion(evaluacion);

        boolean insertaOk = colaEvaluacion.offer(evaluacionModel);
        if (insertaOk) {
            log.error("No se ingreso las solicitudes a evaluar [{}]", evaluacion.getCodigo());
        } else {
            log.info("Se solicita la evaluacion de solicitudes [{}]", evaluacion.getCodigo());
        }
    }
}
