/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.resource;

import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.rs.ExpertoResource;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class ExpertoRestService implements ExpertoResource {

    private static final Logger log = LoggerFactory.getLogger(ExpertoRestService.class);
    
    @Override
    public EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacion) {
        try {
            Experto service = new ExpertoService();
            return service.evaluarAlumno(evaluacion);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }
    
    @Override
    public String test() {
        String result = "experto test";
        log.debug("Resultado " + result);
        return result;
    }
    
}
