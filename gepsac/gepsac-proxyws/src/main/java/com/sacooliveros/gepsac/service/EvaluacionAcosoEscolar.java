
package com.sacooliveros.gepsac.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for evaluacionAcosoEscolar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evaluacionAcosoEscolar">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.gepsac.sacooliveros.com/}model">
 *       &lt;sequence>
 *         &lt;element name="alumno" type="{http://service.gepsac.sacooliveros.com/}alumno" minOccurs="0"/>
 *         &lt;element name="fechaEvaluacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="perfil" type="{http://service.gepsac.sacooliveros.com/}perfil" minOccurs="0"/>
 *         &lt;element name="preguntas" type="{http://service.gepsac.sacooliveros.com/}preguntaEvaluacion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evaluacionAcosoEscolar", propOrder = {
    "alumno",
    "fechaEvaluacion",
    "perfil",
    "preguntas"
})
public class EvaluacionAcosoEscolar
    extends Model
{

    protected Alumno alumno;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEvaluacion;
    protected Perfil perfil;
    @XmlElement(nillable = true)
    protected List<PreguntaEvaluacion> preguntas;

    /**
     * Gets the value of the alumno property.
     * 
     * @return
     *     possible object is
     *     {@link Alumno }
     *     
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Sets the value of the alumno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Alumno }
     *     
     */
    public void setAlumno(Alumno value) {
        this.alumno = value;
    }

    /**
     * Gets the value of the fechaEvaluacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    /**
     * Sets the value of the fechaEvaluacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEvaluacion(XMLGregorianCalendar value) {
        this.fechaEvaluacion = value;
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
     * {@link PreguntaEvaluacion }
     * 
     * 
     */
    public List<PreguntaEvaluacion> getPreguntas() {
        if (preguntas == null) {
            preguntas = new ArrayList<PreguntaEvaluacion>();
        }
        return this.preguntas;
    }

}
