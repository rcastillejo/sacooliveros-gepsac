/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import java.text.MessageFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class ExpertoServiceTest {
    
    private static final Logger log = LoggerFactory.getLogger(ExpertoServiceTest.class);
    
    public ExpertoServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of evaluarAlumno method, of class ExpertoService.
     */
    //@Test
    public void testEvaluarAlumno() {
        log.debug("evaluarAlumno");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();
        
        alumno = new Alumno();
        alumno.setCodigo("A201500098");
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
        alumno.setNacionalidad("Peruana");
        alumno.setDistrito("Pueblo Libre");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");
        
        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EVTest");
        evaluacion.setAlumno(alumno);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);
        
        log.debug("resultado:" + result);
        assertNotNull(result);
        
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(), 
                    perfilEval.getProbabilidad());
        }
        
    }
    
    /**
     * Test of evaluarAlumno method, of class ExpertoService.
     */
    //@Test
    public void testEvaluarAlumno2() {
        log.debug("evaluarAlumno");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();
        
        alumno = new Alumno();
        alumno.setCodigo("A201500098");
        alumno.setGenero("Masculino");
        alumno.setEdad(10);
        alumno.setContextura("Grande");
        alumno.setAltura("Alto");
        alumno.setTipoFamilia("Esamblada");
        alumno.setOrdenNacimiento(2);
        alumno.setNumHnos(2);
        alumno.setNivelEscolar("Primaria");
        alumno.setGradoEscolar(6);
        alumno.setPromedioEscolar(12.00);
        alumno.setNroCambioColegio(1);
        alumno.setReligion("");
        alumno.setNacionalidad("Peruano");
        alumno.setDistrito("Breña");
        alumno.setProvincia("Lima");
        alumno.setDepartamento("Lima");
        
        
        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EVTest");
        evaluacion.setAlumno(alumno);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);
        
        log.debug("resultado:" + result);
        assertNotNull(result);
        
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(), 
                    perfilEval.getProbabilidad());
        }
        
    }
    
}
