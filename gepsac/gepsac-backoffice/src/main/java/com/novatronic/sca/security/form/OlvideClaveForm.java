package com.novatronic.sca.security.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

//import com.novatronic.pscabas.core.model.DataAdapter;

/**
 *
 * Clase que contiene los componentes y atributos de la interfaz OlvideClave.jsp
 */
public class OlvideClaveForm extends ValidatorForm{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String numeroDocumento;
	private String correo;
	private String tipoDocumento;

	public OlvideClaveForm() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return super.validate(mapping, request);
	}

}
