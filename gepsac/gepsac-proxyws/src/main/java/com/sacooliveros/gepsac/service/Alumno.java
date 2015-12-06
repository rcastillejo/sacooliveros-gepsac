
package com.sacooliveros.gepsac.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for alumno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alumno">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.gepsac.sacooliveros.com/}participante">
 *       &lt;sequence>
 *         &lt;element name="cantCambioColegio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantHnos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contextura" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distrito" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="estatura" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gradoEscolar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nacionalidad" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="nivelEscolar" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="ordenNacimiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="perfil" type="{http://service.gepsac.sacooliveros.com/}perfil" minOccurs="0"/>
 *         &lt;element name="promedioEscolar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="provincia" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="religion" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *         &lt;element name="tipoFamilia" type="{http://service.gepsac.sacooliveros.com/}entidad" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alumno", propOrder = {
    "cantCambioColegio",
    "cantHnos",
    "contextura",
    "departamento",
    "direccion",
    "distrito",
    "estatura",
    "fechaNacimiento",
    "gradoEscolar",
    "lugarNacimiento",
    "nacionalidad",
    "nivelEscolar",
    "ordenNacimiento",
    "perfil",
    "promedioEscolar",
    "provincia",
    "religion",
    "sexo",
    "tipoFamilia"
})
public class Alumno
    extends Participante
{

    protected int cantCambioColegio;
    protected int cantHnos;
    protected Entidad contextura;
    protected Entidad departamento;
    protected String direccion;
    protected Entidad distrito;
    protected Entidad estatura;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNacimiento;
    protected int gradoEscolar;
    protected String lugarNacimiento;
    protected Entidad nacionalidad;
    protected Entidad nivelEscolar;
    protected int ordenNacimiento;
    protected Perfil perfil;
    protected double promedioEscolar;
    protected Entidad provincia;
    protected Entidad religion;
    protected Entidad sexo;
    protected Entidad tipoFamilia;

    /**
     * Gets the value of the cantCambioColegio property.
     * 
     */
    public int getCantCambioColegio() {
        return cantCambioColegio;
    }

    /**
     * Sets the value of the cantCambioColegio property.
     * 
     */
    public void setCantCambioColegio(int value) {
        this.cantCambioColegio = value;
    }

    /**
     * Gets the value of the cantHnos property.
     * 
     */
    public int getCantHnos() {
        return cantHnos;
    }

    /**
     * Sets the value of the cantHnos property.
     * 
     */
    public void setCantHnos(int value) {
        this.cantHnos = value;
    }

    /**
     * Gets the value of the contextura property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getContextura() {
        return contextura;
    }

    /**
     * Sets the value of the contextura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setContextura(Entidad value) {
        this.contextura = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setDepartamento(Entidad value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the distrito property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getDistrito() {
        return distrito;
    }

    /**
     * Sets the value of the distrito property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setDistrito(Entidad value) {
        this.distrito = value;
    }

    /**
     * Gets the value of the estatura property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getEstatura() {
        return estatura;
    }

    /**
     * Sets the value of the estatura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setEstatura(Entidad value) {
        this.estatura = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaNacimiento(XMLGregorianCalendar value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the gradoEscolar property.
     * 
     */
    public int getGradoEscolar() {
        return gradoEscolar;
    }

    /**
     * Sets the value of the gradoEscolar property.
     * 
     */
    public void setGradoEscolar(int value) {
        this.gradoEscolar = value;
    }

    /**
     * Gets the value of the lugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Sets the value of the lugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarNacimiento(String value) {
        this.lugarNacimiento = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setNacionalidad(Entidad value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nivelEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getNivelEscolar() {
        return nivelEscolar;
    }

    /**
     * Sets the value of the nivelEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setNivelEscolar(Entidad value) {
        this.nivelEscolar = value;
    }

    /**
     * Gets the value of the ordenNacimiento property.
     * 
     */
    public int getOrdenNacimiento() {
        return ordenNacimiento;
    }

    /**
     * Sets the value of the ordenNacimiento property.
     * 
     */
    public void setOrdenNacimiento(int value) {
        this.ordenNacimiento = value;
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
     * Gets the value of the promedioEscolar property.
     * 
     */
    public double getPromedioEscolar() {
        return promedioEscolar;
    }

    /**
     * Sets the value of the promedioEscolar property.
     * 
     */
    public void setPromedioEscolar(double value) {
        this.promedioEscolar = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setProvincia(Entidad value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the religion property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getReligion() {
        return religion;
    }

    /**
     * Sets the value of the religion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setReligion(Entidad value) {
        this.religion = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setSexo(Entidad value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the tipoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link Entidad }
     *     
     */
    public Entidad getTipoFamilia() {
        return tipoFamilia;
    }

    /**
     * Sets the value of the tipoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entidad }
     *     
     */
    public void setTipoFamilia(Entidad value) {
        this.tipoFamilia = value;
    }

}
