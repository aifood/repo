package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.ClienteDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Cliente;

@Stateless(name = "ClienteDao")
public class ClienteDaoImpl extends PagueCertoBaseDaoImpl<Cliente> implements ClienteDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Cliente cliente) {

		query.setParameter("id", cliente.getId());
		query.setParameter("nome",
				(cliente.getNome() != null) && (!cliente.getNome().equals(STRING_VAZIA)) ? "%"
						+ cliente.getNome().toUpperCase() + "%" : null);
		query.setParameter("empresa", (cliente.getEmpresa() != null) ? cliente.getEmpresa() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByParameters(Cliente cliente) {
		try {
			Query query = createNamedQuery("Cliente.findAllByParameters");
			query = setarParametrosQuery(query, cliente);

			return (List<Cliente>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByParameters(Cliente cliente, int start, int quantity) {
		try {
			Query query = createNamedQuery("Cliente.findAllByParameters");
			query = setarParametrosQuery(query, cliente);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Cliente cliente) {
		try {
			Query query = createNamedQuery("Cliente.findQuantityAllByParameters");
			query = setarParametrosQuery(query, cliente);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
