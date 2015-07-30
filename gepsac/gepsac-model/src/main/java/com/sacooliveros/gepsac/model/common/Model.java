/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Model implements Serializable{

    private String codigo;
    private Date fecCre;
    private String usuCre;
    private Date fecMod;
    private String usuMod;
    private String estado;

    public Model() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecCre() {
        return fecCre;
    }

    public void setFecCre(Date fecCre) {
        this.fecCre = fecCre;
    }

    public String getUsuCre() {
        return usuCre;
    }

    public void setUsuCre(String usuCre) {
        this.usuCre = usuCre;
    }

    public Date getFecMod() {
        return fecMod;
    }

    public void setFecMod(Date fecMod) {
        this.fecMod = fecMod;
    }

    public String getUsuMod() {
        return usuMod;
    }

    public void setUsuMod(String usuMod) {
        this.usuMod = usuMod;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Model{" + "codigo=" + codigo + ", fecCre=" + fecCre + ", usuCre=" + usuCre + ", fecMod=" + fecMod + ", usuMod=" + usuMod + ", estado=" + estado + '}';
    }

}
