/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.EngineFactory;
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

    //private boolean continuaServerThread;
    private final int workerId;
    private final BlockingQueue<Mensaje> colaEvaluacion;
    private final Experto service;

    public EvaluadorTask(int workerId, BlockingQueue<Mensaje> colaEvaluacion) {
        this.workerId = workerId;
        this.colaEvaluacion = colaEvaluacion;
        //this.continuaServerThread = Boolean.TRUE;
        this.service = new ExpertoService();
    }

    @Override
    public void run() {

        log.info("Iniciando el thread[" + workerId + "] de atencion");

        while (Boolean.TRUE) {
            Mensaje mensaje = obtenerDatosCola();
            procesarMensaje(mensaje);
        }

        log.info("Finalizando hilo de atencion [" + workerId + "]");

    }

    public void procesarMensaje(Mensaje mensaje) {
        String id = mensaje.getId();

        try {
            /**
             * 4.1.3.	El sistema carga las reglas de acoso escolar de cada
             * perfil
             */
            log.info("El sistema carga las reglas de acoso escolar de cada perfil");
            Engine engine = EngineFactory.create();
            
            EvaluacionAcosoEscolar evaluacion = mensaje.getEvaluacion();
            
            String msg = service.evaluarRespuestaAcosoEscolar(evaluacion, engine);
            log.info(id + "\t" + msg);
        } catch (ExpertoServiceException e) {
            log.error(id + "\t" + e.getMessage(), e);
        } catch (Exception e) {
            log.error(id + "\tOcurrio un error en el procesamiento", e);
        }
    }

    /**
     * Lee el mensaje de cola de comunicacion cada 1 segundo
     *
     * @return Mensaje
     */
    public Mensaje obtenerDatosCola() {
        Mensaje mensaje;
        log.trace("Esperando leer de la cola ...");
        while (true) {
            try {
                log.trace("Verificando mensaje de la cola");
                mensaje = colaEvaluacion.poll(1, TimeUnit.SECONDS);
                if (mensaje != null) {
                    break;
                }
            } catch (InterruptedException e) {
                log.warn("No se pudo leer de la cola");
            }
        }

        log.debug("Mensjae obtenido de la cola [" + mensaje + "]s");

        return mensaje;
    }

    /*
    public void stop() {
        continuaServerThread = Boolean.FALSE;
    }*/
}
