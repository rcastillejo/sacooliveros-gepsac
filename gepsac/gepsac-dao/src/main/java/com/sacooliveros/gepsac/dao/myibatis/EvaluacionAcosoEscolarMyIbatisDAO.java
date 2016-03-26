/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.EvaluacionAcosoEscolarDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.EvaluacionAcosoEscolarMapper;
import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacionAlternativa;
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
public class EvaluacionAcosoEscolarMyIbatisDAO extends GenericMyIbatisDAO implements EvaluacionAcosoEscolarDAO {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionAcosoEscolarMyIbatisDAO.class);

    public EvaluacionAcosoEscolarMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public String getCodigo() {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            String codigo = mapper.getCodigo();
            log.debug("Codigo [{}]", new Object[]{codigo});
            return codigo;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List listar() {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
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
    public EvaluacionAcosoEscolar obtener(String id) {
        EvaluacionAcosoEscolar model;
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
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
    public EvaluacionAcosoEscolar obtenerDesdePlantillaVigente() {
        EvaluacionAcosoEscolar model;
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            model = mapper.getDesdePlantillaVigente();
                        
            log.info("Evaluacion obtenido [{}]", model);
            return model;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<PreguntaEvaluacionAlternativa> obtenerPreguntaDesdePlantilla(String codigoPlantilla) {
        List<PreguntaEvaluacionAlternativa> preguntasEvaluacionAlternativa;
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            preguntasEvaluacionAlternativa = mapper.listarPreguntaDesdePlantilla(codigoPlantilla);
             
            log.info("Listado obtenido [{}]", preguntasEvaluacionAlternativa == null ? 0 : preguntasEvaluacionAlternativa.size());
            return preguntasEvaluacionAlternativa;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }


    @Override
    public void ingresar(EvaluacionAcosoEscolar model, List<PreguntaEvaluacionAlternativa> preguntaAlternativas) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);

            log.debug("Registrando [{}] ...", model);
            if (mapper.insert(model) == 0) {
                throw new DAOException("No se pudo registrar");
            }

            if (preguntaAlternativas != null) {
                ingresarPreguntas(mapper, model, preguntaAlternativas);
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

    public void ingresarPreguntas(EvaluacionAcosoEscolarMapper mapper, EvaluacionAcosoEscolar model, List<PreguntaEvaluacionAlternativa> preguntas) {
        for (PreguntaEvaluacionAlternativa pregunta : preguntas) {
            pregunta.setCodigoEvaluacion(model.getCodigo());
            pregunta.setCodigoPlantilla(model.getCodigoPlantilla());
            if (mapper.insertPreguntaEvaluacion(pregunta) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }
    }

    @Override
    public void actualizar(EvaluacionAcosoEscolar model) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);

            model.setFechaEvaluacion(new Date());

            log.debug("Actualizando [{}] ...", model);
            if (mapper.update(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }

            if (model.getPreguntas() != null) {
                actualizarPreguntas(mapper, model);
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

    public void actualizarPreguntas(EvaluacionAcosoEscolarMapper mapper, EvaluacionAcosoEscolar model) {
        List<PreguntaEvaluacion> preguntas;

        preguntas = model.getPreguntas();

        for (PreguntaEvaluacion pregunta : preguntas) {
            pregunta.setCodigoEvaluacion(model.getCodigo());
            if (mapper.updatePreguntas(pregunta) == 0) {
                throw new DAOException("No se pudo registrar");
            }
        }

    }

    @Override
    public void eliminar(EvaluacionAcosoEscolar plan) {
    }

    @Override
    public List<EvaluacionAcosoEscolar> listarEvaluacionPorEstado(String codigoEstado) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
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
    public List<EvaluacionAcosoEscolar> listarEvaluacionEvaluadoResuelto() {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            List listado = mapper.queryEvaluadoResuelto();
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<PreguntaEvaluacion> listarPreguntaEvaluacion(String codigoEvaluacion) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            List listado = mapper.queryPregunta(codigoEvaluacion);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<PreguntaEvaluacionAlternativa> listarPreguntaEvaluacionAlternativa(String codigoEvaluacion, String codigoPregunta) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            List listado = mapper.queryPreguntaAlternativa(codigoEvaluacion, codigoPregunta);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public List<PreguntaEvaluacion> listarPreguntaAfirmativa(String codigoEvaluacion) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);
            List listado = mapper.queryPreguntaAfirmativa(codigoEvaluacion);
            log.debug("Listado tamanio[{}] [{}] ", new Object[]{listado == null ? 0 : listado.size(), listado});
            return listado;
        } catch (Exception e) {
            throw new DAOException("Error al consultar", e);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void actualizarRespuestaEvaluacion(EvaluacionAcosoEscolar model) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);

            model.setFechaEvaluacion(new Date());

            log.debug("Actualizando [{}] ...", model);
            if (mapper.updateRespEvalAcosoEscolar(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
            /*@TODO:Ingresar la relacion de reglas que fueron aplicadas
            if (model.getPreguntas() != null) {
                actualizarPreguntas(mapper, model);
            }*/

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

    private void actualizarRespuestaPreguntas(EvaluacionAcosoEscolarMapper mapper, EvaluacionAcosoEscolar model) {
        List<PreguntaEvaluacion> preguntas;

        preguntas = model.getPreguntas();

        for (PreguntaEvaluacion pregunta : preguntas) {
            for (PreguntaEvaluacionAlternativa alternativa : pregunta.getAlternativas()) {
                alternativa.setCodigoEvaluacion(model.getCodigo());
                alternativa.setCodigoPregunta(pregunta.getPregunta().getCodigo());
                log.debug("Actualizando Alternativa [{}]", alternativa);
                if (mapper.updateRespuestaPreguntas(alternativa) == 0) {
                    throw new DAOException("No se pudo registrar");
                }
            }
        }

    }

    @Override
    public void registrarRespuestaEvaluacion(EvaluacionAcosoEscolar model) {
        SqlSession session = null;
        EvaluacionAcosoEscolarMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(EvaluacionAcosoEscolarMapper.class);

            log.debug("Actualizando [{}] ...", model);
            if (mapper.updateResueltoEvalAcosoEscolar(model) == 0) {
                throw new DAOException("No se pudo actualizar");
            }

            if (model.getPreguntas() != null) {
                actualizarRespuestaPreguntas(mapper, model);
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
    public void ingresar(EvaluacionAcosoEscolar t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
