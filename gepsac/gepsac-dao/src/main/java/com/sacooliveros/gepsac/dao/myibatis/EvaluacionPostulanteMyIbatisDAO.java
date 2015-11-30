/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.EvaluacionPostulanteDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.EvaluacionPostulanteMapper;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.model.experto.PerfilEvaluado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class EvaluacionPostulanteMyIbatisDAO extends GenericMyIbatisDAO implements EvaluacionPostulanteDAO {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionPostulanteMyIbatisDAO.class);

    public EvaluacionPostulanteMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public List listar() {
        SqlSession session = null;
        EvaluacionPostulanteMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionPostulanteMapper.class);
            List listado = mapper.query();
            log.debug("Listado tamanio[{}] [{}] ", listado == null ? 0 : listado.size(), listado);
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public EvaluacionPostulante obtener(String id) {
        EvaluacionPostulante model;
        SqlSession session = null;
        EvaluacionPostulanteMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionPostulanteMapper.class);
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
    public void ingresar(EvaluacionPostulante model) {
        SqlSession session = null;
        EvaluacionPostulanteMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionPostulanteMapper.class);

            model.setFechaEvaluacion(new Date());

            log.debug("Registrando [{}] ...", model);
            if (mapper.insert(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }

            if (model.getPerfiles() != null) {
                ingresarPerfiles(mapper, model);
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

    public void ingresarPerfiles(EvaluacionPostulanteMapper mapper, EvaluacionPostulante model) {
        List<PerfilEvaluado> perfiles;

        perfiles = model.getPerfiles();

        for (PerfilEvaluado perfilEvaluado : perfiles) {
            perfilEvaluado.setCodigoEvaluacion(model.getCodigo());
            if (mapper.insertPerfiles(perfilEvaluado) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }

    }

    @Override
    public void actualizar(EvaluacionPostulante model) {
        SqlSession session = null;
        EvaluacionPostulanteMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionPostulanteMapper.class);

            model.setFechaEvaluacion(new Date());

            log.debug("Actualizando [{}] ...", model);
            if (mapper.update(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }

            if (model.getPerfiles() != null) {
                actualizarPerfiles(mapper, model);
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

    public void actualizarPerfiles(EvaluacionPostulanteMapper mapper, EvaluacionPostulante model) {
        List<PerfilEvaluado> perfiles;

        perfiles = model.getPerfiles();

        for (PerfilEvaluado perfilEvaluado : perfiles) {
            perfilEvaluado.setCodigoEvaluacion(model.getCodigo());
            if (mapper.updatePerfiles(perfilEvaluado) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }

    }

    @Override
    public void eliminar(EvaluacionPostulante plan) {
    }

}
