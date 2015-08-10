package com.poc.dao;


import java.util.List;



public interface PagueCertoBaseDao<E> extends BaseDao<E> {

	/**
	 * Realiza atualização de entidade de acordo com os dados que existem na
	 * base de dados.
	 * 
	 * @param entity
	 *            - entidade a ser atualizada.
	 * @return a entidade persistida e atualizada.
	 */
	E refresh(E entity);

	/**
	 * Exclui a entidade informada como parâmetro.
	 * 
	 * @param entity
	 *            - entidade a ser excluída.
	 */
	void delete(E entity);

	/**
	 * Verifica quantos registros existem de acordo com a entidade informada
	 * como parâmetro.
	 * 
	 * @param exemplo
	 *            - entidade que servirá como base de consulta.
	 * @return o número de registros informados.
	 */
	int count(E exemplo);

	/**
	 * Realiza consulta de acordo com a entidade informada como parâmetro.
	 * 
	 * @param exemplo
	 *            - entidade que servirá como base de consulta.
	 * @return uma lista contendo as entidades encontradas, ou uma lista vazia,
	 *         caso não exista nenhum registro.
	 */
	List<E> findByExample(E exemplo);

	/**
	 * Realiza consulta de acordo com a entidade informada como parâmetro em
	 * conjunto com os dados de paginação.
	 * 
	 * @param exemplo
	 *            - entidade que servirá como base de consulta.
	 * @param firstResult
	 *            - índice de onde começará a busca dos registros.
	 * @param maxResults
	 *            - quantos objetos no máximo terá a lista retornada.
	 * @return uma lista contendo as entidades encontradas, ou uma lista vazia,
	 *         caso não exista nenhum registro.
	 */
	List<E> findByExample(E exemplo, int firstResult, int maxResults);

	/**
	 * Consulta todas entidades utilizando ordenação do tipo <code>ASC</code>.
	 * 
	 * @param entityClass
	 *            - classe ou subclasse do tipo genérico.
	 * @param orderByProperty
	 *            - propriedade pela qual se deseja ordenar.
	 * @return uma lista contendo as entidades encontradas, ou uma lista vazia,
	 *         caso não exista nenhum registro.
	 */
	List<E> findAllOrderedAsc(Class<? extends E> entityClass,
			String orderByProperty);

	/**
	 * Consulta todas entidades utilizando ordenação do tipo <code>DESC</code>.
	 * 
	 * @param entityClass
	 *            - classe ou subclasse do tipo genérico.
	 * @param orderByProperty
	 *            - propriedade pela qual se deseja ordenar.
	 * @return uma lista contendo as entidades encontradas, ou uma lista vazia,
	 *         caso não exista nenhum registro.
	 */
	List<E> findAllOrderedDesc(Class<? extends E> entityClass,
			String orderByProperty);

	int executarSentencaNativeQuery(String querySentenca, Object[] parametros);

}