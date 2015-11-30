
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for evaluarAlumno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evaluarAlumno">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="evaluacionPostulante" type="{http://service.gepsac.sacooliveros.com/}evaluacionPostulante" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evaluarAlumno", propOrder = {
    "evaluacionPostulante"
})
public class EvaluarAlumno {

    protected EvaluacionPostulante evaluacionPostulante;

    /**
     * Gets the value of the evaluacionPostulante property.
     * 
     * @return
     *     possible object is
     *     {@link EvaluacionPostulante }
     *     
     */
    public EvaluacionPostulante getEvaluacionPostulante() {
        return evaluacionPostulante;
    }

    /**
     * Sets the value of the evaluacionPostulante property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvaluacionPostulante }
     *     
     */
    public void setEvaluacionPostulante(EvaluacionPostulante value) {
        this.evaluacionPostulante = value;
    }

}
