/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.evaluador.config.Configuration;
import com.sacooliveros.gepsac.evaluador.util.Identificador;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.service.evaluacion.Evaluacion;
import com.sacooliveros.gepsac.service.evaluacion.EvaluacionService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class SolicitudPsicologicaMonitor extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(SolicitudPsicologicaMonitor.class);
    private Identificador idf;
    private BlockingQueue<Mensaje> cola;
    private Evaluacion service;
    private long waitMilis;
    private int maxMinutosPendiente;

    public void configure(Configuration configuration, BlockingQueue<Mensaje> cola) {
        this.idf = Identificador.getInstance(configuration.getBrokerName());
        this.cola = cola;
        this.service = new EvaluacionService();
        this.waitMilis = configuration.getTimeForThreads();
        this.maxMinutosPendiente = Integer.parseInt(configuration.getProperty("solicitudPsicologica.maxMinutosPendiente"));
    }

    @Override
    public void run() {

        while (Boolean.TRUE) {

            log.info("Inicia la revisión de las solicitudes psicologicas en estado pendiente");

            try {

                /**
                 * 4.1.2.	El sistema busca los registros de las evaluaciones en
                 * estado “Registrado”.
                 */
                log.info("El sistema busca los registros de las evaliaciones en estado 'Pendiente' que superaron " + maxMinutosPendiente + " minutos");
                SolicitudPsicologicaDAO solicitudPsicologicaDAO = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();
                List<SolicitudPsicologica> solicitudes = solicitudPsicologicaDAO.listarPendiente(maxMinutosPendiente);

                if (solicitudes == null || solicitudes.isEmpty()) {
                    log.info("El sistema no encuentra solicitudes pendientes ");
                } else {
                    log.info("El sistema obtiene  evaliaciones en estado 'Pendiente' que superaron 30 minutos [{}]", solicitudes.size());
                    /**
                     * Se crea el mensaje para cada evaluacion
                     */
                    for (SolicitudPsicologica solicitud : solicitudes) {
                        enviarMensaje(solicitud);
                    }
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

    private void enviarMensaje(SolicitudPsicologica solicitud) {
        Mensaje mensaje = new Mensaje();
        mensaje.setId(idf.getCode());
        mensaje.setRequest(solicitud);

        boolean insertaOk = cola.offer(mensaje);
        if (insertaOk) {
            log.debug("Se ingreso la solicitud a revisar [{}]", solicitud.getCodigo());
        } else {
            log.debug("No Se ingreso el mensaje para la revision [{}]", solicitud.getCodigo());
        }
    }
}
