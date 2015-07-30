/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.dao.mybatis.mapper.PlanMapper;
import com.sacooliveros.gepsac.model.Plan;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author rcastillejo
 */
public class PlanMyIbatisDAO extends GenericMyIbatisDAO implements PlanDAO {

    private final List<Plan> planes;

    public PlanMyIbatisDAO() {
        planes = new ArrayList();
    }

    @Override
    public List listar() {
        SqlSession session = null;
        PlanMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(PlanMapper.class);
            return mapper.query();
        } finally {
            closeConnection(session);
        }
    }


    @Override
    public Plan obtener(String id) {
        SqlSession session = null;
        PlanMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(PlanMapper.class);
            return mapper.get(id);
        } finally {
            closeConnection(session);
        }
    }
    
    @Override
    public Plan obtenerVigente(int anio) {
        
        SqlSession session = null;
        PlanMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(PlanMapper.class);
            return mapper.obtenerVigente(anio);
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void ingresar(Plan plan) {

    }

    @Override
    public void actualizar(Plan plan) {
        SqlSession session = null;
        PlanMapper mapper;

        try {
            session = getConnection();
            mapper = session.getMapper(PlanMapper.class);
            if (mapper.update(plan) == 0) {
                throw new DAOException("No se pudo actualizar");
            }
        } finally {
            closeConnection(session);
        }
    }

    @Override
    public void eliminar(Plan plan) {
        
    }

}
