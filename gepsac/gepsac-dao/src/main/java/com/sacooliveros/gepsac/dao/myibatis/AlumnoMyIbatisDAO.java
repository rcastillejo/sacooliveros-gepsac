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
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class AlumnoMyIbatisDAO extends GenericMyIbatisDAO implements AlumnoDAO {

    private static final Logger log = LoggerFactory.getLogger(AlumnoMyIbatisDAO.class);

    public AlumnoMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public List listar() {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            List listado = mapper.query();
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
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
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
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
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public Alumno obtenerEvaluado(String id) {
        Alumno model;
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            model = mapper.getEvaluado(id);
            log.info("Plan obtenido [{}]", model);
            return model;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
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
            throw new DAOException("Error al grabar", e);
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
            throw new DAOException("Error al grabar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void ingresarEvaluado(Alumno model) {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            log.debug("Registrando [{}] ...", model);
            if (mapper.insertEvaluado(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }
            session.commit();
            log.info("Registrado [{}]", model);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw new DAOException("Error al grabar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void actualizarEvaluado(Alumno model) {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            log.debug("Actualizando [{}] ...", model);
            if (mapper.updateEvaluado(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
            session.commit();
            log.info("Actualizado [{}]", model);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw new DAOException("Error al grabar", e);
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

    @Override
    public void grabarEvaluado(Alumno model) {

        Alumno alumnoConsultado = obtenerEvaluado(model.getCodigo());
        log.debug("Alumno consultado [{}]", alumnoConsultado);

        if (alumnoConsultado == null) {
            ingresarEvaluado(model);
        } else {
            actualizarEvaluado(model);
        }
    }

    @Override
    public void cargarCodificacionAlumno(Alumno alumno) {
        Alumno model;
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            model = mapper.cargarCodificacionAlumno(alumno);
            log.info("Codificacion alumno obtenido [{}]", model);
            
            alumno.setContextura(model.getContextura());
            alumno.setSexo(model.getSexo());
            alumno.setDepartamento(model.getDepartamento());
            alumno.setDistrito(model.getDistrito());
            alumno.setEstatura(model.getEstatura());
            alumno.setNacionalidad(model.getNacionalidad());
            alumno.setNivelEscolar(model.getNivelEscolar());
            alumno.setProvincia(model.getProvincia());
            alumno.setReligion(model.getReligion());
            alumno.setSexo(model.getSexo());
            alumno.setTipoFamilia(model.getTipoFamilia());
            
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void actualizarEstadoAlumnoEvaluado(Alumno model) {
        SqlSession session = null;
        AlumnoMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(AlumnoMapper.class);
            log.debug("Actualizando [{}] ...", model);
            if (mapper.updateEstadoEvaluado(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
            session.commit();
            log.info("Actualizado [{}]", model);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw new DAOException("Error al grabar", e);
        } finally {
            closeConnection(session);
        }
    }

}
