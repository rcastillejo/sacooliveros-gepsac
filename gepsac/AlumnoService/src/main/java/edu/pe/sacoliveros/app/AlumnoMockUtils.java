/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pe.sacoliveros.app;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class AlumnoMockUtils {

    private static final Logger log = LoggerFactory.getLogger(AlumnoMockUtils.class);

    public List<Alumno> createAlumnos(String codigoPrefix, int size) {

        List<Alumno> alumnosPostulante = new ArrayList<Alumno>();
        for (int i = 0; i < size; i++) {
            Alumno alumno = createAlumno(codigoPrefix + i);
            alumnosPostulante.add(alumno);
        }

        return alumnosPostulante;
    }

    public List<Alumno> buscarAlumnos(
            List<Alumno> alumnos,
            String codigo,
            String nombres,
            String apellidos) {

        String codigoParam = codigo == null ? "" : codigo;
        String nombresParam = nombres == null ? "" : nombres;
        String apellidosParam = apellidos == null ? "" : apellidos;

        List<Alumno> alumnosBuscados = new ArrayList<Alumno>();
        for (Alumno alumno : alumnos) {
            String apellidosAlumno = alumno.getApellidoPaterno() + ' ' + alumno.getApellidoMaterno();
            if (alumno.getCodigo().toUpperCase().contains(codigoParam.toUpperCase())
                    && alumno.getNombres().toUpperCase().contains(nombresParam.toUpperCase())
                    && apellidosAlumno.toUpperCase().contains(apellidosParam.toUpperCase())) {
                alumnosBuscados.add(alumno);
            }
        }
        return alumnosBuscados;
    }

    public Alumno obtenerAlumno(List<Alumno> alumnos, String codigo) {
        Alumno alumnoObtenido = null;
        for (Alumno alumno : alumnos) {
            if (alumno.getCodigo().equals(codigo)) {
                alumnoObtenido = alumno;
                break;
            }
        }
        return alumnoObtenido;
    }

    public Alumno createAlumno(String codigo) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);
        alumno.setGenero(AlumnoAtributtesUtils.getGenero());

        alumno.setNombres(AlumnoAtributtesUtils.getNombre(alumno.getGenero()));
        alumno.setApellidoPaterno(AlumnoAtributtesUtils.getApellidos());
        alumno.setApellidoMaterno(AlumnoAtributtesUtils.getApellidos());
        alumno.setDomicilio(AlumnoAtributtesUtils.getDomicilio());

        alumno.setEdad(AlumnoAtributtesUtils.getInt(11, 15));
        alumno.setContextura(AlumnoAtributtesUtils.getContextura());

        alumno.setAltura(AlumnoAtributtesUtils.getEstatura());
        alumno.setTipoFamilia(AlumnoAtributtesUtils.getTipoFamilia());
        alumno.setOrdenNacimiento(AlumnoAtributtesUtils.getInt(1, 4));
        alumno.setNumHnos(AlumnoAtributtesUtils.getNumHnos(alumno.getOrdenNacimiento()));
        alumno.setNivelEscolar(AlumnoAtributtesUtils.getNivelEscolar());
        alumno.setGradoEscolar(AlumnoAtributtesUtils.getGradoEscolar(alumno.getNivelEscolar()));
        alumno.setPromedioEscolar(AlumnoAtributtesUtils.getInt(10, 18));
        alumno.setNroCambioColegio(AlumnoAtributtesUtils.getInt(0, 5));
        alumno.setReligion(AlumnoAtributtesUtils.getReligion());
        alumno.setNacionalidad(AlumnoAtributtesUtils.getNacionalidad());
        alumno.setDistrito(AlumnoAtributtesUtils.getDistrito());
        alumno.setProvincia(AlumnoAtributtesUtils.getProvincia());
        alumno.setDepartamento(AlumnoAtributtesUtils.getDepartamento());
        log.debug("Alumno creado [{}]", alumno);
        return alumno;
    }

    public Alumno createAlumnoAgresor(String codigo, 
            String nombres, String apellidoPaterno, String apellidoMaterno) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);

        alumno.setNombres(nombres);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);

        alumno.setDomicilio(AlumnoAtributtesUtils.getDomicilio());

        alumno.setGenero("Masculino");
        alumno.setEdad(18);
        alumno.setContextura("Grande");
        alumno.setAltura("Alto");
        alumno.setTipoFamilia("Extensa");
        alumno.setOrdenNacimiento(1);
        alumno.setNumHnos(0);
        alumno.setNivelEscolar("Secundaria");
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(10.00);
        alumno.setNroCambioColegio(4);
        alumno.setReligion("Católico");
        alumno.setNacionalidad("Peruano");
        alumno.setDistrito("Pueblo Libre");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");

        log.debug("Alumno Agresor creado [{}]", alumno);
        return alumno;
    }

    public Alumno createAlumnoVictima(String codigo, 
            String nombres, String apellidoPaterno, String apellidoMaterno) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);

        alumno.setNombres(nombres);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setDomicilio(AlumnoAtributtesUtils.getDomicilio());

        alumno.setGenero("Masculino");
        alumno.setEdad(15);
        alumno.setContextura("Pequeño");
        alumno.setAltura("Alto");
        alumno.setTipoFamilia("Monoparental");
        alumno.setOrdenNacimiento(2);
        alumno.setNumHnos(2);
        alumno.setNivelEscolar("Secundaria");
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(11.00);
        alumno.setNroCambioColegio(2);
        alumno.setReligion("Católico");
        alumno.setNacionalidad("Peruano");
        alumno.setDistrito("Pueblo Libre");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");

        log.debug("Alumno Victima creado [{}]", alumno);
        return alumno;
    }

    public Alumno createAlumnoTestigo(String codigo, 
            String nombres, String apellidoPaterno, String apellidoMaterno) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);

        alumno.setNombres(nombres);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setDomicilio(AlumnoAtributtesUtils.getDomicilio());

        alumno.setGenero("Femenino");
        alumno.setEdad(15);
        alumno.setContextura("Pequeño");
        alumno.setAltura("Medio");
        alumno.setTipoFamilia("Extensa");
        alumno.setOrdenNacimiento(2);
        alumno.setNumHnos(2);
        alumno.setNivelEscolar("Primaria");
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(17.00);
        alumno.setNroCambioColegio(2);
        alumno.setReligion("Ateo");
        alumno.setNacionalidad("Peruano");
        alumno.setDistrito("Pueblo Libre");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");

        log.debug("Alumno Testigo creado [{}]", alumno);
        return alumno;
    }

    public Alumno createAlumnoNoIdentificado(String codigo, 
            String nombres, String apellidoPaterno, String apellidoMaterno) {
        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);

        alumno.setNombres(nombres);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setDomicilio(AlumnoAtributtesUtils.getDomicilio());

        alumno.setGenero("Femenino");
        alumno.setEdad(12);
        alumno.setContextura("Mediano");
        alumno.setAltura("Alto");
        alumno.setTipoFamilia("Nuclear");
        alumno.setOrdenNacimiento(2);
        alumno.setNumHnos(2);
        alumno.setNivelEscolar("Secundaria");
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(17.00);
        alumno.setNroCambioColegio(2);
        alumno.setReligion("Católico");
        alumno.setNacionalidad("Peruano");
        alumno.setDistrito("Pueblo Libre");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");

        log.debug("Alumno No Identificado creado [{}]", alumno);
        return alumno;
    }

}
