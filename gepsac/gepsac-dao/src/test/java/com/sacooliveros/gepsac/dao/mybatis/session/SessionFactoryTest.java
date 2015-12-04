/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.mybatis.session;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.model.comun.Entidad;
import com.sacooliveros.gepsac.model.experto.Alumno;
import org.apache.ibatis.session.SqlSessionFactory;
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
public class SessionFactoryTest {
    private static final Logger log = LoggerFactory.getLogger(SessionFactoryTest.class);
    public SessionFactoryTest() {
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
     * Test of getSqlSessionFactory method, of class SessionFactory.
     */
    //@Test
    public void testGetSqlSessionFactory() {
        System.out.println("getSqlSessionFactory");
        SessionFactory instance = new SessionFactory();
        SqlSessionFactory result = instance.getSqlSessionFactory();
        assertNotNull(result);
    }
    

    /**
     * Test of destroy method, of class SessionFactory.
     */
    //@Test
    public void testDestroy() {
        System.out.println("destroy");
        SessionFactory instance = new SessionFactory();
        instance.destroy();
        assertNull(instance.getSqlSessionFactory());
    }
    
    @Test
    public void testCargarAlumno() {
        SingletonDAOFactory.init(SingletonDAOFactory.MY_IBATIS);
        
        AlumnoDAO dao = SingletonDAOFactory.getDAOFactory().getAlumnoDAO();
        
        Alumno alumno = new Alumno();
        alumno.setCodigo("A201500098");
        alumno.setSexo(new Entidad("Masculino"));
        alumno.setEdad(15);
        alumno.setContextura(new Entidad("Pequeño"));
        alumno.setEstatura(new Entidad("Alto"));
        alumno.setTipoFamilia(new Entidad("Monoparental"));
        alumno.setOrdenNacimiento(2);
        alumno.setCantHnos(2);
        alumno.setNivelEscolar(new Entidad("Secundaria"));
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(11.00);
        alumno.setCantCambioColegio(2);
        alumno.setReligion(new Entidad("Católico"));
        alumno.setNacionalidad(new Entidad("Peruana"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        dao.cargarCodificacionAlumno(alumno);
        assertNotNull(alumno);
        log.debug("alumno Sexo[{}]", alumno.getSexo());
        log.debug("alumno Contextura[{}]", alumno.getContextura());
        log.debug("alumno Estatura[{}]", alumno.getEstatura());
        log.debug("alumno tipoFamilia[{}]", alumno.getTipoFamilia());
        log.debug("alumno religion[{}]", alumno.getReligion());
        log.debug("alumno getNivelEscolar[{}]", alumno.getNivelEscolar());
        log.debug("alumno getDistrito[{}]", alumno.getDistrito());
        log.debug("alumno getProvincia[{}]", alumno.getProvincia());
        log.debug("alumno getDepartamento[{}]", alumno.getDepartamento());
        log.debug("alumno getNacionalidad[{}]", alumno.getNacionalidad());
        
        SingletonDAOFactory.destroy();
    }
}
