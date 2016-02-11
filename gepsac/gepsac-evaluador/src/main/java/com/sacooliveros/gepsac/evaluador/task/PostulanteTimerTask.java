/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.evaluador.config.Configuration;
import com.sacooliveros.gepsac.proxyws.util.ProxyUtil;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import edu.pe.sacoliveros.app.Alumno;
import edu.pe.sacoliveros.app.WebServiceAlumno;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class PostulanteTimerTask extends TimerTask{

    private static final Logger log = LoggerFactory.getLogger(PostulanteTimerTask.class);
    private long timeout;
    
    @Override
    public void configure(Configuration configuration, BlockingQueue<Mensaje> colaEvaluacion) {
        super.configure(configuration, colaEvaluacion);
        timeout = configuration.getTimeout();
    }

    @Override
    public void run() {

        while (Boolean.TRUE) {

            log.info("Inicia la evaluación de las respuestas de la evaluación de acoso escolar");

            try {

                /**
                 * 4.1.2.	El sistema busca los registros de las evaluaciones en
                 * estado “Registrado”.
                 */
                log.info("El sistema busca los registros de las evaliaciones en estado 'Registrado'");
                List<Alumno> evaluaciones = buscaAlumnoPostulante();

                /**
                 * Se crea el mensaje para cada evaluacion
                 */
                for (Alumno evaluacion : evaluaciones) {
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
    private List<Alumno> buscaAlumnoPostulante() {
        WebServiceAlumno service = ProxyUtil.getAlumoServicePort(timeout);
        return service.listarAlumnoPostulante();
    }

    private void enviarMensaje(Alumno evaluacion) {
        Mensaje evaluacionModel = new Mensaje();
        evaluacionModel.setId(idf.getCode());
        evaluacionModel.setRequest(evaluacion);

        boolean insertaOk = colaEvaluacion.offer(evaluacionModel);
        if (insertaOk) {
            log.debug("Se ingreso la evaluacion a revisar [{}]", evaluacion.getCodigo());
        } else {
            log.debug("No Se ingreso las evaluaciones para la revision [{}]", evaluacion.getCodigo());
        }
    }
}
