package com.br.crud.rest.example.usuario;

public class UsuarioNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7562967016796437540L;
	
	public UsuarioNotFoundException(String exception) {
		super(exception);
	}
}
