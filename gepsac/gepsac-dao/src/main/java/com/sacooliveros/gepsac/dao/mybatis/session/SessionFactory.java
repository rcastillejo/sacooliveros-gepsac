package com.sacooliveros.gepsac.dao.mybatis.session;

import com.sacooliveros.gepsac.dao.exception.DAOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {

    private static final String DEFAULT_RESOURCE = "mybatis-config.xml";
    private SqlSessionFactory sessionFactory;

    public SessionFactory() {
        this(DEFAULT_RESOURCE);
    }

    public SessionFactory(String resource) {
        try {
            Reader reader = Resources.getResourceAsReader(
                    resource);

            sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        } catch (Exception e) {
            throw new DAOException("Error al crear la fabrica de sesiones", e);
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sessionFactory;
    }

    public void destroy() {
        sessionFactory = null;
    }
}
