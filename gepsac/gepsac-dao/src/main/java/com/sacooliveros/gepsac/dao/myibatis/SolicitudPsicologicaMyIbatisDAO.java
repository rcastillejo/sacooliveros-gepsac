/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.SolicitudPsicologicaMapper;
import com.sacooliveros.gepsac.dao.mybatis.mapper.SolicitudPsicologicaMapper;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudAlumno;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class SolicitudPsicologicaMyIbatisDAO extends GenericMyIbatisDAO implements SolicitudPsicologicaDAO {

    private static final Logger log = LoggerFactory.getLogger(SolicitudPsicologicaMyIbatisDAO.class);

    public SolicitudPsicologicaMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public List listar() {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
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
    public List<SolicitudPsicologica> listarPorEstado(String codigoEstado) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            List listado = mapper.queryEstado(codigoEstado);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<SolicitudPsicologica> listarPendiente(int maxMinutosPendiente) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            List listado = mapper.queryPendiente(maxMinutosPendiente);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List listarAlumno(String codigoSolicitud) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            List listado = mapper.queryAlumnos(codigoSolicitud);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    /*
    @Override
    public Alumno obtener(String id) {
        Alumno model;
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
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
    public Alumno obtenerPostulante(String id) {
        Alumno model;
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            model = mapper.getPostulante(id);
            log.info("Plan obtenido [{}]", model);
            return model;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

   

    
    

    
     */
    @Override
    public void ingresar(SolicitudPsicologica model) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            log.debug("Registrando SolicitudPsicologica [{}] ...", model);
            if (mapper.insert(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }

            for (SolicitudAlumno object : model.getAlumnoInvolucrado()) {
                object.setCodigoSolicitud(model.getCodigo());
                mapper.insertAlumnos(object);
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
    public void actualizar(SolicitudPsicologica model) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            log.debug("Actualizando SolicitudPsicologica [{}] ...", model);
            if (mapper.update(model) == 0) {
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
    public void actualizarEstado(SolicitudPsicologica model) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            log.debug("Actualizando Estado SolicitudPsicologica [{}] ...", model);
            if (mapper.updateEstado(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
            session.commit();
            log.info("Estado Actualizado [{}]", model);

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
    public SolicitudPsicologica obtener(String id) {
        SolicitudPsicologica model;
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            model = mapper.get(id);
            log.info("Plan obtenido [{}]", model);
            return model;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    /*@Override
    public void grabarSolicitudPsicologica(SolicitudPsicologica model) {
        SolicitudPsicologica solicitudPsicologicaConsultado = obtener(model.getCodigo());
        log.debug("SolicitudPsicologica consultado [{}]", solicitudPsicologicaConsultado);

        if (solicitudPsicologicaConsultado == null) {
            ingresar(model);
        } else {
            actualizar(model);
        }
    }*/

    @Override
    public void eliminar(SolicitudPsicologica model) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            log.debug("Eliminando SolicitudPsicologica [{}] ...", model);
            if (mapper.delete(model) == 0) {
                throw new DAOException("No se pudo eliminar");
            }
            session.commit();
            log.info("Eliminado [{}]", model);

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
    public int cantidadSolicitudPsicologicaAlumnoInvolucrado(String alumnoInvolucradoCodigo) {
        SqlSession session = null;
        SolicitudPsicologicaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(SolicitudPsicologicaMapper.class);
            int cantidad = mapper.queryCantidadAtendidas(alumnoInvolucradoCodigo);
            log.debug("cantidad [{}]", new Object[]{cantidad});
            return cantidad;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

}
