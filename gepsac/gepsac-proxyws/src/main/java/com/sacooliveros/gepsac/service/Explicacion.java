
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for explicacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="explicacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="evaluacionAcosoEscolar" type="{http://service.gepsac.sacooliveros.com/}evaluacionAcosoEscolar" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "explicacion", propOrder = {
    "evaluacionAcosoEscolar"
})
public class Explicacion {

    protected EvaluacionAcosoEscolar evaluacionAcosoEscolar;

    /**
     * Gets the value of the evaluacionAcosoEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link EvaluacionAcosoEscolar }
     *     
     */
    public EvaluacionAcosoEscolar getEvaluacionAcosoEscolar() {
        return evaluacionAcosoEscolar;
    }

    /**
     * Sets the value of the evaluacionAcosoEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvaluacionAcosoEscolar }
     *     
     */
    public void setEvaluacionAcosoEscolar(EvaluacionAcosoEscolar value) {
        this.evaluacionAcosoEscolar = value;
    }

}
