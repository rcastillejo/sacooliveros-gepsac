/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs;

import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ricardo
 */
@Path("experto")
@Consumes("application/json")
@Produces("application/json")
public interface ExpertoResource {
    
    @GET
    String test();
    
    @POST
    @Path("/evaluar/alumno")
    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionPostulante);
}