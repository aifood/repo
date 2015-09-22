package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import com.poc.dao.PessoaDao;
import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.service.PessoaService;

public class PessoaServiceImpl implements PessoaService {

	private static final long serialVersionUID = 1L;

	@Inject
	protected Logger log;

	@Inject
	PessoaDao pessoaDao;

	public List<Pessoa> getAll() {
		return pessoaDao.findAll(Pessoa.class);
	}

	public Pessoa findById(long idPessoa) {
		return pessoaDao.find(Pessoa.class, idPessoa);
	}

	public List<Pessoa> findByParameters(Pessoa pessoa) {
		return pessoaDao.findByParameters(pessoa);
	}

	public List<Pessoa> findByParameters(Pessoa pessoa, int start, int quantity) {
		return pessoaDao.findByParameters(pessoa, start, quantity);
	}

	public long count() {
		return pessoaDao.count(Pessoa.class);
	}

	public long countByParameters(Pessoa pessoa) {
		return pessoaDao.countByParameters(pessoa);
	}

	public void inserirPessoa(Pessoa pessoa) {
		pessoaDao.create(pessoa);
	}

	public void alterarPessoa(Pessoa pessoa) {
		pessoaDao.edit(pessoa);
	}

	public void excluirPessoa(long idPessoa) {
		pessoaDao.delete(Pessoa.class, idPessoa);
	}

	public List<Pessoa> findByTipoPermissao(TipoPermissao tipoPermissao) {
		return pessoaDao.findByTipoPermissao(tipoPermissao);
	}

	@Override
	public Pessoa findByEmailESenha(Pessoa pessoa) {
		return pessoaDao.findByEmailESenha(pessoa);
	}
}
