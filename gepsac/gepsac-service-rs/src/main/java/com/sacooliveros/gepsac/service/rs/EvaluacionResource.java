/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ricardo
 */
@Path("evaluacion")
@Consumes("application/json")
@Produces("application/json")
public interface EvaluacionResource {

    @GET
    String test();

    /* @GET
    @Path("/evaluacionAcosoEscolar")
    EvaluacionAcosoEscolar getEvaluacionAcosoEscolar();
    
    @POST
    @Path("/alumno/evaluar")
    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionPostulante);
     */
    @GET
    @Path("/solicitudPsicologica")
    List<SolicitudPsicologica> listarSolicitudPsicologica();

    @GET
    @Path("/solicitudPsicologica/{codigoUsuario}")
    List<SolicitudPsicologica> listarSolicitudPsicologica(@PathParam("codigoUsuario") String codigoUsuario);

    @POST
    @Path("/solicitudPsicologica")
    String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica);

    @GET
    @Path("/acosoEscolar/resuelto")
    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarResuelto();

    @GET
    @Path("/acosoEscolar/{codigoEvaluacion}")
    EvaluacionAcosoEscolar obtenerEvaluacionAcosoEscolar(@PathParam("codigoEvaluacion") String codigoEvaluacion);

    @GET
    @Path("/acosoEscolar/porResolver")
    List<EvaluacionAcosoEscolar> listarEvaluacionAEPorResolver();

    @POST
    @Path("/acosoEscolar/resolver")
    String resolverAcosoEscolar(EvaluacionAcosoEscolar evaluacion);

}
