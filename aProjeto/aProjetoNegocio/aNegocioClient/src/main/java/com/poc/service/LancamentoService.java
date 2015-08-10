package com.poc.service;


import javax.ejb.Remote;

import com.poc.entity.Lancamento;

//import br.com.paguecerto.entity.Lancamento;

@Remote
public interface LancamentoService {
	
	Lancamento findById(long idLancamento);
	
	void inserirLancamento(long idConta, Lancamento lancamento);
	
	void excluirLancamento(long idLancamento);

	void alterarLancamento(Lancamento lancamento);

	long count();
}