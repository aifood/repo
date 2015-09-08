package com.poc.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.dao.PessoaDao;
import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;

@Stateless(name = "PessoaDao")
public class PessoaDaoImpl extends PagueCertoBaseDaoImpl<Pessoa> implements PessoaDao {
	String STRING_VAZIA = "";

	private Query setarParametrosQuery(Query query, Pessoa pessoa) {

		query.setParameter("id", pessoa.getId());
		query.setParameter("nome",
				(pessoa.getNome() != null) && (!pessoa.getNome().equals(STRING_VAZIA)) ? "%"
						+ pessoa.getNome().toUpperCase() + "%" : null);
		query.setParameter("email",
				(pessoa.getEmail() != null) && (!pessoa.getEmail().equals(STRING_VAZIA)) ? "%"
						+ pessoa.getEmail().toUpperCase() + "%" : null);
		query.setParameter("senha",
				(pessoa.getSenha() != null) && (!pessoa.getSenha().equals(STRING_VAZIA)) ? "%"
						+ pessoa.getSenha().toUpperCase() + "%" : null);
		query.setParameter("tipoPermissao", (pessoa.getTipoPermissao() != null) ? pessoa
				.getTipoPermissao().getId() : null);
		query.setParameter("empresa", (pessoa.getEmpresa() != null) ? pessoa
				.getEmpresa().getId() : null);
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findByParameters(Pessoa pessoa) {
		try {
			Query query = createNamedQuery("Pessoa.findAllByParameters");
			query = setarParametrosQuery(query, pessoa);

			return (List<Pessoa>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findByParameters(Pessoa pessoa, int start, int quantity) {
		try {
			Query query = createNamedQuery("Pessoa.findAllByParameters");
			query = setarParametrosQuery(query, pessoa);
			query.setFirstResult(start);
			query.setMaxResults(quantity);

			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public long countByParameters(Pessoa pessoa) {
		try {
			Query query = createNamedQuery("Pessoa.findQuantityAllByParameters");
			query = setarParametrosQuery(query, pessoa);

			return Long.parseLong(query.getSingleResult().toString());
		} catch (NoResultException nre) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findByTipoPermissao(TipoPermissao tipoPermissao) {
		try {
			Query query = createNamedQuery("Pessoa.findByTipoPermissao");
			query.setParameter("tipoPermissao", tipoPermissao.getId());

			return (List<Pessoa>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public Pessoa findByEmailESenha(Pessoa pessoa) {
		try {
			Query query = createNamedQuery("Pessoa.findByEmailESenha");
			query.setParameter("email", pessoa.getEmail());
			query.setParameter("senha", pessoa.getSenha());

			return (Pessoa) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
