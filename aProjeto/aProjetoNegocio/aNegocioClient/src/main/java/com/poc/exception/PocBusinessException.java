package com.poc.exception;

import javax.ejb.ApplicationException;

/**
 * Classe utilizada para lançamento de exceções referente ao negócio das aplicações J2EE. Se refere somente ao negócio e portanto não deve ser usada para infra
 * e para tratamento de camadas.
 * 
 * @author d337277 Daniel Xavier Araújo (daniel.xavier@dataprev.gov.br)
 */
@ApplicationException(rollback = true)
public class PocBusinessException extends PocBaseBusinessException {

	/**
	 * Identificador serial de versão
	 */
	private static final long serialVersionUID = 114975353851811731L;

	public PocBusinessException() {
		super();
	}

	public PocBusinessException(PocExceptionMessage message) {
		super(message);
	}

	public PocBusinessException(String key, String debugMessage, Object... params) {
		super(key, debugMessage, params);
	}
	
}