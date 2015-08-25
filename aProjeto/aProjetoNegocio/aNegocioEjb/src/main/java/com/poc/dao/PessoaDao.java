package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;

@Local
public interface PessoaDao extends PagueCertoBaseDao<Pessoa> {

	public List<Pessoa> findByParameters(Pessoa pessoa);

	public List<Pessoa> findByParameters(Pessoa pessoa, int start,
			int quantity);

	public long countByParameters(Pessoa pessoa);

	public List<Pessoa> findByTipoPermissao(TipoPermissao tipoPermissao);

	public Pessoa findByEmailESenha(Pessoa pessoa);
}
