
package com.sacooliveros.gepsac.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for regla complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="regla">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.gepsac.sacooliveros.com/}model">
 *       &lt;sequence>
 *         &lt;element name="deshabilitado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="perfil" type="{http://service.gepsac.sacooliveros.com/}perfil" minOccurs="0"/>
 *         &lt;element name="preguntas" type="{http://service.gepsac.sacooliveros.com/}preguntaRegla" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regla", propOrder = {
    "deshabilitado",
    "perfil",
    "preguntas"
})
public class Regla
    extends Model
{

    protected boolean deshabilitado;
    protected Perfil perfil;
    @XmlElement(nillable = true)
    protected List<PreguntaRegla> preguntas;

    /**
     * Gets the value of the deshabilitado property.
     * 
     */
    public boolean isDeshabilitado() {
        return deshabilitado;
    }

    /**
     * Sets the value of the deshabilitado property.
     * 
     */
    public void setDeshabilitado(boolean value) {
        this.deshabilitado = value;
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
     * Gets the value of the preguntas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preguntas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreguntas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreguntaRegla }
     * 
     * 
     */
    public List<PreguntaRegla> getPreguntas() {
        if (preguntas == null) {
            preguntas = new ArrayList<PreguntaRegla>();
        }
        return this.preguntas;
    }

}
