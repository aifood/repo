package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.EntregadorDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Entregador;

@Stateless(name = "EntregadorDao")
public class EntregadorDaoImpl extends PagueCertoBaseDaoImpl<Entregador> implements EntregadorDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Entregador entregador) {

		query.setParameter("id", entregador.getId());
		query.setParameter("nome",
				(entregador.getNome() != null) && (!entregador.getNome().equals(STRING_VAZIA)) ? "%"
						+ entregador.getNome().toUpperCase() + "%" : null);
		query.setParameter("empresa", (entregador.getEmpresa() != null) ? entregador.getEmpresa() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Entregador> findByParameters(Entregador entregador) {
		try {
			Query query = createNamedQuery("Entregador.findAllByParameters");
			query = setarParametrosQuery(query, entregador);

			return (List<Entregador>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entregador> findByParameters(Entregador entregador, int start, int quantity) {
		try {
			Query query = createNamedQuery("Entregador.findAllByParameters");
			query = setarParametrosQuery(query, entregador);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Entregador entregador) {
		try {
			Query query = createNamedQuery("Entregador.findQuantityAllByParameters");
			query = setarParametrosQuery(query, entregador);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
