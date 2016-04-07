
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preguntaRegla complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preguntaRegla">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoRegla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pregunta" type="{http://service.gepsac.sacooliveros.com/}pregunta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preguntaRegla", propOrder = {
    "codigoRegla",
    "pregunta"
})
public class PreguntaRegla {

    protected String codigoRegla;
    protected Pregunta pregunta;

    /**
     * Gets the value of the codigoRegla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRegla() {
        return codigoRegla;
    }

    /**
     * Sets the value of the codigoRegla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRegla(String value) {
        this.codigoRegla = value;
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

}
