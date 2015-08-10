package com.poc.dao;


import java.util.List;

public interface BaseDao<E> {

	/**
	 * Insere a entidade.
	 * @param entityObject Entidade com o "registro" a ser inserido.
	 */
    public void create(E entityObject);
    
    /**
     * Altera a entidade.
     * @param entityObject Entidade com o "registro" a ser alterado.
     */
    public E edit(E entityObject);
    
    /**
     * Apaga a entidade.
     * @param entityObject Entidade com o "registro" a ser excluido.
     */
    public void delete(E entityObject);
    
    /**
     * Apaga a entidade.
     * @param entityClass Classe que representa a entidade
     * @param pk Chave primária
     */
    public void delete(Class<E> entityClass,Object pk);
    
    /**
     * Busca a entidade através da chave primária.     
     * @param entityClass Classe que representa a entidade.
     * @param pk Chave primária
     * @return Retorna a entidade
     */
    public E find(Class<E> entityClass, Object pk);

    /**
     * Busca todos os registros de uma entidade.
     * @param entityClass Classe que representa a entidade.
     * @return Retorna a lista de entidades.
     */
    public List<E> findAll(Class<E> entityClass);


    /**
     * Busca todos os registros de uma entidade dentro de um determinado intervalo.
     * @param entityClass Classe que representa a entidade.
     * @param firstResult Primeiro registro da consulta.
     * @param maxResult Número de registros na consulta.
     * @return Retorna a lista de entidades.
     */
    public List<E> findAll(Class<E> entityClass,int firstResult, int maxResult);
    
    /**
     * Retorna a quantidade de registros de uma entidade.
     * @param exemplo
     * @return A quantidade de registros da entidade.
     */
    public long count(Class<E> entityClass);
}
