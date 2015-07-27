package com.novatronic.sca.security.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * Clase que contiene los componentes y atributos de la interfaz CambioClave.jsp
 */
public class CambioClaveForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nuevaClave;
	private String confirmarClave;
	private String claveActual;

	public CambioClaveForm() {
		nuevaClave="";
		confirmarClave="";
		claveActual="";
	}
	
	public String getNuevaClave() {
		return nuevaClave;
	}

	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

	public String getClaveActual() {
		return claveActual;
	}

	public void setClaveActual(String claveActual) {
		this.claveActual = claveActual;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return super.validate(mapping, request);
	}

}
