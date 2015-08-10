package com.poc.service;


import java.util.List;

import javax.ejb.Remote;

import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;


@Remote
public interface PessoaService {
	
	List<Pessoa> getAll();

	Pessoa findById(long idPessoa);
	
	List<Pessoa> findByParameters(Pessoa pessoa);

	List<Pessoa> findByParameters(Pessoa pessoa, int start,
			int quantity);

	long countByParameters(Pessoa pessoa);
	
	long count();

	List<Pessoa> findByTipoPermissao(TipoPermissao tipoPermissao);
	
	void inserirPessoa(Pessoa pessoa);

	void alterarPessoa(Pessoa pessoa);

	void excluirPessoa(long idPessoa);
	
	Pessoa findByEmailESenha(Pessoa pessoa);
}