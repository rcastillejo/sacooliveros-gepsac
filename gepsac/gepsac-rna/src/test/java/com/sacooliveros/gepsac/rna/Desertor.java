/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.rna;

/**
 *
 * @author Ricardo
 */
public class Desertor {

    private int codigo;
    private int edad;
    private String genero;
    private String educacion;
    private double ingreso;
    private String reclamo;
    private int posesionTarjeta;
    private double montoPrestamo;
    private String estadoCuenta;
    private String estadoCredito;
    private int nroTransacciones;
    private double montoConsumo;
    private int cantidadTnx;
    private String desertor;

    public Desertor() {
    }

    public Desertor(int codigo, int edad, String genero, String educacion, double ingreso, String reclamo, int posesionTarjeta, double montoPrestamo, String estadoCuenta, String estadoCredito, int nroTransacciones, double montoConsumo, int cantidadTnx, String desertor) {
        this.codigo = codigo;
        this.edad = edad;
        this.genero = genero;
        this.educacion = educacion;
        this.ingreso = ingreso;
        this.reclamo = reclamo;
        this.posesionTarjeta = posesionTarjeta;
        this.montoPrestamo = montoPrestamo;
        this.estadoCuenta = estadoCuenta;
        this.estadoCredito = estadoCredito;
        this.nroTransacciones = nroTransacciones;
        this.montoConsumo = montoConsumo;
        this.cantidadTnx = cantidadTnx;
        this.desertor = desertor;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public String getReclamo() {
        return reclamo;
    }

    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    public int getPosesionTarjeta() {
        return posesionTarjeta;
    }

    public void setPosesionTarjeta(int posesionTarjeta) {
        this.posesionTarjeta = posesionTarjeta;
    }

    public double getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(String estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    public int getNroTransacciones() {
        return nroTransacciones;
    }

    public void setNroTransacciones(int nroTransacciones) {
        this.nroTransacciones = nroTransacciones;
    }

    public double getMontoConsumo() {
        return montoConsumo;
    }

    public void setMontoConsumo(double montoConsumo) {
        this.montoConsumo = montoConsumo;
    }

    public int getCantidadTnx() {
        return cantidadTnx;
    }

    public void setCantidadTnx(int cantidadTnx) {
        this.cantidadTnx = cantidadTnx;
    }

    public String getDesertor() {
        return desertor;
    }

    public void setDesertor(String desertor) {
        this.desertor = desertor;
    }

}
