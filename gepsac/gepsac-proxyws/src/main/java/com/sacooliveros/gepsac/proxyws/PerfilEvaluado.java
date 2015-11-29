
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for perfilEvaluado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="perfilEvaluado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEvaluacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perfil" type="{http://service.gepsac.sacooliveros.com/}perfil" minOccurs="0"/>
 *         &lt;element name="probabilidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="seleccionado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "perfilEvaluado", propOrder = {
    "codigoEvaluacion",
    "perfil",
    "probabilidad",
    "seleccionado"
})
public class PerfilEvaluado {

    protected String codigoEvaluacion;
    protected Perfil perfil;
    protected double probabilidad;
    protected boolean seleccionado;

    /**
     * Gets the value of the codigoEvaluacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEvaluacion() {
        return codigoEvaluacion;
    }

    /**
     * Sets the value of the codigoEvaluacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEvaluacion(String value) {
        this.codigoEvaluacion = value;
    }

    /**
     * Gets the value of the perfil property.
     * 
     * @return
     *     possible object is
     *     {@link Perfil }
     *     
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * Sets the value of the perfil property.
     * 
     * @param value
     *     allowed object is
     *     {@link Perfil }
     *     
     */
    public void setPerfil(Perfil value) {
        this.perfil = value;
    }

    /**
     * Gets the value of the probabilidad property.
     * 
     */
    public double getProbabilidad() {
        return probabilidad;
    }

    /**
     * Sets the value of the probabilidad property.
     * 
     */
    public void setProbabilidad(double value) {
        this.probabilidad = value;
    }

    /**
     * Gets the value of the seleccionado property.
     * 
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Sets the value of the seleccionado property.
     * 
     */
    public void setSeleccionado(boolean value) {
        this.seleccionado = value;
    }

}
