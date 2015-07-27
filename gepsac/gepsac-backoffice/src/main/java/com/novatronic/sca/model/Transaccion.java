/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.novatronic.sca.model;

/**
 *
 * @author rcastillejo
 */
public class Transaccion {
    private String tipoMensaje;
    private String numeroTarjeta;
    private String numeroCelular;
    private int OperacionBM;
    private String codigoOperacion;
    private String descripcionOperacion;
    private String estadoOperacion;
    private String OperacionBanco;
    private String codigoTransaccion;
    private String fechaTransaccion;
    private String fechaOperacion;
    private String codigoResultado;
    private String resultadoOperacion;
    private String horaTransaccion;
    private double monto;
    private int trace;
    private String terminalId;

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getTrace() {
        return trace;
    }

    public void setTrace(int trace) {
        this.trace = trace;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the numeroCelular
     */
    public String getNumeroCelular() {
        return numeroCelular;
    }

    /**
     * @param numeroCelular the numeroCelular to set
     */
    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    

    /**
     * @return the codigoOperacion
     */
    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    /**
     * @param codigoOperacion the codigoOperacion to set
     */
    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    /**
     * @return the descripcionOperacion
     */
    public String getDescripcionOperacion() {
        return descripcionOperacion;
    }

    /**
     * @param descripcionOperacion the descripcionOperacion to set
     */
    public void setDescripcionOperacion(String descripcionOperacion) {
        this.descripcionOperacion = descripcionOperacion;
    }

    /**
     * @return the estadoOperacion
     */
    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    /**
     * @param estadoOperacion the estadoOperacion to set
     */
    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    /**
     * @return the OperacionBanco
     */
    public String getOperacionBanco() {
        return OperacionBanco;
    }

    /**
     * @param OperacionBanco the OperacionBanco to set
     */
    public void setOperacionBanco(String OperacionBanco) {
        this.OperacionBanco = OperacionBanco;
    }

    /**
     * @return the fechaOperacion
     */
    public String getFechaOperacion() {
        return fechaOperacion;
    }

    /**
     * @param fechaOperacion the fechaOperacion to set
     */
    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    /**
     * @return the codigoResultado
     */
    public String getCodigoResultado() {
        return codigoResultado;
    }

    /**
     * @param codigoResultado the codigoResultado to set
     */
    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    /**
     * @return the resultadoOperacion
     */
    public String getResultadoOperacion() {
        return resultadoOperacion;
    }

    /**
     * @param resultadoOperacion the resultadoOperacion to set
     */
    public void setResultadoOperacion(String resultadoOperacion) {
        this.resultadoOperacion = resultadoOperacion;
    }

    /**
     * @return the OperacionBM
     */
    public int getOperacionBM() {
        return OperacionBM;
    }

    /**
     * @param OperacionBM the OperacionBM to set
     */
    public void setOperacionBM(int OperacionBM) {
        this.OperacionBM = OperacionBM;
    }
    
    
    
}
