
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preguntaEvaluacionAlternativa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preguntaEvaluacionAlternativa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alternativa" type="{http://service.gepsac.sacooliveros.com/}alternativa" minOccurs="0"/>
 *         &lt;element name="codigoEvaluacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoPregunta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "preguntaEvaluacionAlternativa", propOrder = {
    "alternativa",
    "codigoEvaluacion",
    "codigoPregunta",
    "seleccionado"
})
public class PreguntaEvaluacionAlternativa {

    protected Alternativa alternativa;
    protected String codigoEvaluacion;
    protected String codigoPregunta;
    protected boolean seleccionado;

    /**
     * Gets the value of the alternativa property.
     * 
     * @return
     *     possible object is
     *     {@link Alternativa }
     *     
     */
    public Alternativa getAlternativa() {
        return alternativa;
    }

    /**
     * Sets the value of the alternativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Alternativa }
     *     
     */
    public void setAlternativa(Alternativa value) {
        this.alternativa = value;
    }

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
     * Gets the value of the codigoPregunta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPregunta() {
        return codigoPregunta;
    }

    /**
     * Sets the value of the codigoPregunta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPregunta(String value) {
        this.codigoPregunta = value;
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
