/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.novatronic.sca.dao;

/**
 *
 * @author rcastillejo
 */
public class MockDAOFactory extends DAOFactory{

    public MockDAOFactory() {
    }
    
    @Override
    public TransaccionDAO getTransaccionDAO() {
        return new TransaccionMockDAO();
    }
    
}
