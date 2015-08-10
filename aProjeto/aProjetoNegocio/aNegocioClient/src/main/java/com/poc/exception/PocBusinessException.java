package com.poc.exception;

import javax.ejb.ApplicationException;

/**
 * Classe utilizada para lan�amento de exce��es referente ao neg�cio das aplica��es J2EE. Se refere somente ao neg�cio e portanto n�o deve ser usada para infra
 * e para tratamento de camadas.
 * 
 * @author d337277 Daniel Xavier Ara�jo (daniel.xavier@dataprev.gov.br)
 */
@ApplicationException(rollback = true)
public class PocBusinessException extends PocBaseBusinessException {

	/**
	 * Identificador serial de vers�o
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