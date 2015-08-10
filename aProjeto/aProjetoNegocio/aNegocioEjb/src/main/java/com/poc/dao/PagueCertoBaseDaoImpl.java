package com.poc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReportQuery;
import org.eclipse.persistence.queries.ReportQueryResult;

public abstract class PagueCertoBaseDaoImpl<E> extends AbstractBaseDao<E> implements PagueCertoBaseDao<E> {

	@PersistenceContext//(unitName = "AdminSibePu")
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected JpaEntityManager getJpaEntityManager() {
		return (JpaEntityManager) getEntityManager().getDelegate();
	}

	/**
	 * Realiza atualização de entidade de acordo com os dados que existem na
	 * base de dados.
	 * 
	 * @param entity
	 *            - entidade a ser atualizada.
	 * @return a entidade persistida e atualizada.
	 */
	@SuppressWarnings("unchecked")
	public E refresh(E entity) {
		return (E) getJpaEntityManager().getActiveSession().refreshObject(
				entity);
	}

	/**
	 * Exclui a entidade informada como parâmetro.
	 * 
	 * @param entity
	 *            - entidade a ser excluída.
	 */
	@Override
	public void delete(E entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	/**
	 * Cria query de acordo com o nome informado e insere os parametros de
	 * acordo com os nomes e valores que estão nos vetores informados.
	 * 
	 * @param namedQuery
	 *            - nome da <i>named query</i> a ser criada.
	 * @param parameterNames
	 *            - nomes dos parametros da query.
	 * @param parameterValues
	 *            - valores dos parametros da query.
	 * @return o objeto Query criado e com os parâmetros setados.
	 */
	protected Query createNamedQuery(String namedQuery,
			String[] parameterNames, Object[] parameterValues) {
		Query query = super.createNamedQuery(namedQuery);
		setParameters(query, parameterNames, parameterValues);
		return query;
	}

	/**
	 * Cria query de acordo com o nome informado e insere os parametros de
	 * acordo com os valores que estão no vetor informado.
	 * 
	 * @param namedQuery
	 *            - nome da <i>named query</i> a ser criada.
	 * @param parameterValues
	 *            - valores dos parametros da query.
	 * @return o objeto Query criado e com os parâmetros setados.
	 */
	protected Query createNamedQuery(String namedQuery, Object[] parameterValues) {
		Query query = super.createNamedQuery(namedQuery);
		setParameters(query, parameterValues);
		return query;
	}

	/**
	 * Insere os parametros e seus respectivos valores na query.
	 * 
	 * @param query
	 *            - objeto Query que terá seus parametros setados.
	 * @param parameterNames
	 *            - nomes dos parametros da query.
	 * @param parameterValues
	 *            - valores dos parametros da query.
	 */
	protected void setParameters(Query query, String[] parameterNames,
			Object[] parameterValues) {
		for (int indice = 0; indice < parameterNames.length; indice++) {
			query.setParameter(parameterNames[indice], parameterValues[indice]);
		}
	}

	/**
	 * Insere os parametros na query na ordem que estão no vetor.
	 * 
	 * @param query
	 *            - objeto Query que terá seus parametros setados.
	 * @param parameterValues
	 *            - valores dos parametros da query.
	 */
	protected void setParameters(Query query, Object[] parameterValues) {
		for (int indice = 0; indice < parameterValues.length; indice++) {
			query.setParameter(indice + 1, parameterValues[indice]);
		}
	}

	/**
	 * Recupera a lista resultante da query informada, tratando o caso de ser
	 * uma lista vazia e por conseguinte evitando algum erro antes de retornar
	 * para o serviço.
	 * 
	 * @param query
	 *            - objeto Query que contem resultado da consulta.
	 * @return a lista resultante ou uma lista vazia, caso nenhum dado seja
	 *         retornado.
	 */
	@SuppressWarnings("unchecked")
	protected List<E> getResultList(Query query) {
		if (query.getResultList().isEmpty()) {
			return new ArrayList<E>(0);
		}
		return (List<E>) query.getResultList();
	}

	/**
	 * Recupera o objeto da consulta realizada, tratando o caso de não existir
	 * nenhum registro e por conseguinte evitando algum erro antes de retornar
	 * para o serviço.
	 * 
	 * @param query
	 *            - objeto Query que contem resultado da consulta.
	 * @return o objeto resultante ou <code>null</code>, caso nenhum registro
	 *         seja encontrado. Caso seja retornado mais de um registro, então
	 *         retornada a primeira ocorrência.
	 */
	@SuppressWarnings("unchecked")
	protected E getSingleResult(Query query) {
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (E) query.getResultList().get(0);
	}

	/**
	 * Verifica quantos registros existem de acordo com a entidade informada
	 * como parâmetro.
	 * 
	 * @param exemplo
	 *            - entidade que servirá como base de consulta.
	 * @return o número de registros informados.
	 */
	public int count(E exemplo) {
		ReportQuery rq = new ReportQuery();
		rq.setExampleObject(exemplo);
		rq.addCount();
		ReportQueryResult queryResult = (ReportQueryResult) getJpaEntityManager()
				.createQuery(rq).getSingleResult();
		Number result = (Number) queryResult.getResults().get(0);
		return result.intValue();
	}

	/**
	 * Obtém a quantidade de itens baseadas na query.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * @param firstResult
	 *            - índice de onde começará a busca dos registros.
	 * @param maxResults
	 *            - quantos objetos no máximo terá a lista retornada.
	 * 
	 * @return Retorna a quantidade de itens baseadas na query.
	 */
	protected Long count(String namedQuery,
			final Map<String, ? extends Object> parameters, int firstResult,
			int maxResults) {

		Query query = prepareQuery(namedQuery, parameters, firstResult,
				maxResults);
		return ((Number) query.getResultList().get(0)).longValue();
	}

	/**
	 * Obtém a quantidade de itens baseadas na query.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * @param maxResults
	 *            - quantos objetos no máximo terá a lista retornada.
	 * 
	 * @return Retorna a quantidade de itens baseadas na query.
	 */
	protected Long count(final String namedQuery,
			Map<String, ? extends Object> parameters, int maxResults) {
		return count(namedQuery, parameters, 0, maxResults);
	}

	/**
	 * Obtém a quantidade de itens baseadas na query.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * 
	 * @return Retorna a quantidade de itens baseadas na query.
	 */
	protected Long count(String namedQuery,
			Map<String, ? extends Object> parameters) {
		return count(namedQuery, parameters, 0, 0);
	}

	/**
	 * Obtém a quantidade de itens baseadas na query.
	 * 
	 * @param namedQuery
	 *            Named query.
	 * 
	 * @return Retorna a quantidade de itens baseadas na query.
	 */
	protected Long count(final String namedQuery) {
		return count(namedQuery, null);
	}

	/**
	 * Localiza por uma named query e passando argumentos.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo parametro e valor.
	 * 
	 * @return Retorna o elemento localizado.
	 */
	protected E findSingleResultByNamedQuery(String namedQuery,
			Map<String, ? extends Object> parameters) {

		Query query = null;

		if (parameters != null && !parameters.isEmpty()) {
			query = createNamedQuery(namedQuery, (String[]) parameters.keySet()
					.toArray(new String[parameters.keySet().size()]),
					parameters.values().toArray());
		} else {
			query = createNamedQuery(namedQuery);
		}

		return getSingleResult(query);
	}

	/**
	 * Realiza consulta de acordo com a entidade informada como parâmetro.
	 * 
	 * @param exemplo
	 *            - entidade que servirá como base de consulta.
	 * @return uma lista contendo as entidades encontradas, ou uma lista vazia,
	 *         caso não exista nenhum registro.
	 */
	public List<E> findByExample(E exemplo) {
		return getResultList(getJpaEntityManager()
				.createQueryByExample(exemplo));
	}

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
	public List<E> findByExample(E exemplo, int firstResult, int maxResults) {
		return getResultList(getJpaEntityManager()
				.createQueryByExample(exemplo).setFirstResult(firstResult)
				.setMaxResults(maxResults));
	}

	/**
	 * Localiza por uma named query, passando argumentos e informando o registro
	 * inicial e o máximo de resultados.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * @param firstResult
	 *            - índice de onde começará a busca dos registros.
	 * @param maxResults
	 *            - quantos objetos no máximo terá a lista retornada.
	 * 
	 * @return Retorna uma lista de elementos localizados.
	 */
	protected List<E> findByNamedQuery(String namedQuery,
			Map<String, ? extends Object> parameters, int firstResult,
			int maxResults) {

		Query query = prepareQuery(namedQuery, parameters, firstResult,
				maxResults);
		return getResultList(query);
	}

	/**
	 * Localiza por uma named query, passando argumentos e informando o registro
	 * inicial e o máximo de resultados.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * @param maxResults
	 *            - quantos objetos no máximo terá a lista retornada.
	 * 
	 * @return Retorna uma lista de elementos localizados.
	 */
	protected List<E> findByNamedQuery(String namedQuery,
			Map<String, ? extends Object> parameters, int maxResults) {
		return findByNamedQuery(namedQuery, parameters, 0, maxResults);
	}

	/**
	 * Localiza por uma named query, passando argumentos e informando o registro
	 * inicial e o máximo de resultados.
	 * 
	 * @param namedQuery
	 *            - nome da query.
	 * @param parameters
	 *            - mapa contendo cada parametro e seu valor respectivo.
	 * 
	 * @return Retorna uma lista de elementos localizados.
	 */
	protected List<E> findByNamedQuery(String namedQuery,
			Map<String, ? extends Object> parameters) {
		return findByNamedQuery(namedQuery, parameters, 0, 0);
	}

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
	public List<E> findAllOrderedAsc(Class<? extends E> entityClass,
			String orderByProperty) {
		ReadAllQuery readQuery = new ReadAllQuery(entityClass);
		readQuery.addAscendingOrdering(orderByProperty);
		return getResultList(getJpaEntityManager().createQuery(readQuery));
	}

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
	public List<E> findAllOrderedDesc(Class<? extends E> entityClass,
			String orderByProperty) {
		ReadAllQuery readQuery = new ReadAllQuery(entityClass);
		readQuery.addDescendingOrdering(orderByProperty);
		return getResultList(getJpaEntityManager().createQuery(readQuery));
	}

	private Query prepareQuery(String namedQuery,
			final Map<String, ? extends Object> parameters, int firstResult,
			int maxResults) {
		Query query = null;

		if (parameters != null && !parameters.isEmpty()) {
			query = createNamedQuery(namedQuery, (String[]) parameters.keySet()
					.toArray(new String[parameters.keySet().size()]),
					parameters.values().toArray());
		} else {
			query = createNamedQuery(namedQuery);
		}

		if (firstResult != 0) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != 0) {
			query.setMaxResults(maxResults);
		}
		return query;
	}

	public int executarSentencaNativeQuery(String querySentenca,
			Object[] parametros) {

		int registrosAfetados = 0;
		Query query = getEntityManager().createNativeQuery(querySentenca);
		if ((parametros != null) && (parametros.length > 0)) {
			for (int id = 0; id < parametros.length; id++) {
				query.setParameter(id, parametros[id]);
			}
		}
		registrosAfetados = query.executeUpdate();

		return registrosAfetados;
	}

	protected String montarParametroPesquisaIndex(List<String> identificadores, boolean isString) {
		StringBuffer idsParam = new StringBuffer();
		int contador = 0;
		for (String id : identificadores) {
			if (isString) {
				idsParam.append("'" + id.toUpperCase() + "'");
			} else {
				idsParam.append(id.toUpperCase());
			}
			if (contador < identificadores.size() - 1) {
				idsParam.append(",");
				contador++;
			}
		}
		return idsParam.toString();
	}

}
