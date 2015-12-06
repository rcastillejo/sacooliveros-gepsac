
package edu.pe.sacoliveros.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for alumno complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alumno">
 *   &lt;complexContent>
 *     &lt;extension base="{http://app.sacoliveros.pe.edu/}model">
 *       &lt;sequence>
 *         &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contextura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="genero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoEscolar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivelEscolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroCambioColegio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numHnos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ordenNacimiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="promedioEscolar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="religion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoFamilia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "altura",
    "apellidoMaterno",
    "apellidoPaterno",
    "contextura",
    "departamento",
    "distrito",
    "domicilio",
    "edad",
    "genero",
    "gradoEscolar",
    "nacionalidad",
    "nivelEscolar",
    "nombres",
    "nroCambioColegio",
    "numHnos",
    "ordenNacimiento",
    "promedioEscolar",
    "provincia",
    "religion",
    "tipoFamilia"
})
public class Alumno
    extends Model
{

    protected String altura;
    protected String apellidoMaterno;
    protected String apellidoPaterno;
    protected String contextura;
    protected String departamento;
    protected String distrito;
    protected String domicilio;
    protected int edad;
    protected String genero;
    protected int gradoEscolar;
    protected String nacionalidad;
    protected String nivelEscolar;
    protected String nombres;
    protected int nroCambioColegio;
    protected int numHnos;
    protected int ordenNacimiento;
    protected double promedioEscolar;
    protected String provincia;
    protected String religion;
    protected String tipoFamilia;

    /**
     * Gets the value of the altura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltura() {
        return altura;
    }

    /**
     * Sets the value of the altura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltura(String value) {
        this.altura = value;
    }

    /**
     * Gets the value of the apellidoMaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Gets the value of the apellidoPaterno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Gets the value of the contextura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextura() {
        return contextura;
    }

    /**
     * Sets the value of the contextura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextura(String value) {
        this.contextura = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the distrito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Sets the value of the distrito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrito(String value) {
        this.distrito = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicilio(String value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the edad property.
     * 
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Sets the value of the edad property.
     * 
     */
    public void setEdad(int value) {
        this.edad = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
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
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nivelEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelEscolar() {
        return nivelEscolar;
    }

    /**
     * Sets the value of the nivelEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelEscolar(String value) {
        this.nivelEscolar = value;
    }

    /**
     * Gets the value of the nombres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets the value of the nombres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombres(String value) {
        this.nombres = value;
    }

    /**
     * Gets the value of the nroCambioColegio property.
     * 
     */
    public int getNroCambioColegio() {
        return nroCambioColegio;
    }

    /**
     * Sets the value of the nroCambioColegio property.
     * 
     */
    public void setNroCambioColegio(int value) {
        this.nroCambioColegio = value;
    }

    /**
     * Gets the value of the numHnos property.
     * 
     */
    public int getNumHnos() {
        return numHnos;
    }

    /**
     * Sets the value of the numHnos property.
     * 
     */
    public void setNumHnos(int value) {
        this.numHnos = value;
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
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the religion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReligion() {
        return religion;
    }

    /**
     * Sets the value of the religion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReligion(String value) {
        this.religion = value;
    }

    /**
     * Gets the value of the tipoFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoFamilia() {
        return tipoFamilia;
    }

    /**
     * Sets the value of the tipoFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoFamilia(String value) {
        this.tipoFamilia = value;
    }

}
