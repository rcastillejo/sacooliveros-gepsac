/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pe.sacoliveros.app;

/**
 *
 * @author Ricardo
 */
public class Alumno {

    private String codigo;

    //Datos generales
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private String telefono;
    private String email;
    private String tipo;
    private String domicilio;

    //Atributos a clasificar
    private String genero;
    private String contextura;
    private String altura;
    private String tipoFamilia;
    private int ordenNacimiento;
    private int numHnos;
    private String nivelEscolar;
    private int gradoEscolar;
    private double promedioEscolar;
    private int nroCambioColegio;
    private String religion;
    private String nacionalidad;
    private String distrito;
    private String provincia;
    private String departamento;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContextura() {
        return contextura;
    }

    public void setContextura(String contextura) {
        this.contextura = contextura;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(String tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public int getOrdenNacimiento() {
        return ordenNacimiento;
    }

    public void setOrdenNacimiento(int ordenNacimiento) {
        this.ordenNacimiento = ordenNacimiento;
    }

    public int getNumHnos() {
        return numHnos;
    }

    public void setNumHnos(int numHnos) {
        this.numHnos = numHnos;
    }

    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public int getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(int gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public double getPromedioEscolar() {
        return promedioEscolar;
    }

    public void setPromedioEscolar(double promedioEscolar) {
        this.promedioEscolar = promedioEscolar;
    }

    public int getNroCambioColegio() {
        return nroCambioColegio;
    }

    public void setNroCambioColegio(int nroCambioColegio) {
        this.nroCambioColegio = nroCambioColegio;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", domicilio=" + domicilio + ", genero=" + genero + ", edad=" + edad + ", contextura=" + contextura + ", altura=" + altura + ", tipoFamilia=" + tipoFamilia + ", ordenNacimiento=" + ordenNacimiento + ", numHnos=" + numHnos + ", nivelEscolar=" + nivelEscolar + ", gradoEscolar=" + gradoEscolar + ", promedioEscolar=" + promedioEscolar + ", nroCambioColegio=" + nroCambioColegio + ", religion=" + religion + ", nacionalidad=" + nacionalidad + ", distrito=" + distrito + ", provincia=" + provincia + ", departamento=" + departamento + '}';
    }

}
