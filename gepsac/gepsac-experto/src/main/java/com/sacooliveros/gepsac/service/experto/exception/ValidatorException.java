/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.exception;

/**
 *
 * @author Ricardo
 */
public class ValidatorException extends ServiceException {

    public ValidatorException(String code) {
        super(code);
    }

    public ValidatorException(String code, String message) {
        super(code, message);
    }

    public ValidatorException(String code, String message, Object... args) {
        super(code, message, args);
    }

    public ValidatorException(String code, String message, Throwable cause, Object... args) {
        super(code, message, cause, args);
    }

    public ValidatorException(String code, Throwable cause) {
        super(code, cause);
    }

}
