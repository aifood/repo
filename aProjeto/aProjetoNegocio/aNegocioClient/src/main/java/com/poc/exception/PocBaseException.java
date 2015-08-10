package com.poc.exception;

import java.io.Serializable;
import java.util.List;

/**
 * Interace que contém todos os métodos necessário para a renderização de mensagem de erro na tela, e uma mensagem específica de debug
 * 
 */
public interface PocBaseException extends Serializable {

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param key Chave da mensagem
	 * @param debugMessage Mensagem de debug
	 * @param params Parâmetros da mensagem
	 */
	public void addMessage(final String key, final String debugMessage, final Object... params);

	/**
	 * Adiciona a mensagem a lista de mensagens
	 * 
	 * @param message Mensagem que será adicionada
	 */
	public void addMessage(final PocExceptionMessage message);

	/**
	 * Método utilizado para verificar se a lista de mensagns está vazia
	 * 
	 * @return Retorna <code>true</code> se não existir nenhuma mensagem na lista, <code>fase</code> caso contrário
	 */
	public boolean isEmpty();

	/**
	 * Retorna todas as mensagens
	 * 
	 * @return Retorna a lista de mensagens
	 */
	public List<PocExceptionMessage> getMessages();
}