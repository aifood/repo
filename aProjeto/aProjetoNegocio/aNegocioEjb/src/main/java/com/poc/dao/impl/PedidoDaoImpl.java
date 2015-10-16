package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.PedidoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Pedido;

@Stateless(name = "PedidoDao")
public class PedidoDaoImpl extends PagueCertoBaseDaoImpl<Pedido> implements PedidoDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Pedido pedido) {

		query.setParameter("id", pedido.getId());
		query.setParameter("empresa", (pedido.getEmpresa() != null) ? pedido.getEmpresa() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> findByParameters(Pedido pedido) {
		try {
			Query query = createNamedQuery("Pedido.findAllByParameters");
			query = setarParametrosQuery(query, pedido);

			return (List<Pedido>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> findByParameters(Pedido pedido, int start, int quantity) {
		try {
			Query query = createNamedQuery("Pedido.findAllByParameters");
			query = setarParametrosQuery(query, pedido);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Pedido pedido) {
		try {
			Query query = createNamedQuery("Pedido.findQuantityAllByParameters");
			query = setarParametrosQuery(query, pedido);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
