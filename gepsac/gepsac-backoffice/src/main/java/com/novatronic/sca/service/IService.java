/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.novatronic.sca.service;

import java.util.List;

/**
 *
 * @author rcastillejo
 * @param <T>
 */
public interface IService<T> {
    
    List<T> listar(); 
    List<T> buscar(String... params);
    String ingresar(T model);
    String actualizar(T model);
    T obtener(String id);
    String elimimar(T model);
}
