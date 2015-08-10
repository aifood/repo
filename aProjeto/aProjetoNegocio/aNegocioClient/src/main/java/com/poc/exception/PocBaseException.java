package com.poc.exception;

import java.io.Serializable;
import java.util.List;

/**
 * Interace que cont�m todos os m�todos necess�rio para a renderiza��o de mensagem de erro na tela, e uma mensagem espec�fica de debug
 * 
 */
public interface PocBaseException extends Serializable {

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Par�metros da mensagem
	 */
	public void addMessage(final String key, final String debugMessage, final Object... params);

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param message Mensagem que ser� adicionada
	 */
	public void addMessage(final PocExceptionMessage message);

	/**
	 * M�todo utilizado para verificar se a lista de mensagns est� vazia
	 * 
	 * @return Retorna <code>true</code> se n�o existir nenhuma mensagem na lista, <code>fase</code> caso contr�rio
	 */
	public boolean isEmpty();

	/**
	 * Retorna todas as mensagens
	 * 
	 * @return Retorna a lista de mensagens
	 */
	public List<PocExceptionMessage> getMessages();
}