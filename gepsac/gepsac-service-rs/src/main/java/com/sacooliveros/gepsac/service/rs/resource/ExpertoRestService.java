/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.resource;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.model.experto.Regla;
import com.sacooliveros.gepsac.model.util.State;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import com.sacooliveros.gepsac.service.rs.ExpertoResource;
import com.sacooliveros.gepsac.service.rs.exception.RestServiceException;
import java.util.List;
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
    private final Experto service;

    public ExpertoRestService() {
        this.service = new ExpertoService();
    }
    
    
    @Override
    public EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacion) {
        try {
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
    
    @Override
    public EvaluacionAcosoEscolar getEvaluacionAcosoEscolar() {
        EvaluacionAcosoEscolar result = new EvaluacionAcosoEscolar();
        result.setCodigo("1234");
        log.debug("Resultado " + result);
        return result;
    }

    @Override
    public ExplicacionResultado generarExplicacion(String codigoEvaluacionAcosoEscolar) {        
        try {
            return service.generarExplicacionResultado(codigoEvaluacionAcosoEscolar);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluado() {
         
        try {
            return service.listarEvaluacionAcosoEscolar(State.EvaluacionAcosoEscolar.EVALUADO);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluadoResuelto() {
         
        try {
            return service.listarEvaluacionAcosoEscolarEvaluadoResuelto();
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public EvaluacionAcosoEscolar consultarEvaluacionAcosoEscolarEvaluado(String codigoEvaluacion) {    
        try {
            return service.consultarResultadoAcosoEscolar(codigoEvaluacion);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException(
                    Response.status(500).entity(e.getMessage()).build());
        }
    }

    @Override
    public List<Perfil> listarPerfil() { 
        try {
            return service.listarPerfil();
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public List<Pregunta> listarPregunta() { 
        try {
            return service.listarPregunta();
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public List<Regla> listarRegla() { 
        try {
            return service.listarRegla();
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public Regla obtenerRegla(String codigoRegla) { 
        try {
            return service.obtenerRegla(codigoRegla);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public String agregarRegla(Regla regla) {
        try {
            return service.agregarRegla(regla);
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public String modificarRegla(Regla regla) {
        try {
            return service.actualizarRegla(regla);
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    @Override
    public String eliminarRegla(String codigoRegla) {
        try {
            return service.eliminarRegla(codigoRegla);
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        } catch (ExpertoServiceException e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }
    
}
