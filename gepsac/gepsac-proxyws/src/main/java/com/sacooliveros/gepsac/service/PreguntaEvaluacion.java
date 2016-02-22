
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preguntaEvaluacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preguntaEvaluacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEvaluacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ordenEvaluacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pregunta" type="{http://service.gepsac.sacooliveros.com/}pregunta" minOccurs="0"/>
 *         &lt;element name="regla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preguntaEvaluacion", propOrder = {
    "codigoEvaluacion",
    "ordenEvaluacion",
    "pregunta",
    "regla",
    "respuesta"
})
public class PreguntaEvaluacion {

    protected String codigoEvaluacion;
    protected int ordenEvaluacion;
    protected Pregunta pregunta;
    protected String regla;
    protected String respuesta;

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
     * Gets the value of the ordenEvaluacion property.
     * 
     */
    public int getOrdenEvaluacion() {
        return ordenEvaluacion;
    }

    /**
     * Sets the value of the ordenEvaluacion property.
     * 
     */
    public void setOrdenEvaluacion(int value) {
        this.ordenEvaluacion = value;
    }

    /**
     * Gets the value of the pregunta property.
     * 
     * @return
     *     possible object is
     *     {@link Pregunta }
     *     
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Sets the value of the pregunta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pregunta }
     *     
     */
    public void setPregunta(Pregunta value) {
        this.pregunta = value;
    }

    /**
     * Gets the value of the regla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegla() {
        return regla;
    }

    /**
     * Sets the value of the regla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegla(String value) {
        this.regla = value;
    }

    /**
     * Gets the value of the respuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespuesta(String value) {
        this.respuesta = value;
    }

}
