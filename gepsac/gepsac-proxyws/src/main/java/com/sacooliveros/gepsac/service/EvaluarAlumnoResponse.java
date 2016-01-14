
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for evaluarAlumnoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evaluarAlumnoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.gepsac.sacooliveros.com/}evaluacionPostulante" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evaluarAlumnoResponse", propOrder = {
    "_return"
})
public class EvaluarAlumnoResponse {

    @XmlElement(name = "return")
    protected EvaluacionPostulante _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link EvaluacionPostulante }
     *     
     */
    public EvaluacionPostulante getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvaluacionPostulante }
     *     
     */
    public void setReturn(EvaluacionPostulante value) {
        this._return = value;
    }

}
