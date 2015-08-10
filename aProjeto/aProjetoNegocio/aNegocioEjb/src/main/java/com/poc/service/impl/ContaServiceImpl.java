package com.poc.service.impl;


import java.util.ArrayList;

import javax.ejb.EJB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.dao.CartaoCreditoDao;
import com.poc.dao.ContaBancariaDao;
import com.poc.dao.ContaDao;
import com.poc.dao.PessoaDao;
import com.poc.entity.CartaoCredito;
import com.poc.entity.Conta;
import com.poc.entity.ContaBancaria;
import com.poc.entity.Pessoa;
import com.poc.service.ContaService;

public abstract class ContaServiceImpl implements ContaService {

	static Logger logger = LogManager.getLogger(ContaServiceImpl.class);

	@EJB
	ContaDao contaDao;

	@EJB
	ContaBancariaDao contaBancariaDao;

	@EJB
	CartaoCreditoDao cartaoCreditoDao;

	@EJB
	PessoaDao pessoaDao;

	public ContaServiceImpl() {
	}

	public void inserirConta(long idPessoa, Conta conta) {
		Pessoa pessoa = pessoaDao.find(Pessoa.class, idPessoa);

		if (pessoa != null) {

			// Cria a associação nos dois sentidos
			pessoa.getContas().add(conta);

			if (conta.getPessoas() == null)
				conta.setPessoas(new ArrayList<Pessoa>());

			conta.getPessoas().add(pessoa);

			// Se soh existir essa conta, adiciona ela como conta padrão
			if (pessoa.getContas().size() == 1)
				pessoa.setContaPadrao(conta);

			pessoaDao.edit(pessoa);
		}
		// TODO: else lancar erro de usuario nao encontrado
	}

	public void excluirConta(long idPessoa, long idConta) {

		Pessoa pessoa = pessoaDao.find(Pessoa.class, idPessoa);
		Conta conta = contaDao.find(Conta.class, idConta);

		// TODO:Tratar caso n existam esses objetos
		if (pessoa != null && conta != null) {

			if (pessoa.getContas().contains(conta)) {

				// desassocia a conta ao usuário, em ambos os sentidos
				pessoa.getContas().remove(conta);
				conta.getPessoas().remove(pessoa);

				// se a conta for a conta padrão do usuário, entao desassocia
				// essa conta padrao
				if ((pessoa.getContaPadrao() != null) && (pessoa.getContaPadrao().getId() == conta.getId())) {
					pessoa.setContaPadrao(null);
				}

				// Se ao remover essa associação Pessoa/Conta,
				// essa conta não tiver mais pessoas associadas, ela pode ser
				// removida da base
				if (conta.getPessoas().size() == 0) {
					contaDao.delete(conta);
				}
				pessoaDao.edit(pessoa);
			}
			// TODO: else: tratar caso a conta especificada nao esteja associada
			// ao usuario
		}
	}

	public void alterarConta(Conta conta) {
		if (conta instanceof ContaBancaria)
			contaBancariaDao.edit((ContaBancaria) conta);
		else if (conta instanceof CartaoCredito)
			cartaoCreditoDao.edit((CartaoCredito) conta);
	}

	public long count() {
		return contaDao.count(Conta.class);
	}

	public Conta findById(long idConta) {
		Conta c = contaDao.find(Conta.class, idConta);
		if (c != null)
			c.getLancamentos().size();

		return c;
	}
}
