package com.poc.exception;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Objeto que contém todos os métodos necessário para a renderização de mensagem de erro na tela, e uma mensagem específica de debug
 * 
 * @author Sibe2
 */
public final class PocExceptionMessage implements Serializable {

	/**
	 * Versão do serial UID
	 */
	private static final long serialVersionUID = -3610027613552878130L;

	/**
	 * Chave da mensagem de erro no arquivo de parâmetros
	 */
	private final String key;

	/**
	 * Parâmetros da mensagem de erro
	 */
	private final Object[] params;

	/**
	 * Mensagem de debug
	 */
	private final String debugMessage;

	/**
	 * Construtor padrão
	 * 
	 * @param key Chave da mensagem de erro
	 * @param debugMessage Mensagem de debug
	 * @param params Parâmetros da mensagem
	 */
	public PocExceptionMessage(final String key, final String debugMessage, final Object... params) {

		if (key == null || key.equals("")) {
			throw new IllegalArgumentException("Key nao pode ser nulo.");
		}

		if (debugMessage == null || debugMessage.equals("")) {
			throw new IllegalArgumentException("Debug message nao pode ser nulo.");
		}

		this.key = key;
		this.params = params;
		this.debugMessage = debugMessage;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		Object[] result = null;
		if (params != null) {
			result = params.clone();
		}
		return result;
	}

	/**
	 * @return the debugMessage
	 */
	public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (key == null ? 0 : key.hashCode());
		result = prime * result + Arrays.hashCode(params);
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
		PocExceptionMessage other = (PocExceptionMessage) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (!Arrays.equals(params, other.params)) {
			return false;
		}
		return true;
	}
}