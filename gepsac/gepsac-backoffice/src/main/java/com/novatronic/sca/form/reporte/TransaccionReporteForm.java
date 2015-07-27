/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.form.reporte;

import com.novatronic.sca.model.Transaccion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author rcastillejo
 */
public class TransaccionReporteForm extends ActionForm {

    /**
     * Parametros de busqueda
     */
    private Map<String, String> filtro = new TreeMap<String, String>();
    private String campo1;
    private String campo2;
    private String campo3;
    private String campo4;
    private LabelValueBean item = null;
    private List itemList = null;
    private String selectedItem;

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public TransaccionReporteForm() {
    }

    public LabelValueBean getItem() {
        return item;
    }

    public void setItem(LabelValueBean item) {
        this.item = item;
    }

    public List<LabelValueBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<LabelValueBean> itemList) {
        this.itemList = itemList;
    }
    /**
     * Resultado
     */

    private List<Transaccion> listado;
    private Map resultado;
    /**
     * Mensajes Satisfactorio
     */
    private String mensaje;
    /**
     * Mensajes de Error
     */
    private String mensajeError;

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public String getCampo4() {
        return campo4;
    }

    public void setCampo4(String campo4) {
        this.campo4 = campo4;
    }

    public List<Transaccion> getListado() {
        return listado;
    }

    public void setListado(List<Transaccion> listado) {
        this.listado = listado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Map<String, String> getFiltro() {
        System.out.println("getFiltro");
        return filtro;
    }

    public void setFiltro(String key, String value) {
        System.out.println("setFiltro param");
        filtro.put(key, value);
    }

    public void getFiltro(String key) {
        System.out.println("getFiltro key");
        filtro.get(key);
    }

    public Map getResultado() {
        return resultado;
    }

    public void setResultado(Map resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "TransaccionReporteForm{" + "filtro=" + filtro + ", campo1=" + campo1 + ", campo2=" + campo2 + ", campo3=" + campo3 + ", campo4=" + campo4 + ", listado=" + listado + ", resultado=" + resultado + ", mensaje=" + mensaje + ", mensajeError=" + mensajeError + '}';
    }

}
