/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pe.sacoliveros.app;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "WebServiceAlumno")
public class WebServiceAlumno {

    private static final int SIZE = 10;
    private static final int SIZE_POSTULANTE = 5;
    private static List<Alumno> alumnosPostulante;
    private static List<Alumno> alumnosEvaluado;
    private static final boolean CON_ALUMNO = Boolean.TRUE;

    private final AlumnoMockUtils alumnoUtils = new AlumnoMockUtils();

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listarAlumnoPostulante")
    public List<Alumno> listarAlumnoPostulante() {
        if (CON_ALUMNO) {
            if (alumnosPostulante == null || alumnosPostulante.isEmpty()) {
                alumnosPostulante = alumnoUtils.createAlumnos(AlumnoAtributtesUtils.CODIGO_PREFIX_POSTULANTE, SIZE_POSTULANTE);
                alumnosPostulante.add(alumnoUtils.createAlumnoAgresor("A201500999", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoVictima("A201500998", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoTestigo("A201500997", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoNoIdentificado("A201500996", "", "", ""));
            }
        } else {
            alumnosPostulante = new ArrayList<Alumno>();
        }
        return alumnosPostulante;
    }

    @WebMethod(operationName = "buscarAlumnoPostulante")
    public List<Alumno> buscarAlumnoPostulante(
            @WebParam(name = "codigo") String codigo,
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos) {
        
        alumnosPostulante = listarAlumnoPostulante();

        return alumnoUtils.buscarAlumnos(alumnosPostulante, codigo, nombres, apellidos);
    }

    @WebMethod(operationName = "obtenerAlumnoPostulante")
    public Alumno obtenerAlumnoPostulante(
            @WebParam(name = "codigo") String codigo) {
        alumnosPostulante = listarAlumnoPostulante();
        return alumnoUtils.obtenerAlumno(alumnosPostulante, codigo);
    }

    @WebMethod(operationName = "listarAlumnoEvaluado")
    public List<Alumno> listarAlumnoEvaluado() {
        if (CON_ALUMNO) {
            if (alumnosEvaluado == null || alumnosEvaluado.isEmpty()) {
                alumnosEvaluado = alumnoUtils.createAlumnos(AlumnoAtributtesUtils.CODIGO_PREFIX, SIZE_POSTULANTE);
                alumnosPostulante.add(alumnoUtils.createAlumnoAgresor("A201600099", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoVictima("A201600098", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoTestigo("A201600097", "", "", ""));
                alumnosPostulante.add(alumnoUtils.createAlumnoNoIdentificado("A201600096", "", "", ""));
            }
        } else {
            alumnosEvaluado = new ArrayList<Alumno>();
        }
        return alumnosEvaluado;
    }

    @WebMethod(operationName = "buscarAlumnoEvaluado")
    public List<Alumno> buscarAlumnoEvaluado(
            @WebParam(name = "codigo") String codigo,
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos) {
        alumnosEvaluado = listarAlumnoEvaluado();
        return alumnoUtils.buscarAlumnos(alumnosEvaluado, codigo, nombres, apellidos);
    }

    @WebMethod(operationName = "obtenerAlumnoEvaluacion")
    public Alumno obtenerAlumnoEvaluacion(
            @WebParam(name = "codigo") String codigo) {
        alumnosEvaluado = listarAlumnoEvaluado();
        return alumnoUtils.obtenerAlumno(alumnosEvaluado, codigo);
    }
}
