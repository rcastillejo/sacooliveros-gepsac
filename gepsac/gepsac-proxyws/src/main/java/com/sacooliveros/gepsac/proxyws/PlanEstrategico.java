
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for planEstrategico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="planEstrategico">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.gepsac.sacooliveros.com/}documento">
 *       &lt;sequence>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fecApertura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecPlan" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hitos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planEstrategico", propOrder = {
    "anio",
    "fecApertura",
    "fecFin",
    "fecInicio",
    "fecPlan",
    "fecRegistro",
    "hitos",
    "titulo"
})
public class PlanEstrategico
    extends Documento
{

    protected int anio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecApertura;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecInicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecPlan;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecRegistro;
    protected String hitos;
    protected String titulo;

    /**
     * Gets the value of the anio property.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Gets the value of the fecApertura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecApertura() {
        return fecApertura;
    }

    /**
     * Sets the value of the fecApertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecApertura(XMLGregorianCalendar value) {
        this.fecApertura = value;
    }

    /**
     * Gets the value of the fecFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecFin() {
        return fecFin;
    }

    /**
     * Sets the value of the fecFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecFin(XMLGregorianCalendar value) {
        this.fecFin = value;
    }

    /**
     * Gets the value of the fecInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecInicio() {
        return fecInicio;
    }

    /**
     * Sets the value of the fecInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecInicio(XMLGregorianCalendar value) {
        this.fecInicio = value;
    }

    /**
     * Gets the value of the fecPlan property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecPlan() {
        return fecPlan;
    }

    /**
     * Sets the value of the fecPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecPlan(XMLGregorianCalendar value) {
        this.fecPlan = value;
    }

    /**
     * Gets the value of the fecRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecRegistro() {
        return fecRegistro;
    }

    /**
     * Sets the value of the fecRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecRegistro(XMLGregorianCalendar value) {
        this.fecRegistro = value;
    }

    /**
     * Gets the value of the hitos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHitos() {
        return hitos;
    }

    /**
     * Sets the value of the hitos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHitos(String value) {
        this.hitos = value;
    }

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

}
