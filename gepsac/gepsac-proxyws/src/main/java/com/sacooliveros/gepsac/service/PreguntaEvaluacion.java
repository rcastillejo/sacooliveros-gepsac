
package com.sacooliveros.gepsac.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="alternativas" type="{http://service.gepsac.sacooliveros.com/}preguntaEvaluacionAlternativa" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codigoEvaluacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "preguntaEvaluacion", propOrder = {
    "alternativas",
    "codigoEvaluacion",
    "pregunta"
})
public class PreguntaEvaluacion {

    @XmlElement(nillable = true)
    protected List<PreguntaEvaluacionAlternativa> alternativas;
    protected String codigoEvaluacion;
    protected Pregunta pregunta;

    /**
     * Gets the value of the alternativas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternativas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternativas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreguntaEvaluacionAlternativa }
     * 
     * 
     */
    public List<PreguntaEvaluacionAlternativa> getAlternativas() {
        if (alternativas == null) {
            alternativas = new ArrayList<PreguntaEvaluacionAlternativa>();
        }
        return this.alternativas;
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
