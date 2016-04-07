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
public class AlumnoAtributtesUtils {

    public static final String CODIGO_PREFIX = "A20150000";
    public static final String CODIGO_PREFIX_POSTULANTE = "A20160000";

    private static final String[] NOMBRES_MASCULINOS = {"Luis Ricardo", "Braulia Andrea", "Jose Alberto", "Jose Luis", "Braulio Sergio", "Raúl Leonardo"};
    private static final String[] NOMBRES_FEMENINOS = {"Luis Ricardo", "Braulia Andrea", "Jose Alberto", "Jose Luis", "Braulio Sergio", "Raúl Leonardo"};
    private static final String[] APELLIDOS = {"Castillejo", "Castillo", "Jose ", "Gálvez", "Cornejo", "Herrera", "Jimenez"};
    private static final String[] DOMICILIOS = {"Manco Segundo 333", "Gálvez Barrenechea 515", "Univertiaria 550", "Bolivar 1510"};
    private static final String[] GENERO = {"Masculino", "Femenino"};
    private static final String[] CONTEXTURA = {"Grande", "Mediano", "Pequeño"};
    private static final String[] ESTATURA = {"Alto", "Medio", "Bajo"};
    private static final String[] TIPO_FAMILIA = {"Nuclear", "Monoparental", "Extensa", "Esamblada"};
    private static final String[] NIVEL_ESCOLAR = {"Secundaria", "Primaria"};
    private static final String[] RELIGION = {"Católico", "Evangélico", "Mormón"};
    private static final String[] NACIONALIDAD = {"Peruano"};
    private static final String[] DISTRITO = {"Lince", "La Victoria", "Jesus María", "Breña", "Pueblo Libre"};
    private static final String[] PROVINCIA = {"Lima"};
    private static final String[] DEPARTAMENTO = {"Lima"};

    public static String getCodigo(String codigo, int index) {
        return codigo + index;
    }

    public static int getNumHnos(int ordenNacimiento) {
        return ordenNacimiento == 1 ? 0 : getInt(1, 4);
    }

    public static int getGradoEscolar(String nivelEscolar) {
        return nivelEscolar.equals(NIVEL_ESCOLAR[0]) ? getInt(1, 5) : getInt(1, 6);
    }

    public static String getDepartamento() {
        return getValue(DEPARTAMENTO);
    }

    public static String getProvincia() {
        return getValue(PROVINCIA);
    }

    public static String getDistrito() {
        return getValue(DISTRITO);
    }

    public static String getNacionalidad() {
        return getValue(NACIONALIDAD);
    }

    public static String getReligion() {
        return getValue(RELIGION);
    }

    public static String getNivelEscolar() {
        return getValue(NIVEL_ESCOLAR);
    }

    public static String getTipoFamilia() {
        return getValue(TIPO_FAMILIA);
    }

    public static String getEstatura() {
        return getValue(ESTATURA);
    }

    public static String getContextura() {
        return getValue(CONTEXTURA);
    }

    public static String getDomicilio() {
        return getValue(DOMICILIOS);
    }

    public static String getApellidos() {
        return getValue(APELLIDOS);
    }

    public static String getGenero() {
        return getValue(GENERO);
    }

    public static String getNombre(String genero) {
        return genero.equals(GENERO[0]) ? getNombreMasculino() : getNombreFemenino();
    }

    public static String getNombreMasculino() {
        return getValue(NOMBRES_MASCULINOS);
    }

    public static String getNombreFemenino() {
        return getValue(NOMBRES_FEMENINOS);
    }

    private static String getValue(String[] values) {
        return values[getInt(0, values.length - 1)];
    }

    public static int getInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
