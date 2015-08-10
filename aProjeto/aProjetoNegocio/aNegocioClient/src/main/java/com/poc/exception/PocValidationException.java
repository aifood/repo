package com.poc.exception;

import javax.ejb.ApplicationException;

import org.apache.commons.collections.CollectionUtils;

/**
 * Classe utilizada para lançamento de exceções referente à validações. Se
 * refere somente à validações e portanto não deve ser usada para infra e para
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
	 * Construtor padrão
	 */
	public PocValidationException() {
		super();
	}

	/**
	 * Construtor a partir de uma instancia de {@link PocExceptionMessage}
	 * 
	 * @param message
	 *            Mensagem da exceção
	 */
	public PocValidationException(PocExceptionMessage message) {
		super(message);
	}

	/**
	 * Construtor que ira resolver a mensagem da exceção
	 * 
	 * @param key
	 *            Chave da mensagem no arquivo de mensagens
	 * @param debugMessage
	 *            Descrição da mensagem
	 * @param params
	 *            Parâmetros da mensagem
	 */
	public PocValidationException(String key, String debugMessage,
			Object... params) {
		super(key, debugMessage, params);
	}

	/**
	 * @return <code>true</code> se tem mensagens, <code>false</code> caso
	 *         contrário.
	 */
	public boolean hasMessages() {
		return CollectionUtils.isNotEmpty(getMessages());
	}
}