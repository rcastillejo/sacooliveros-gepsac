/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.factory.MyBatisDAOFactory;
import com.sacooliveros.gepsac.model.comun.Entidad;
import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
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
        SingletonDAOFactory.init(SingletonDAOFactory.MY_IBATIS);
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

    //@Test
    public void testCargarAlumno() {
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
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        dao.cargarCodificacionAlumno(alumno);
        log.debug("alumno Sexo[{}]", alumno.getSexo());
        log.debug("alumno Contextura[{}]", alumno.getContextura());
        log.debug("alumno Estatura[{}]", alumno.getEstatura());
        log.debug("alumno tipoFamilia[{}]", alumno.getTipoFamilia());
        log.debug("alumno religion[{}]", alumno.getReligion());
        log.debug("alumno getNivelEscolar[{}]", alumno.getNivelEscolar());
        log.debug("alumno getDistrito[{}]", alumno.getDistrito());
        log.debug("alumno getProvincia[{}]", alumno.getProvincia());
        log.debug("alumno getDepartamento[{}]", alumno.getDepartamento());
    }

    /**
     * Test of evaluarAlumno method, of class ExpertoService.
     */
    //@Test
    public void testEvaluarAlumnoVictima() {
        log.debug("evaluarAlumno");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();

        alumno = new Alumno();
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
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EV"+new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()));
        evaluacion.setAlumno(alumno);
        
        log.debug("evaluacion:" + evaluacion);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);

        log.debug("resultado:" + result);
        assertNotNull(result);

        log.debug("resultado:" + result);
        assertNotNull(result);
        Perfil perfil = null;
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(),
                    perfilEval.getProbabilidad());
            if(perfilEval.isSeleccionado()){
                perfil = perfilEval.getPerfil();
            }
        }
        
        assertNotNull(perfil);
        assertEquals("P0002", perfil.getCodigo());

    }
    //@Test
    public void testEvaluarAlumnoAgresor() {
        log.debug("evaluarAlumno");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();

        alumno = new Alumno();
        alumno.setCodigo("A201500098");
        alumno.setSexo(new Entidad("Masculino"));
        alumno.setEdad(18);
        alumno.setContextura(new Entidad("Grande"));
        alumno.setEstatura(new Entidad("Alto"));
        alumno.setTipoFamilia(new Entidad("Extensa"));
        alumno.setOrdenNacimiento(1);
        alumno.setCantHnos(0);
        alumno.setNivelEscolar(new Entidad("Secundaria"));
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(10.00);
        alumno.setCantCambioColegio(4);
        alumno.setReligion(new Entidad("Católico"));
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EV"+new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()));
        evaluacion.setAlumno(alumno);
        
        log.debug("evaluacion:" + evaluacion);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);

        log.debug("resultado:" + result);
        assertNotNull(result);
        Perfil perfil = null;
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}] [{}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(),
                    perfilEval.getProbabilidad(), perfilEval.isSeleccionado());
            if(perfilEval.isSeleccionado()){
                perfil = perfilEval.getPerfil();
            }
        }
        
        assertNotNull(perfil);
        assertEquals("P0001", perfil.getCodigo());

    }
    //@Test
    public void testEvaluarAlumnoTestigo() {
        log.debug("testEvaluarAlumnoTestigo");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();

        alumno = new Alumno();
        alumno.setCodigo("A201500098");
        alumno.setSexo(new Entidad("Femenino"));
        alumno.setEdad(15);
        alumno.setContextura(new Entidad("Grande"));
        alumno.setEstatura(new Entidad("Medio"));
        alumno.setTipoFamilia(new Entidad("Extensa"));
        alumno.setOrdenNacimiento(2);
        alumno.setCantHnos(2);
        alumno.setNivelEscolar(new Entidad("Primaria"));
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(17.00);
        alumno.setCantCambioColegio(2);
        alumno.setReligion(new Entidad("Ateo"));
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EV"+new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()));
        evaluacion.setAlumno(alumno);
        
        log.debug("evaluacion:" + evaluacion);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);

        log.debug("resultado:" + result);
        assertNotNull(result);

        log.debug("resultado:" + result);
        assertNotNull(result);
        Perfil perfil = null;
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(),
                    perfilEval.getProbabilidad());
            if(perfilEval.isSeleccionado()){
                perfil = perfilEval.getPerfil();
            }
        }
        
        assertNotNull(perfil);
        assertEquals("P0003", perfil.getCodigo());

    }
    //@Test
    public void testEvaluarAlumnoNoIdentificado() {
        log.debug("testEvaluarAlumnoNoIdentificado");
        //String codigo = "A201500099";
        //String codigo = "A201500098";
        String codigo = "2";
        Alumno alumno;
        ExpertoService instance = new ExpertoService();

        alumno = new Alumno();
        alumno.setCodigo("A201500098");
        alumno.setSexo(new Entidad("Femenino"));
        alumno.setEdad(12);
        alumno.setContextura(new Entidad("Mediano"));
        alumno.setEstatura(new Entidad("Alto"));
        alumno.setTipoFamilia(new Entidad("Nuclear"));
        alumno.setOrdenNacimiento(2);
        alumno.setCantHnos(2);
        alumno.setNivelEscolar(new Entidad("Secundaria"));
        alumno.setGradoEscolar(5);
        alumno.setPromedioEscolar(17.00);
        alumno.setCantCambioColegio(2);
        alumno.setReligion(new Entidad("Católico"));
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Pueblo Libre"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

        EvaluacionPostulante evaluacion = new EvaluacionPostulante();
        evaluacion.setCodigo("EV"+new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()));
        evaluacion.setAlumno(alumno);
        
        log.debug("evaluacion:" + evaluacion);
        
        EvaluacionPostulante result = instance.evaluarAlumno(evaluacion);

        log.debug("resultado:" + result);
        assertNotNull(result);

        log.debug("resultado:" + result);
        assertNotNull(result);
        Perfil perfil = null;
        for (PerfilEvaluado perfilEval : result.getPerfiles()) {
            log.debug("Perfil [{}={}]", perfilEval.getPerfil() != null ? perfilEval.getPerfil().getCodigo() : perfilEval.getPerfil(),
                    perfilEval.getProbabilidad());
            if(perfilEval.isSeleccionado()){
                perfil = perfilEval.getPerfil();
            }
        }
        
        assertNotNull(perfil);
        assertEquals("P0000", perfil.getCodigo());

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
        alumno.setSexo(new Entidad("Masculino"));
        alumno.setEdad(10);
        alumno.setContextura(new Entidad("Grande"));
        alumno.setEstatura(new Entidad("Alto"));
        alumno.setTipoFamilia(new Entidad("Esamblada"));
        alumno.setOrdenNacimiento(2);
        alumno.setCantHnos(2);
        alumno.setNivelEscolar(new Entidad("Primaria"));
        alumno.setGradoEscolar(6);
        alumno.setPromedioEscolar(12.00);
        alumno.setCantCambioColegio(1);
        alumno.setReligion(new Entidad(""));
        alumno.setNacionalidad(new Entidad("Peruano"));
        alumno.setDistrito(new Entidad("Breña"));
        alumno.setProvincia(new Entidad("Lima"));
        alumno.setDepartamento(new Entidad("Lima"));

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
