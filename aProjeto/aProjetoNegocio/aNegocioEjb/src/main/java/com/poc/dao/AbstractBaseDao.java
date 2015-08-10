package com.poc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe que implementa os métodos básicos para acesso a dados.
 * <BR>
 * Esta classe contém a instancia do objeto EntityManager que será utilizado para 
 * operações de persistencia(criar, alterar, excluir, pesquisar...).  
 * <BR>
 * Esta classe receberá através de injeção de dependencia o EntityManager. 
 * 
 */
public abstract class AbstractBaseDao<E> implements BaseDao<E>{
	
	private static final Logger log = LogManager.getLogger(AbstractBaseDao.class);	
	
	/**
	 * Insere a entidade.
	 * @param entityObject Entidade com o "registro" a ser inserido.
	 *
	 */
    public void create(E entityObject) {
    	log.debug("Insere entidade:" + entityObject.getClass().getName());
       	getEntityManager().persist(entityObject);
    }
    
    /**
     * Altera a entidade.
     * @param entityObject Entidade com o "registro" a ser alterado.
     */
    public E edit(E entityObject) {
    	log.debug("Altera entidade:" + entityObject.getClass().getName());    	
        return getEntityManager().merge(entityObject);
    }
    
    /**
     * Apaga a entidade.<br />
     * @param entityObject Entidade com o "registro" a ser excluido.
     */
    public void destroy(E entityObject) {
    	log.debug("Apaga entidade:" + entityObject.getClass().getName());    	
        getEntityManager().merge(entityObject);
        getEntityManager().remove(entityObject);
    }
    
    /**
     * Apaga a entidade.<br />
     * A chave primária deve estar preenchida com valores válidos.
     * @param entityObject Entidade com o "registro" a ser excluido.
     */
    public void delete(E entityObject) {
    	log.debug("Apaga entidade:" + entityObject.getClass().getName());    	
        getEntityManager().remove(entityObject);
    }
    
    /**
     * Apaga a entidade.
     * @param entityClass Classe que representa a entidade
     * @param pk Chave primária
     */
    public void delete(Class<E> entityClass,Object pk) {
    	log.debug("Apaga entidade:" + entityClass.getName());
    	getEntityManager().remove(getEntityManager().find(entityClass, pk));
    }

    /**
     * Busca a entidade através da chave primária.     
     * @param entityClass Classe que representa a entidade.
     * @param pk Chave primária
     * @return Retorna a entidade
     */
    public E find(Class<E> entityClass, Object pk) {
    	log.debug("Pesquisa entidade:" + entityClass.getName() + " - pk:" + pk);
    	E find = getEntityManager().find(entityClass, pk);
        return find;
    }

    /**
     * Busca todos os registros de uma entidade.
     * @param entityClass Classe que representa a entidade.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entityClass) {
    	log.debug("Pesquisa todos os registros da entidade:" + entityClass.getName());
        Query query = getEntityManager().createQuery("select o from " + entityClass.getSimpleName()   + " o ");
    	return query.getResultList();
    }


    /**
     * Busca todos os registros de uma entidade dentro de um determinado intervalo.
     * @param entityClass Classe que representa a entidade.
     * @param firstResult Primeiro registro da consulta.
     * @param maxResult Número de registros na consulta.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entityClass,int firstResult, int maxResult) {
    	log.debug("Pesquisa todos os registros da entidade:" + entityClass.getName() + " posicao inicial:" + firstResult + " total de registros maximo: " + maxResult);
    	Query query = getEntityManager().createQuery("select o from " + entityClass.getSimpleName() + " o ");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }
    
    public long count(Class<E> entityClass) {
    	Query query = getEntityManager().createQuery("select count(o) from " + entityClass.getSimpleName() + " o ");        
        return (Long)query.getSingleResult();
    }
    
    
    /**
     * Consulta através de uma query JPA.
     * @param strQuery Query de consulta.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("rawtypes")
	protected List findByQuery(String strQuery) {
    	log.debug("Pesquisa por query:" + strQuery);
        Query query = createQuery(strQuery); 
    	return query.getResultList();
    }
    
    /**
     * Consulta através de uma query JPA dentro de um determinado intervalo.
     * @param strQuery Query de consulta.
     * @param firstResult Primeiro registro da consulta.
     * @param maxResult Número de registros na consulta.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("rawtypes")
	protected List findByQuery(String strQuery, int firstResult, int maxResult) {
    	log.debug("Pesquisa por query:" + strQuery + " posicao inicial:" + firstResult + " total de registros maximo: " + maxResult);
        Query query = createQuery(strQuery); 
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
    	return query.getResultList();
    }


    /**
     * Consulta através de uma NamedQuery.
     * @param strQuery Nome da NamedQuery.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("rawtypes")
	protected List findByNamedQuery(String namedQuery) {
    	log.debug("Pesquisa por namedquery:" + namedQuery);
        Query query = createNamedQuery(namedQuery); 
    	return query.getResultList();
    }

    /**
     * Consulta através de uma NamedQuery dentro de um determinado intervalo.
     * @param namedQuery Nome da NamedQuery.
     * @param firstResult Primeiro registro da consulta.
     * @param maxResult Número de registros na consulta.
     * @return Retorna a lista de entidades.
     */
    @SuppressWarnings("rawtypes")
	protected List findByNamedQuery(String namedQuery, int firstResult, int maxResult) {
    	log.debug("Pesquisa por namedquery:" + namedQuery + " posicao inicial:" + firstResult + " total de registros maximo: " + maxResult);
    	Query query = createNamedQuery(namedQuery); 
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
    	return query.getResultList();
    }
    
    /**
     * Executa uma determinada instrução JPA.<br />
     * Normalmente inserts, updates, e deletes.
     * @param strQuery Instrução JPA.
     * @return Retorna a quantidade de registros afetados.
     */
    protected int executeQuery(String strQuery){
    	log.debug("Executa a query:" + strQuery);
    	return createQuery(strQuery).executeUpdate(); 
    }
    
    
    /**
     * Executa uma determinada instrução NamedQuery.<br />
     * Normalmente inserts, updates, e deletes.
     * @param strQuery Nome da NamedQuery.
     * @return Retorna a quantidade de registros afetados.
     */
    protected int executeNamedQuery(String strQuery){
    	log.debug("Executa a named query:" + strQuery);
    	return createNamedQuery(strQuery).executeUpdate(); 
    }

    
    /**
     * Cria um objeto Query através de uma string com a query.
     * @param query Query JPA.
     * @return Objeto Query.
     */
    protected Query createQuery(String query) {
    	log.debug("Cria query:" + query);
    	return getEntityManager().createQuery(query);
    }

    
    /**
     * Cria um objeto Query através de uma NamedQuery.
     * @param namedQuery Identificação da NamedQuery.
     * @return Objeto Query.
     */
    protected Query createNamedQuery(String namedQuery) {
    	log.debug("Cria namedquery:" + namedQuery);
    	return getEntityManager().createNamedQuery(namedQuery);
    }


    /**
     * Método abstrato para retornar o EntityManager.
     * Definir na aplicação qual a unidade de persistencia e implementar este metodo.
     * @return EntityManager entityManager.
     */
    protected abstract EntityManager getEntityManager();

}
