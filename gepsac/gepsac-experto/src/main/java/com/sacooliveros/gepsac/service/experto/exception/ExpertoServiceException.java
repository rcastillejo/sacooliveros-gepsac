/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.exception;

import java.text.MessageFormat;

/**
 *
 * @author Ricardo
 */
public class ExpertoServiceException extends RuntimeException{

    private final String code;

    public ExpertoServiceException(String code) {
        this.code = code;
    }

    public ExpertoServiceException(String code, String message, Object... args) {
        super(MessageFormat.format(message, args));
        this.code = code;
    }

    public ExpertoServiceException(String code, String message, Throwable cause, Object... args) {
        super(MessageFormat.format(message, args), cause);
        this.code = code;
    }

    public ExpertoServiceException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
        
}
