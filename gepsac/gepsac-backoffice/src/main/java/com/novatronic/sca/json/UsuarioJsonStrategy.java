/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.util.Arrays;

/**
 *
 * @author Marco
 */
public class UsuarioJsonStrategy implements ExclusionStrategy{
    
    private String[] campos = new String[]{"contrasena","noCriptoContra"};

    public boolean shouldSkipField(FieldAttributes fa) {
        return (Arrays.asList(campos)).contains(fa.getName());
    }

    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }
}
