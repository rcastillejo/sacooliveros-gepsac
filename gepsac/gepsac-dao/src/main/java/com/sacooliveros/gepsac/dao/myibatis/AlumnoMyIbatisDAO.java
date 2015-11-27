/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.AlumnoDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.AlumnoMapper;
import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class AlumnoMyIbatisDAO extends GenericMyIbatisDAO implements AlumnoDAO {

    private static final Logger log = LoggerFactory.getLogger(AlumnoMyIbatisDAO.class);

    public AlumnoMyIbatisDAO() {
    }

    @Override
    public List listar() {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            List listado = mapper.query();
            log.debug("Listado tamanio[{}] [{}] ", listado == null ? 0 : listado.size(), listado);
            return listado;
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public Alumno obtener(String id) {
        Alumno model;
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            model = mapper.get(id);
            log.info("Plan obtenido [{}]", model);
            return model;
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void ingresar(Alumno model) {

    }

    @Override
    public void actualizar(Alumno model) {
    }

    @Override
    public void eliminar(Alumno plan) {
    }

    @Override
    public Alumno obtenerPostulante(String id) {
        Alumno model;
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            model = mapper.getPostulante(id);
            log.info("Plan obtenido [{}]", model);
            return model;
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void ingresarPostulante(Alumno model) {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            log.debug("Registrando [{}] ...", model);
            if (mapper.insertPostulante(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }
            session.commit();
            log.info("Registrado [{}]", model);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw e;
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void actualizarPostulante(Alumno model) {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            log.debug("Actualizando [{}] ...", model);
            if (mapper.updatePostulante(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
            session.commit();
            log.info("Actualizado [{}]", model);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw e;
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void grabarPostulante(Alumno model) {

        Alumno alumnoConsultado = obtenerPostulante(model.getCodigo());
        log.debug("Alumno consultado [{}]", alumnoConsultado);

        if (alumnoConsultado == null) {
            ingresarPostulante(model);
        } else {
            actualizarPostulante(model);
        }
    }
}
