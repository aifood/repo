package com.poc.service.impl;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.poc.dao.ContaDao;
import com.poc.dao.LancamentoDao;
import com.poc.entity.Conta;
import com.poc.entity.Lancamento;
import com.poc.service.LancamentoService;

@Stateless(name = "LancamentoService")
public class LancamentoServiceImpl implements LancamentoService {

	@EJB
	LancamentoDao lancamentoDao;

	@EJB
	ContaDao contaDao;

	public Lancamento findById(long idLancamento) {
		return lancamentoDao.find(Lancamento.class, idLancamento);
	}

	public void inserirLancamento(long idConta, Lancamento lancamento) {
		Conta conta = contaDao.find(Conta.class, idConta);

		if (conta != null) {

			// Cria a associação nos dois sentidos
			conta.getLancamentos().add(lancamento);
			lancamento.setConta(conta);

			contaDao.edit(conta);
		}
		// TODO: else lancar erro de usuario nao encontrado
	}

	public void excluirLancamento(long idLancamento) {

		Lancamento lancamento = lancamentoDao.find(Lancamento.class, idLancamento);
		Conta conta = lancamento.getConta();

		// TODO:Tratar caso n existam esses objetos
		if (conta != null && lancamento != null) {
			conta.getLancamentos().remove(lancamento);

			// lancamentoDao.delete(lancamento);
			// OU
			contaDao.edit(conta);
		}
	}

	public void alterarLancamento(Lancamento lancamento) {
		lancamentoDao.edit(lancamento);
	}

	public long count() {
		return lancamentoDao.count(Lancamento.class);
	}
}
