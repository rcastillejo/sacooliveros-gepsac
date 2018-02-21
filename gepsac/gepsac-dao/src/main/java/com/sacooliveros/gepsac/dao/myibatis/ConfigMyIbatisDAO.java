/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.ConfigDAO;
import com.sacooliveros.gepsac.dao.ReglaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.exception.ForeignKeyException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.ConfigMapper;
import com.sacooliveros.gepsac.dao.mybatis.mapper.ReglaMapper;
import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class ConfigMyIbatisDAO extends GenericMyIbatisDAO implements ConfigDAO {

    private static final Logger log = LoggerFactory.getLogger(ConfigMyIbatisDAO.class);

    public ConfigMyIbatisDAO(SqlSessionFactory factory) {
        super(factory);
    }

    @Override
    public List<String> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtener(String id) {
        String model;
        SqlSession session = null;
        ConfigMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(ConfigMapper.class);
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
    public void ingresar(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
