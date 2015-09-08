package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.EmpresaDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Empresa;

@Stateless(name = "EmpresaDao")
public class EmpresaDaoImpl extends PagueCertoBaseDaoImpl<Empresa> implements EmpresaDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Empresa empresa) {

		query.setParameter("id", empresa.getId());
		query.setParameter("nome",
				(empresa.getNome() != null) && (!empresa.getNome().equals(STRING_VAZIA)) ? "%"
						+ empresa.getNome().toUpperCase() + "%" : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> findByParameters(Empresa empresa) {
		try {
			Query query = createNamedQuery("Empresa.findAllByParameters");
			query = setarParametrosQuery(query, empresa);

			return (List<Empresa>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> findByParameters(Empresa empresa, int start, int quantity) {
		try {
			Query query = createNamedQuery("Empresa.findAllByParameters");
			query = setarParametrosQuery(query, empresa);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Empresa empresa) {
		try {
			Query query = createNamedQuery("Empresa.findQuantityAllByParameters");
			query = setarParametrosQuery(query, empresa);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
