package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.ItemPedidoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.ItemPedido;

@Stateless(name = "ItemPedidoDao")
public class ItemPedidoDaoImpl extends PagueCertoBaseDaoImpl<ItemPedido> implements ItemPedidoDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, ItemPedido itemPedido) {

		query.setParameter("id", itemPedido.getId());
		query.setParameter("nome",
				(itemPedido.getProduto().getNome() != null) && (!itemPedido.getProduto().getNome().equals(STRING_VAZIA)) ? "%"
						+ itemPedido.getProduto().getNome().toUpperCase() + "%" : null);
		query.setParameter("pedido", (itemPedido.getPedido() != null) ? itemPedido.getPedido() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<ItemPedido> findByParameters(ItemPedido itemPedido) {
		try {
			Query query = createNamedQuery("ItemPedido.findAllByParameters");
			query = setarParametrosQuery(query, itemPedido);

			return (List<ItemPedido>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ItemPedido> findByParameters(ItemPedido itemPedido, int start, int quantity) {
		try {
			Query query = createNamedQuery("ItemPedido.findAllByParameters");
			query = setarParametrosQuery(query, itemPedido);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(ItemPedido itemPedido) {
		try {
			Query query = createNamedQuery("ItemPedido.findQuantityAllByParameters");
			query = setarParametrosQuery(query, itemPedido);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
