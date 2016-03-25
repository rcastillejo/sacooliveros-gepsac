/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.ReglaMapper;
import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class ReglaMyIbatisDAO extends GenericMyIbatisDAO implements ReglaDAO {

    private static final Logger log = LoggerFactory.getLogger(ReglaMyIbatisDAO.class);

    public ReglaMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public List listar() {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
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
    public Regla obtener(String id) {
        Regla model;
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
            model = mapper.get(id);
            log.info("obtenido [{}]", model);
            return model;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void ingresar(Regla model) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);

            log.debug("Obteniendo codigo [{}] ...", model);
            String codigo = mapper.getCodigo(model);
            if (codigo == null) {
                throw new DAOException("No se pudo obtener codigo");
            }

            model.setCodigo(codigo);

            log.debug("Registrando [{}] ...", model);
            if (mapper.insert(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }

            if (model.getPreguntas() != null) {
                ingresarPreguntas(mapper, model);
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

    private void ingresarPreguntas(ReglaMapper mapper, Regla model) {
        List<PreguntaRegla> preguntas;

        preguntas = model.getPreguntas();

        for (PreguntaRegla pregunta : preguntas) {
            pregunta.setCodigoRegla(model.getCodigo());
            if (mapper.insertPregunta(pregunta) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }

    }

    @Override
    public void actualizar(Regla model) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);

            log.debug("Actualizando [{}] ...", model);
            if (mapper.update(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }

            if (model.getPreguntas() != null) {
                eliminarPreguntas(mapper, model);
                ingresarPreguntas(mapper, model);
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

    /*public void actualizarPreguntas(ReglaMapper mapper, Regla model) {
        List<PreguntaRegla> preguntas;

        preguntas = model.getPreguntas();

        for (PreguntaRegla pregunta : preguntas) {
            pregunta.setCodigoRegla(model.getCodigo());
            if (mapper.updatePreguntas(pregunta) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }

    }*/
    @Override
    public void eliminar(Regla model) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);

            log.debug("Eliminando [{}] ...", model);

            eliminarPreguntas(mapper, model);

            if (mapper.delete(model) == 0) {
                throw new DAOException("No se pudo eliminar");
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

    private void eliminarPreguntas(ReglaMapper mapper, Regla model) {
        if (mapper.deletePreguntas(model) == 0) {
            throw new DAOException("No se pudo eliminar");
        }
    }

    @Override
    public List<Perfil> listarPerfil() {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
            List listado = mapper.queryPerfil();
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<Pregunta> listarPregunta() {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
            List listado = mapper.queryPregunta();
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<PreguntaRegla> listarPreguntaRegla(String codigoRegla) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
            List listado = mapper.getPreguntas(codigoRegla);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    //@Override
    public void actualizarPreguntas(Regla regla) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);

            log.debug("Actualizando [{}] ...", regla);
            eliminarPreguntas(mapper, regla);
            ingresarPreguntas(mapper, regla);
            session.commit();
            log.info("Actualizado [{}]", regla);

        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            throw new DAOException("Error al grabar", e);
        } finally {
            closeConnection(session);
        }
    }

    //@Override
    public void registrarPreguntas(Regla regla) {
        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);

            log.debug("Registrando [{}] ...", regla);
            ingresarPreguntas(mapper, regla);
            session.commit();
            log.info("Registrado [{}]", regla);

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
    public List<Regla> listarReglaActiva() {

        SqlSession session = null;
        ReglaMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ReglaMapper.class);
            List<Regla> listado = mapper.queryReglaActiva();
            if (listado != null && !listado.isEmpty()) {
                for (Regla regla : listado) {
                    List<PreguntaRegla> preguntas = mapper.getPreguntas(regla.getCodigo());
                    regla.setPreguntas(preguntas);
                    log.trace("Preguntas [codigo={}, preguntas={}]", new Object[]{regla.getCodigo(), preguntas});
                }
            }
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }

    }

}
