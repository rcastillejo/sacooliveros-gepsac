/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.message;

/**
 *
 * @author Ricardo
 */
public class Mensaje {

    private String id;
    private Object request;
    private String response;
    private long init;
    private long end;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void start() {
        init = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long getInit() {
        return init;
    }

    public long getEnd() {
        return end;
    }
    

    @Override
    public String toString() {
        return "EvaluadorModel{" + "id=" + id + ", evaluaciones=" + request + ", response=" + response + '}';
    }

}
