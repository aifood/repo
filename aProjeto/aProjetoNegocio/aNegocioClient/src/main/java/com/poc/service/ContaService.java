package com.poc.service;


import com.poc.entity.Conta;


public interface ContaService {

	void inserirConta(long idPessoa, Conta conta);

	void alterarConta(Conta conta);
	
	void excluirConta(long idPessoa, long idConta);

	long count();
	
	Conta findById(long idConta);
}