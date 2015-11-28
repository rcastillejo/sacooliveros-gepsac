/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pe.sacoliveros.app;

import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "WebServiceAlumno")
public class WebServiceAlumno {

    private static final Logger log = LoggerFactory.getLogger(WebServiceAlumno.class);

    private static final String CODIGO_PREFIX = "A20152811";
    private static final String[] GENERO = {"Masculino", "Femenino"};
    private static final String[] CONTEXTURA = {"Grande", "Mediano", "Pequeño"};
    private static final String[] ALTURA = {"Alto", "Medio", "Bajo"};
    private static final String[] TIPO_FAMILIA = {"Nuclear", "Monoparental", "Extensa", "Esamblada", "Extensa"};
    private static final String[] NIVEL_ESCOLAR = {"Secundaria", "Primaria"};
    private static final String[] RELIGION = {"Católico", "Evangelico", "Mormón"};
    private static final String[] NACIONALIDAD = {"Peruano"};
    private static final String[] DISTRITO = {"Lince", "La Victoria", "Jesus María", "Breña", "Pueblo Libre"};
    private static final String[] PROVINCIA = {"Lima"};
    private static final String[] DEPARTAMENTO = {"Lima"};
    private static final int SIZE = 10;
    private static List<Alumno> alumnos;

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listarAlumnoPostulante")
    public List<Alumno> listarAlumnoPostulante() {
        if (alumnos == null || alumnos.isEmpty()) {
            createAlumnosPostulante(SIZE);
        }
        return alumnos;
    }

    @WebMethod(operationName = "obtenerrAlumnoPostulante")
    public Alumno obtenerrAlumnoPostulante(String codigo) {
        listarAlumnoPostulante();
        for (Alumno alumno : alumnos) {
            if (alumno.getCodigo().equals(codigo)) {
                return alumno;
            }
        }
        throw new WebServiceException("No se encuentra el alumno con código: " + codigo);
    }

    private void createAlumnosPostulante(int size) {
        alumnos = new ArrayList<Alumno>();
        for (int i = 0; i < size; i++) {
            Alumno alumno = createAlumno(i);
            alumnos.add(alumno);
        }
    }

    private Alumno createAlumno(int index) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(CODIGO_PREFIX + index);
        alumno.setGenero(GENERO[getInt(0, GENERO.length - 1)]);
        alumno.setEdad(getInt(11, 15));
        alumno.setContextura(CONTEXTURA[getInt(0, CONTEXTURA.length - 1)]);
        alumno.setAltura(ALTURA[getInt(0, ALTURA.length - 1)]);
        alumno.setTipoFamilia(TIPO_FAMILIA[getInt(0, TIPO_FAMILIA.length - 1)]);
        alumno.setOrdenNacimiento(getInt(1, 4));
        alumno.setNumHnos(alumno.getOrdenNacimiento() == 1 ? 0 : getInt(1, 4));
        alumno.setNivelEscolar(NIVEL_ESCOLAR[getInt(0, NIVEL_ESCOLAR.length - 1)]);
        alumno.setGradoEscolar(getInt(0, 5));
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
