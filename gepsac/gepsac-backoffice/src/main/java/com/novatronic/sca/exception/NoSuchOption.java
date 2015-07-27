/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.exception;

import com.novatronic.sca.util.Constantes;

/**
 *
 * @author Marco
 */
public class NoSuchOption extends Exception{

    public NoSuchOption() {
        super(Constantes.NO_SUCH_OPTION);
    }
    
}
