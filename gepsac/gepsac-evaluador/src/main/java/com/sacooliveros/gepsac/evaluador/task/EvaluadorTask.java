/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.evaluador.perf.Logp;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.Engines;
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

    protected int workerId;
    protected BlockingQueue<Mensaje> colaEvaluacion;
    protected Experto service;

    public void configure(int workerId, BlockingQueue<Mensaje> colaEvaluacion) {
        this.workerId = workerId;
        this.colaEvaluacion = colaEvaluacion;
        this.service = new ExpertoService();
    }

    @Override
    public void run() {

        log.debug("Iniciando el thread[" + workerId + "] de atencion");

        while (Boolean.TRUE) {
            Mensaje mensaje = obtenerDatosCola();
            procesarMensaje(mensaje);
        }

        log.debug("Finalizando hilo de atencion [" + workerId + "]");

    }

    public void procesarMensaje(Mensaje mensaje) {
        EvaluacionAcosoEscolar evaluacionAcosoEscolar = (EvaluacionAcosoEscolar) mensaje.getRequest();
        String logId = '[' + evaluacionAcosoEscolar.getCodigo() + ']';
        try {

            /**
             * 4.1.3.	El sistema carga las reglas de acoso escolar de cada
             * perfil
             */
            log.info(logId + "El sistema carga las reglas de acoso escolar de cada perfil");
            Engine engine = Engines.create();

            String msg = service.evaluarRespuestaAcosoEscolar(evaluacionAcosoEscolar, engine);
            log.info(logId + msg);

            if (evaluacionAcosoEscolar.getCodigoSolicitud() != null) {
                msg = service.verificarSolicitudPsicologica(evaluacionAcosoEscolar);
                log.info(logId + msg);
            }

            Logp.showTrx(evaluacionAcosoEscolar.getCodigo(), "EvaluarAcosoEscolar", mensaje.getInit());
        } catch (ValidatorException e) {
            log.error(logId + e.getMessage(), e);
        } catch (ExpertoServiceException e) {
            log.error(logId + e.getMessage(), e);
        } catch (Exception e) {
            log.error(logId + "Ocurrio un error en el procesamiento", e);
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
    public int getWorkerId() {
        return workerId;
    }

    public BlockingQueue<Mensaje> getColaEvaluacion() {
        return colaEvaluacion;
    }

}
