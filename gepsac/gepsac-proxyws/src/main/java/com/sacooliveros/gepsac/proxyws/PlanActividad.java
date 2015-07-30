
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for planActividad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="planActividad">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.gepsac.sacooliveros.com/}estrategiaActividad">
 *       &lt;sequence>
 *         &lt;element name="fechaEjecutada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaProgramada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="meta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planActividad", propOrder = {
    "fechaEjecutada",
    "fechaProgramada",
    "meta",
    "programado"
})
public class PlanActividad
    extends EstrategiaActividad
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEjecutada;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaProgramada;
    protected int meta;
    protected boolean programado;

    /**
     * Gets the value of the fechaEjecutada property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEjecutada() {
        return fechaEjecutada;
    }

    /**
     * Sets the value of the fechaEjecutada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEjecutada(XMLGregorianCalendar value) {
        this.fechaEjecutada = value;
    }

    /**
     * Gets the value of the fechaProgramada property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaProgramada() {
        return fechaProgramada;
    }

    /**
     * Sets the value of the fechaProgramada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaProgramada(XMLGregorianCalendar value) {
        this.fechaProgramada = value;
    }

    /**
     * Gets the value of the meta property.
     * 
     */
    public int getMeta() {
        return meta;
    }

    /**
     * Sets the value of the meta property.
     * 
     */
    public void setMeta(int value) {
        this.meta = value;
    }

    /**
     * Gets the value of the programado property.
     * 
     */
    public boolean isProgramado() {
        return programado;
    }

    /**
     * Sets the value of the programado property.
     * 
     */
    public void setProgramado(boolean value) {
        this.programado = value;
    }

}
