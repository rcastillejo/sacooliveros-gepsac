/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.resource;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.model.util.State;
import com.sacooliveros.gepsac.service.evaluacion.Evaluacion;
import com.sacooliveros.gepsac.service.evaluacion.EvaluacionService;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import com.sacooliveros.gepsac.service.rs.EvaluacionResource;
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
    private final Experto serviceExperto;

    public EvaluacionRestService() {
        this.service = new EvaluacionService();
        this.serviceExperto = new ExpertoService();
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
    public String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica) {
        try {
            return service.registrarSolicitudPsicologica(solicitudPsicologica);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarResuelto() {        
        try {
            return serviceExperto.listarEvaluacionAcosoEscolar(State.EvaluacionAcosoEscolar.RESUELTO);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public EvaluacionAcosoEscolar obtenerEvaluacionAcosoEscolar(String codigoEvaluacion) {                
        try {
            return serviceExperto.consultarResultadoAcosoEscolar(codigoEvaluacion);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public String resolverAcosoEscolar(EvaluacionAcosoEscolar evaluacion) {                   
        try {
            return service.resolverAcosoEscolar(evaluacion);
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
        }
    }
    
}
