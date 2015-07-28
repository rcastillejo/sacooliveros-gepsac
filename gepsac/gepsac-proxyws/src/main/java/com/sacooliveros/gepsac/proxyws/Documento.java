
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for documento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fecCre" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecMod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuCre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documento", propOrder = {
    "estado",
    "fecCre",
    "fecMod",
    "numDoc",
    "usuCre",
    "usuMod"
})
@XmlSeeAlso({
    PlanEstrategico.class
})
public class Documento {

    protected int estado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecCre;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecMod;
    protected String numDoc;
    protected String usuCre;
    protected String usuMod;

    /**
     * Gets the value of the estado property.
     * 
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     */
    public void setEstado(int value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fecCre property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecCre() {
        return fecCre;
    }

    /**
     * Sets the value of the fecCre property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecCre(XMLGregorianCalendar value) {
        this.fecCre = value;
    }

    /**
     * Gets the value of the fecMod property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecMod() {
        return fecMod;
    }

    /**
     * Sets the value of the fecMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecMod(XMLGregorianCalendar value) {
        this.fecMod = value;
    }

    /**
     * Gets the value of the numDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDoc() {
        return numDoc;
    }

    /**
     * Sets the value of the numDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDoc(String value) {
        this.numDoc = value;
    }

    /**
     * Gets the value of the usuCre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuCre() {
        return usuCre;
    }

    /**
     * Sets the value of the usuCre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuCre(String value) {
        this.usuCre = value;
    }

    /**
     * Gets the value of the usuMod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuMod() {
        return usuMod;
    }

    /**
     * Sets the value of the usuMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuMod(String value) {
        this.usuMod = value;
    }

}
