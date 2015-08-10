package com.poc.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que cont�m todos os par�metros necess�rio para a renderiza��o de mensagem de erro na tela, e uma mensagem espec�fica de debug
 * 
 */
public abstract class PocBaseRuntimeException extends RuntimeException implements PocBaseException {

	/**
	 * Identificador serial de vers�o
	 */
	private static final long serialVersionUID = -4505537009016843067L;

	/**
	 * Lista de mensagens que ser�o exibidas
	 */
	private final List<PocExceptionMessage> messages = new ArrayList<PocExceptionMessage>();

	/**
	 * Construtor padr�o, uma op��o que n�o exige que os dados da mensagens sejam passados na hora da cria��o do objeto
	 */
	public PocBaseRuntimeException() {
	}

	/**
	 * Construtor padr�o com mensagem como par�metro
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Par�metros da mensagem
	 */
	public PocBaseRuntimeException(final String key, final String debugMessage, final Object... params) {
		addMessage(key, debugMessage, params);
	}

	/**
	 * Construtor padr�o com mensagem como par�metro
	 * 
	 * @param message Mensagem que ser� adicionada
	 */
	public PocBaseRuntimeException(final PocExceptionMessage message) {
		addMessage(message);
	}

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Par�metros da mensagem
	 */
	public void addMessage(final String key, final String debugMessage, final Object... params) {
		final PocExceptionMessage message = new PocExceptionMessage(key, debugMessage, params);
		messages.add(message);
	}

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param message Mensagem que ser� adicionada
	 */
	public void addMessage(final PocExceptionMessage message) {
		messages.add(message);
	}

	/**
	 * M�todo utilizado para verificar se a lista de mensagns est� vazia
	 * 
	 * @return Retorna <code>true</code> se n�o existir nenhuma mensagem na lista, <code>fase</code> caso contr�rio
	 */
	public boolean isEmpty() {
		return messages.isEmpty();
	}

	/**
	 * Retorna todas as mensagens
	 * 
	 * @return Retorna a lista de mensagens
	 */
	public List<PocExceptionMessage> getMessages() {
		return messages;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (messages == null ? 0 : messages.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PocBaseRuntimeException other = (PocBaseRuntimeException) obj;
		if (messages == null) {
			if (other.messages != null) {
				return false;
			}
		} else if (!messages.equals(other.messages)) {
			return false;
		}
		return true;
	}
}