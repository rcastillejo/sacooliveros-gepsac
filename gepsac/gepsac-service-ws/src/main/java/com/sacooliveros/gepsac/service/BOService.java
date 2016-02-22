/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.service.exception.ServiceException;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "BOService")
@HandlerChain(file = "/handler.xml")
public class BOService {
    
    private static final Logger log = LoggerFactory.getLogger(BOService.class);
    private final Experto service;

    public BOService() {
        this.service = new ExpertoService();
    }
    
    @WebMethod(operationName = "evaluarAlumno")
    public EvaluacionPostulante evaluarAlumno(@WebParam(name = "evaluacionPostulante") EvaluacionPostulante evaluacion) {
        try {
            return service.evaluarAlumno(evaluacion);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getCode(), e.getMessage(), e);
        }
    }

    @WebMethod(operationName = "explicacion")
    public ExplicacionResultado generarExplicacion(@WebParam(name = "codigoEvaluacionAcosoEscolar") String evaluacion) {
        try {
            return service.generarExplicacionResultado(evaluacion);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getCode(), e.getMessage(), e);
        }
    }

}
