/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pe.sacoliveros.app;

import com.sacooliveros.gepsac.model.experto.Alumno;
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
public class WebServiceAlumnoTest {
    
    public WebServiceAlumnoTest() {
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
     * Test of listarAlumnoPostulante method, of class WebServiceAlumno.
     */
    @Test
    public void testListarAlumnoPostulante() {
        System.out.println("listarAlumnoPostulante");
        WebServiceAlumno instance = new WebServiceAlumno();
        
        List<Alumno> result = instance.listarAlumnoPostulante();
        assertEquals(10, result.size()); 
    }
    
}
