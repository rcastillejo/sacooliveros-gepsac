/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.bean.EvaluadorBean;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class EvaluadorTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(EvaluadorTask.class);

    private boolean continuaServerThread;
    private final int workerId;
    private final BlockingQueue<EvaluadorBean> colaEvaluacion;
    private final Experto service;

    public EvaluadorTask(int workerId, BlockingQueue<EvaluadorBean> colaEvaluacion) {
        this.workerId = workerId;
        this.colaEvaluacion = colaEvaluacion;
        this.continuaServerThread = Boolean.TRUE;
        this.service = new ExpertoService();
    }

    @Override
    public void run() {

        log.info("Iniciando el thread[" + workerId + "] de atencion");

        // ----------------------------------------------------------------------------------------------------------------------- //
        //
        // MAIN LOOP - PROCESS MESSAGES
        //
        // ----------------------------------------------------------------------------------------------------------------------- //
        EvaluadorBean evaluacionModel = null;
        String id = null;

        LOOP_SERVER:
        while (continuaServerThread) {

            // -------------------------------------------------------------------------------------------------------------- //
            //
            // Lee el mensaje de cola de comunicacion
            //
            // -------------------------------------------------------------------------------------------------------------- // 
            log.trace("Esperando leer de la cola ...");
            while (true) {
                try {
                    log.trace("Verificando estados [continuaServerThread="
                            + continuaServerThread + "]");
                    evaluacionModel = colaEvaluacion.poll(1, TimeUnit.SECONDS);
                    if (evaluacionModel != null) {
                        break;
                    } else if (!continuaServerThread) {
                        continue LOOP_SERVER;
                    }
                } catch (InterruptedException e2) {
                    if (!continuaServerThread) {
                        continue LOOP_SERVER;
                    }
                }
            }

            log.debug("Procesando evaluaciones[" + evaluacionModel + "]...");

            // ----------------------------------------------------------------------------------------------------------------------- //
            //
            // Obtenemos Mensaje del DTO
            //
            // ----------------------------------------------------------------------------------------------------------------------- //
            id = evaluacionModel.getId();

            /*
             * Aqui llamamos a la interfaz 
             */
            try {
                //Engine engine = EngineFactory.create();
                List<EvaluacionAcosoEscolar> evaluaciones = evaluacionModel.getEvaluaciones();
                String msg = service.evaluarRespuestaAcosoEscolar(evaluaciones);
                log.info(msg);
            } catch (Exception e) {
                log.error(id + "\tOcurrio un error en el procesamiento", e);
            }

        }

        log.info("Finalizando hilo de atencion [" + workerId + "]");

    }

    public void stop() {
        continuaServerThread = Boolean.FALSE;
    }
}
