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

    private static final Logger log = LoggerFactory.getLogger(WebServiceAlumno.class);

    private static final String[] NOMBRES = {"Luis Ricardo", "Braulia Andrea", "Jose Alberto", "Jose Luis", "Braulio Sergio", "Raúl Leonardo"};
    private static final String[] APELLIDOS = {"Castillejo", "Castillo", "Jose ", "Gálvez", "Cornejo", "Herrera", "Jimenez"};
    private static final String[] DOMICILIOS = {"Manco Segundo 333", "Gálvez Barrenechea 515", "Univertiaria 550", "Bolivar 1510"};
    private static final String CODIGO_PREFIX = "A20152811";
    private static final String[] GENERO = {"Masculino", "Femenino"};
    private static final String[] CONTEXTURA = {"Grande", "Mediano", "Pequeño"};
    private static final String[] ESTATURA = {"Alto", "Medio", "Bajo"};
    private static final String[] TIPO_FAMILIA = {"Nuclear", "Monoparental", "Extensa", "Esamblada"};
    private static final String[] NIVEL_ESCOLAR = {"Secundaria", "Primaria"};
    private static final String[] RELIGION = {"Católico", "Evangélico", "Mormón"};
    private static final String[] NACIONALIDAD = {"Peruano"};
    private static final String[] DISTRITO = {"Lince", "La Victoria", "Jesus María", "Breña", "Pueblo Libre"};
    private static final String[] PROVINCIA = {"Lima"};
    private static final String[] DEPARTAMENTO = {"Lima"};
    private static final int SIZE = 10;
    private static List<Alumno> alumnosPrueba;

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listarAlumnoPostulante")
    public List<Alumno> listarAlumnoPostulante() {
        if (alumnosPrueba == null || alumnosPrueba.isEmpty()) {
            createAlumnosPostulante(SIZE);
        }
        return alumnosPrueba;
    }

    @WebMethod(operationName = "buscarAlumnoPostulante")
    public List<Alumno> buscarAlumnoPostulante(
            @WebParam(name = "codigo") String codigo,
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos) {

        List<Alumno> alumnos = listarAlumnoPostulante();
        String codigoParam = codigo == null ? "" : codigo;
        String nombresParam = nombres == null ? "" : nombres;
        String apellidosParam = apellidos == null ? "" : apellidos;

        List<Alumno> alumnosBuscados = new ArrayList<Alumno>();
        for (Alumno alumno : alumnos) {
            String apellidosAlumno = alumno.getApellidoPaterno() + ' ' + alumno.getApellidoMaterno();
            if (alumno.getCodigo().contains(codigoParam)
                    && alumno.getNombres().contains(nombresParam)
                    && apellidosAlumno.contains(apellidosParam)) {
                alumnosBuscados.add(alumno);
            }
        }
        return alumnosBuscados;
    }

    @WebMethod(operationName = "obtenerAlumnoPostulante")
    public Alumno obtenerAlumnoPostulante(
            @WebParam(name = "codigo") String codigo) {
        Alumno alumnoObtenido = null;
        List<Alumno> alumnos = listarAlumnoPostulante();
        for (Alumno alumno : alumnos) {
            if (alumno.getCodigo().equals(codigo)) {
                alumnoObtenido = alumno;
                break;
            }
        }
        return alumnoObtenido;
    }

    private void createAlumnosPostulante(int size) {
        alumnosPrueba = new ArrayList<Alumno>();
        for (int i = 0; i < size; i++) {
            Alumno alumno = createAlumno(i);
            alumnosPrueba.add(alumno);
        }
    }

    private Alumno createAlumno(int index) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(CODIGO_PREFIX + index);
        alumno.setNombres(NOMBRES[getInt(0, NOMBRES.length - 1)]);
        alumno.setApellidoPaterno(APELLIDOS[getInt(0, APELLIDOS.length - 1)]);
        alumno.setApellidoMaterno(APELLIDOS[getInt(0, APELLIDOS.length - 1)]);
        alumno.setDomicilio(DOMICILIOS[getInt(0, DOMICILIOS.length - 1)]);

        alumno.setGenero(GENERO[getInt(0, GENERO.length - 1)]);
        alumno.setEdad(getInt(11, 15));
        alumno.setContextura(CONTEXTURA[getInt(0, CONTEXTURA.length - 1)]);
        alumno.setAltura(ESTATURA[getInt(0, ESTATURA.length - 1)]);
        alumno.setTipoFamilia(TIPO_FAMILIA[getInt(0, TIPO_FAMILIA.length - 1)]);
        alumno.setOrdenNacimiento(getInt(1, 4));
        alumno.setNumHnos(alumno.getOrdenNacimiento() == 1 ? 0 : getInt(1, 4));
        alumno.setNivelEscolar(NIVEL_ESCOLAR[getInt(0, NIVEL_ESCOLAR.length - 1)]);
        alumno.setGradoEscolar(alumno.getNivelEscolar().equals(NIVEL_ESCOLAR[0]) ? getInt(1, 5) : getInt(1, 6));
        alumno.setPromedioEscolar(getInt(10, 18));
        alumno.setNroCambioColegio(getInt(0, 5));
        alumno.setReligion(RELIGION[getInt(0, RELIGION.length - 1)]);
        alumno.setNacionalidad(NACIONALIDAD[getInt(0, NACIONALIDAD.length - 1)]);
        alumno.setDistrito(DISTRITO[getInt(0, DISTRITO.length - 1)]);
        alumno.setProvincia(PROVINCIA[getInt(0, PROVINCIA.length - 1)]);
        alumno.setDepartamento(DEPARTAMENTO[getInt(0, DEPARTAMENTO.length - 1)]);
        log.debug("Alumno creado [{}]", alumno);
        return alumno;
    }

    private int getInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
