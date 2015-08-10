package com.poc.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que contém todos os parâmetros necessário para a renderização de mensagem de erro na tela, e uma mensagem específica de debug
 * 
 */
public abstract class PocBaseRuntimeException extends RuntimeException implements PocBaseException {

	/**
	 * Identificador serial de versão
	 */
	private static final long serialVersionUID = -4505537009016843067L;

	/**
	 * Lista de mensagens que serão exibidas
	 */
	private final List<PocExceptionMessage> messages = new ArrayList<PocExceptionMessage>();

	/**
	 * Construtor padrão, uma opção que não exige que os dados da mensagens sejam passados na hora da criação do objeto
	 */
	public PocBaseRuntimeException() {
	}

	/**
	 * Construtor padrão com mensagem como parâmetro
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Parâmetros da mensagem
	 */
	public PocBaseRuntimeException(final String key, final String debugMessage, final Object... params) {
		addMessage(key, debugMessage, params);
	}

	/**
	 * Construtor padrão com mensagem como parâmetro
	 * 
	 * @param message Mensagem que será adicionada
	 */
	public PocBaseRuntimeException(final PocExceptionMessage message) {
		addMessage(message);
	}

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Parâmetros da mensagem
	 */
	public void addMessage(final String key, final String debugMessage, final Object... params) {
		final PocExceptionMessage message = new PocExceptionMessage(key, debugMessage, params);
		messages.add(message);
	}

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param message Mensagem que será adicionada
	 */
	public void addMessage(final PocExceptionMessage message) {
		messages.add(message);
	}

	/**
	 * Método utilizado para verificar se a lista de mensagns está vazia
	 * 
	 * @return Retorna <code>true</code> se não existir nenhuma mensagem na lista, <code>fase</code> caso contrário
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