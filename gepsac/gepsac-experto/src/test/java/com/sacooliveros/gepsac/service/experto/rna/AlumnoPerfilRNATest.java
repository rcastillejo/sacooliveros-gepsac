/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo
 */
public class AlumnoPerfilRNATest {

    public AlumnoPerfilRNATest() {
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
     * Test of clasificar method, of class AlumnoPerfilRNA.
     */
    @Test
    public void testClasificar() {
        System.out.println("clasificar");
        Alumno alumnoEvaluar;
        AlumnoPerfilRNA instance;

        alumnoEvaluar = new Alumno();
        alumnoEvaluar.setCodigo("A201500099");

        instance = new AlumnoPerfilRNA(alumnoEvaluar);

        List<PerfilEvaluado> result = instance.clasificar();

        assertNotNull(result);

        System.out.println("result:" + result);

    }

}
