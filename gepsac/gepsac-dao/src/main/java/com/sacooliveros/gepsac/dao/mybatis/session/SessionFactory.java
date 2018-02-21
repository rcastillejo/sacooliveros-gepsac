package com.sacooliveros.gepsac.dao.mybatis.session;

import com.sacooliveros.gepsac.dao.exception.DAOException;
import java.io.Reader;
import java.util.Collection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactory {

    private static final Logger log = LoggerFactory.getLogger(SessionFactory.class);
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

            log.debug("Fabrica de sesiones MyBatis creado.");

            Configuration config = sessionFactory.getConfiguration();
            log.trace("Configuracion mybatis [{}]", config);
            if (config != null) {
                Collection<String> parameterNames = config.getParameterMapNames();
                log.trace("Configuracion mybatis parameterNames [{}]", parameterNames);
                for (String name : parameterNames) {
                    log.trace("Parametros [{}={}]", new Object[]{name, sessionFactory.getConfiguration().getParameterMap(name)});
                }
            }

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
