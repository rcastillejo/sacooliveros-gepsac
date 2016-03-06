/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.resource;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.model.util.State;
import com.sacooliveros.gepsac.service.evaluacion.Evaluacion;
import com.sacooliveros.gepsac.service.evaluacion.EvaluacionService;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.rs.EvaluacionResource;
import com.sacooliveros.gepsac.service.rs.ExpertoResource;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class EvaluacionRestService implements EvaluacionResource {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionRestService.class);
    private final Evaluacion service;

    public EvaluacionRestService() {
        this.service = new EvaluacionService();
    }
        
    @Override
    public String test() {
        String result = "evaluacion test";
        log.debug("Resultado " + result);
        return result;
    }

    @Override
    public List<SolicitudPsicologica> listarSolicitudPsicologica() {
        try {
            return service.listarSolicitudPsicologica();
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public SolicitudPsicologica registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) {
        /*try {
            return service.generarExplicacionResultado(evaluacionAcosoEscolar);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }*/
        return null;
    }
    
}
