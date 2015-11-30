/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.myibatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author Ricardo
 */
public class GenericMyIbatisDAO {

    private final SqlSessionFactory sessionFactory;

    public GenericMyIbatisDAO(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SqlSession getConnection() {
        return sessionFactory.openSession();
    }

    public void closeConnection(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
