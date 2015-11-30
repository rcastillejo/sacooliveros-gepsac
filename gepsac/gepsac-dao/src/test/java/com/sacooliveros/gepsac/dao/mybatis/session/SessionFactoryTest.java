/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao.mybatis.session;

import java.sql.Connection;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo
 */
public class SessionFactoryTest {
    
    public SessionFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSqlSessionFactory method, of class SessionFactory.
     */
    @Test
    public void testGetSqlSessionFactory() {
        System.out.println("getSqlSessionFactory");
        SessionFactory instance = new SessionFactory();
        SqlSessionFactory result = instance.getSqlSessionFactory();
        assertNotNull(result);
    }
    

    /**
     * Test of destroy method, of class SessionFactory.
     */
    @Test
    public void testDestroy() {
        System.out.println("destroy");
        SessionFactory instance = new SessionFactory();
        instance.destroy();
        assertNull(instance.getSqlSessionFactory());
    }
    
}
