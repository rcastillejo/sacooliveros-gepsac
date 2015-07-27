package com.novatronic.sca.security.form;

import com.novatronic.pscabas.core.ws.LoginInput;
import com.novatronic.pscabas.core.ws.LoginOutput;
import org.apache.struts.validator.ValidatorActionForm;

//import com.novatronic.pscabas.core.ws.LoginInput;
//import com.novatronic.pscabas.core.ws.LoginOutput;

/**
 *
 * Clase que contiene los componentes y atributos de la interfaz Login.jsp
 */
public class LoginForm  extends ValidatorActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginInput input;
	private LoginOutput output;
	private String nuevaClave;
	private String confirmarClave;
	private String claveActual;
	
	//para cuando se olvida clave
	private String correo;
	public LoginForm() {
		this.input=new LoginInput();
		this.output=new LoginOutput();
	}
	public LoginInput getInput() {
		return input;
	}
	public void setInput(LoginInput input) {
		this.input = input;
	}
	public LoginOutput getOutput() {
		return output;
	}
	public void setOutput(LoginOutput output) {
		this.output = output;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginForm [input=");
		builder.append(input);
		builder.append(", output=");
		builder.append(output);
		builder.append(", nuevaClave=");
		builder.append(nuevaClave);
		builder.append(", confirmarClave=");
		builder.append(confirmarClave);
		builder.append(", claveActual=");
		builder.append(claveActual);
		builder.append(", correo=");
		builder.append(correo);
		builder.append("]");
		return builder.toString();
	}

	
}
