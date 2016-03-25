/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.ExplicacionResultado;
import com.sacooliveros.gepsac.model.experto.Regla;
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
@Path("experto")
@Consumes("application/json")
@Produces("application/json")
public interface ExpertoResource {

    @GET
    String test();

    @GET
    @Path("/evaluacionAcosoEscolar")
    EvaluacionAcosoEscolar getEvaluacionAcosoEscolar();

    @POST
    @Path("/alumno/evaluar")
    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionPostulante);

    @GET
    @Path("/acosoEscolar/evaluado")
    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluado();

    @GET
    @Path("/acosoEscolar/evaluadoResuelto")
    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolarEvaluadoResuelto();

    @GET
    @Path("/explicacion/{codigoEvaluacion}")
    ExplicacionResultado generarExplicacion(@PathParam("codigoEvaluacion") String codigoEvaluacion);

    @GET
    @Path("/acosoEscolar/evaluado/{codigoEvaluacion}")
    EvaluacionAcosoEscolar consultarEvaluacionAcosoEscolarEvaluado(@PathParam("codigoEvaluacion") String codigoEvaluacion);

    @GET
    @Path("/perfil")
    List<Perfil> listarPerfil();

    @GET
    @Path("/pregunta")
    List<Pregunta> listarPregunta();

    @GET
    @Path("/regla")
    List<Regla> listarRegla();

    @GET
    @Path("/regla/{codigoRegla}")
    Regla obtenerRegla(@PathParam("codigoRegla") String codigoRegla);

    @POST
    @Path("/regla")
    @Produces("text/plain")
    String agregarRegla(Regla regla);

    @PUT
    @Path("/regla")
    @Produces("text/plain")
    String modificarRegla(Regla regla);

    @DELETE
    @Path("/regla/{codigoRegla}")
    @Produces("text/plain")
    String eliminarRegla(@PathParam("codigoRegla") String codigoRegla);

}
