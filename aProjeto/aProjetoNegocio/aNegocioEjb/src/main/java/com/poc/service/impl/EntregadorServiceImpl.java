package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.EntregadorDao;
import com.poc.entity.Entregador;
import com.poc.service.EntregadorService;

//@Stateless
public class EntregadorServiceImpl implements EntregadorService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(EntregadorServiceImpl.class);

//	@EJB
	@Inject
	EntregadorDao entregadorDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<Entregador> getAll() {
		return entregadorDao.findAll(Entregador.class);
	}

	public Entregador findById(Integer idEntregador) {
		return entregadorDao.find(Entregador.class, idEntregador);
	}

	public List<Entregador> findByParameters(Entregador entregador) {
		return entregadorDao.findByParameters(entregador);
	}

	public List<Entregador> findByParameters(Entregador entregador, int start, int quantity) {
		return entregadorDao.findByParameters(entregador, start, quantity);
	}

	public long count() {
		return entregadorDao.count(Entregador.class);
	}

	public long countByParameters(Entregador entregador) {
		return entregadorDao.countByParameters(entregador);
	}

	public void inserirEntregador(Entregador entregador) {
		entregadorDao.create(entregador);
	}

	public void alterarEntregador(Entregador entregador) {
		entregadorDao.edit(entregador);
	}

	public void excluirEntregador(Integer idEntregador) {
		entregadorDao.delete(Entregador.class, idEntregador);
	}
	
}
