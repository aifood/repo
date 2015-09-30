package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.ProdutoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Produto;

@Stateless(name = "ProdutoDao")
public class ProdutoDaoImpl extends PagueCertoBaseDaoImpl<Produto> implements ProdutoDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Produto produto) {

		query.setParameter("id", produto.getId());
		query.setParameter("nome",
				(produto.getNome() != null) && (!produto.getNome().equals(STRING_VAZIA)) ? "%"
						+ produto.getNome().toUpperCase() + "%" : null);
		query.setParameter("empresa", (produto.getEmpresa() != null) ? produto.getEmpresa() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findByParameters(Produto produto) {
		try {
			Query query = createNamedQuery("Produto.findAllByParameters");
			query = setarParametrosQuery(query, produto);

			return (List<Produto>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findByParameters(Produto produto, int start, int quantity) {
		try {
			Query query = createNamedQuery("Produto.findAllByParameters");
			query = setarParametrosQuery(query, produto);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Produto produto) {
		try {
			Query query = createNamedQuery("Produto.findQuantityAllByParameters");
			query = setarParametrosQuery(query, produto);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
