package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.FormaPgtoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.FormaPgto;

@Stateless(name = "FormaPgtoDao")
public class FormaPgtoDaoImpl extends PagueCertoBaseDaoImpl<FormaPgto> implements FormaPgtoDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, FormaPgto formaPgto) {

		query.setParameter("id", formaPgto.getId());
		query.setParameter("descricao",
				(formaPgto.getDescricao() != null) && (!formaPgto.getDescricao().equals(STRING_VAZIA)) ? "%"
						+ formaPgto.getDescricao().toUpperCase() + "%" : null);
		query.setParameter("empresa", (formaPgto.getEmpresa() != null) ? formaPgto.getEmpresa() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<FormaPgto> findByParameters(FormaPgto formaPgto) {
		try {
			Query query = createNamedQuery("FormaPgto.findAllByParameters");
			query = setarParametrosQuery(query, formaPgto);

			return (List<FormaPgto>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FormaPgto> findByParameters(FormaPgto formaPgto, int start, int quantity) {
		try {
			Query query = createNamedQuery("FormaPgto.findAllByParameters");
			query = setarParametrosQuery(query, formaPgto);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(FormaPgto formaPgto) {
		try {
			Query query = createNamedQuery("FormaPgto.findQuantityAllByParameters");
			query = setarParametrosQuery(query, formaPgto);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

}
