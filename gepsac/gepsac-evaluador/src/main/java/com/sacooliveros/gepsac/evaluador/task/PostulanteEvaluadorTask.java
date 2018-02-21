/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.task;

import com.sacooliveros.gepsac.evaluador.config.Config;
import com.sacooliveros.gepsac.evaluador.message.Mensaje;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import edu.pe.sacoliveros.app.Alumno;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class PostulanteEvaluadorTask extends EvaluadorTask {

    private static final Logger log = LoggerFactory.getLogger(PostulanteEvaluadorTask.class);

    private String getCodigoDocumento(String codigo) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        return codigo + sdf.format(new Date());
    }

    public void procesarMensaje(Mensaje mensaje) {
        String id = mensaje.getId();

        try {
            log.info(/*id + "\t" +*/"El sistema evalua al alumno postulante");

            Alumno alumno = (Alumno) mensaje.getRequest();
            EvaluacionPostulante evaluacion = new EvaluacionPostulante();
            evaluacion.setCodigo(getCodigoDocumento(Config.CODIGO_DOCUMENTO));

            evaluacion = service.evaluarAlumno(evaluacion);

            String msg = MessageFormat.format(Experto.Mensaje.EVALUAR_ALUMNO_POSTULANTE, new Object[]{evaluacion.getCodigo()});
            log.info(/*id + "\t" + */msg);
        } catch (ExpertoServiceException e) {
            log.error(id + "\t" + e.getMessage(), e);
        } catch (Exception e) {
            log.error(id + "\tOcurrio un error en el procesamiento", e);
        }
    }
}
