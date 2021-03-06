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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    @GET
    @Path("/solicitudPsicologica/editar={codigoSolicitud}")
    SolicitudPsicologica obtenerEditarSolicitudPsicologica(@PathParam("codigoSolicitud") String codigoSolicitud);

    @GET
    @Path("/solicitudPsicologica/consultar={codigoSolicitud}")
    SolicitudPsicologica consultarSolicitudPsicologica(@PathParam("codigoSolicitud") String codigoSolicitud);

    @POST
    @Path("/solicitudPsicologica")
    @Produces("text/plain")
    String registrarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica);

    @PUT
    @Path("/solicitudPsicologica")
    @Produces("text/plain")
    String editarSolicitudPsicologica(SolicitudPsicologica solicitudPsicologica);

    @DELETE
    @Path("/solicitudPsicologica/{codigoSolicitud}")
    @Produces("text/plain")
    String eliminarSolicitudPsicologica(@PathParam("codigoSolicitud") String codigoUsuario);

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
