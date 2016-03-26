/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudAlumno;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.service.evaluacion.Evaluacion;
import com.sacooliveros.gepsac.service.evaluacion.EvaluacionService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class SolicitudPsicologicaConsumer extends EvaluadorTask {

    private static final Logger log = LoggerFactory.getLogger(SolicitudPsicologicaConsumer.class);

    private int workerId;
    private BlockingQueue<Mensaje> cola;
    private Evaluacion service;

    public void configure(int workerId, BlockingQueue<Mensaje> cola) {
        this.workerId = workerId;
        this.cola = cola;
        this.service = new EvaluacionService();
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
        String id = mensaje.getId();

        try {
            SolicitudPsicologicaDAO solicitudPsicologicaDAO = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
            /**
             * 4.1.3.	El sistema carga las reglas de acoso escolar de cada
             * perfil
             */

            SolicitudPsicologica solicitud = (SolicitudPsicologica) mensaje.getRequest();
            log.info(/*id + "\t" +*/"El sistema obtiene la solicitud pendiente [{}]", solicitud.getCodigo());
            
            List<SolicitudAlumno> alumnosInvolucrados = solicitudPsicologicaDAO.listarAlumno(solicitud.getCodigo());
            solicitud.setAlumnoInvolucrado(alumnosInvolucrados);

            String msg = service.verificarSolicitudPsicologicaPendiente(solicitud);

            log.info(/*id + "\t" + */msg);
        } catch (ValidatorException e) {
            log.error(id + "\t" + e.getMessage(), e);
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
                mensaje = cola.poll(1, TimeUnit.SECONDS);
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
        return cola;
    }

}
