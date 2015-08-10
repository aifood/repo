package com.poc.exception;

import javax.ejb.ApplicationException;

import org.apache.commons.collections.CollectionUtils;

/**
 * Classe utilizada para lan�amento de exce��es referente � valida��es. Se
 * refere somente � valida��es e portanto n�o deve ser usada para infra e para
 * tratamento de camadas.
 * 
 */
@ApplicationException(rollback = true)
public class PocValidationException extends PocBaseRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7046103525477328972L;

	/**
	 * Construtor padr�o
	 */
	public PocValidationException() {
		super();
	}

	/**
	 * Construtor a partir de uma instancia de {@link PocExceptionMessage}
	 * 
	 * @param message
	 *            Mensagem da exce��o
	 */
	public PocValidationException(PocExceptionMessage message) {
		super(message);
	}

	/**
	 * Construtor que ira resolver a mensagem da exce��o
	 * 
	 * @param key
	 *            Chave da mensagem no arquivo de mensagens
	 * @param debugMessage
	 *            Descri��o da mensagem
	 * @param params
	 *            Par�metros da mensagem
	 */
	public PocValidationException(String key, String debugMessage,
			Object... params) {
		super(key, debugMessage, params);
	}

	/**
	 * @return <code>true</code> se tem mensagens, <code>false</code> caso
	 *         contr�rio.
	 */
	public boolean hasMessages() {
		return CollectionUtils.isNotEmpty(getMessages());
	}
}