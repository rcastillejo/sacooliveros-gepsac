/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
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
    @Test
    public void testEvaluarAlumno() {
        log.debug("evaluarAlumno");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "A201500097";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();

        alumno = new Alumno();
        alumno.setCodigo(codigo);
        
        EvaluacionPostulante result = instance.evaluarAlumno(alumno);
        
        log.debug("resultado:" + result);
        assertNotNull(result);
    }

}
