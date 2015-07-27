/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.security.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author cruiz
 */
public class ReporteCierreForm extends ValidatorForm {

    private String bin_procesador;
    private String binadquiriente;
    private String bin_autorizador;
    private String ind_term_trans;
    private String fechalogica;
    private String tip_flujo;
    private String des_flujo;
    private String cod_trans_flujo;
    private double nro_trans;
    private double importe;

    public ReporteCierreForm() {
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }

    public ReporteCierreForm(String bin_procesador, String binadquiriente, String bin_autorizador, String ind_term_trans, String fechalogica, String tip_flujo, String des_flujo, String cod_trans_flujo, double nro_trans, double importe) {
        this.bin_procesador = bin_procesador;
        this.binadquiriente = binadquiriente;
        this.bin_autorizador = bin_autorizador;
        this.ind_term_trans = ind_term_trans;
        this.fechalogica = fechalogica;
        this.tip_flujo = tip_flujo;
        this.des_flujo = des_flujo;
        this.cod_trans_flujo = cod_trans_flujo;
        this.nro_trans = nro_trans;
        this.importe = importe;
    }

    /**
     * @return the bin_procesador
     */
    public String getBin_procesador() {
        return bin_procesador;
    }

    /**
     * @param bin_procesador the bin_procesador to set
     */
    public void setBin_procesador(String bin_procesador) {
        this.bin_procesador = bin_procesador;
    }

    /**
     * @return the binadquiriente
     */
    public String getBinadquiriente() {
        return binadquiriente;
    }

    /**
     * @param binadquiriente the binadquiriente to set
     */
    public void setBinadquiriente(String binadquiriente) {
        this.binadquiriente = binadquiriente;
    }

    /**
     * @return the bin_autorizador
     */
    public String getBin_autorizador() {
        return bin_autorizador;
    }

    /**
     * @param bin_autorizador the bin_autorizador to set
     */
    public void setBin_autorizador(String bin_autorizador) {
        this.bin_autorizador = bin_autorizador;
    }

    /**
     * @return the ind_term_trans
     */
    public String getInd_term_trans() {
        return ind_term_trans;
    }

    /**
     * @param ind_term_trans the ind_term_trans to set
     */
    public void setInd_term_trans(String ind_term_trans) {
        this.ind_term_trans = ind_term_trans;
    }

    /**
     * @return the fechalogica
     */
    public String getFechalogica() {
        return fechalogica;
    }

    /**
     * @param fechalogica the fechalogica to set
     */
    public void setFechalogica(String fechalogica) {
        this.fechalogica = fechalogica;
    }

    /**
     * @return the tip_flujo
     */
    public String getTip_flujo() {
        return tip_flujo;
    }

    /**
     * @param tip_flujo the tip_flujo to set
     */
    public void setTip_flujo(String tip_flujo) {
        this.tip_flujo = tip_flujo;
    }

    /**
     * @return the des_flujo
     */
    public String getDes_flujo() {
        return des_flujo;
    }

    /**
     * @param des_flujo the des_flujo to set
     */
    public void setDes_flujo(String des_flujo) {
        this.des_flujo = des_flujo;
    }

    /**
     * @return the cod_trans_flujo
     */
    public String getCod_trans_flujo() {
        return cod_trans_flujo;
    }

    /**
     * @param cod_trans_flujo the cod_trans_flujo to set
     */
    public void setCod_trans_flujo(String cod_trans_flujo) {
        this.cod_trans_flujo = cod_trans_flujo;
    }

    /**
     * @return the nro_trans
     */
    public double getNro_trans() {
        return nro_trans;
    }

    /**
     * @param nro_trans the nro_trans to set
     */
    public void setNro_trans(double nro_trans) {
        this.nro_trans = nro_trans;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

}
