package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.PedidoDao;
import com.poc.entity.Pedido;
import com.poc.service.PedidoService;

//@Stateless
public class PedidoServiceImpl implements PedidoService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(PedidoServiceImpl.class);

//	@EJB
	@Inject
	PedidoDao pedidoDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<Pedido> getAll() {
		return pedidoDao.findAll(Pedido.class);
	}

	public Pedido findById(Integer idPedido) {
		return pedidoDao.find(Pedido.class, idPedido);
	}

	public List<Pedido> findByParameters(Pedido pedido) {
		return pedidoDao.findByParameters(pedido);
	}

	public List<Pedido> findByParameters(Pedido pedido, int start, int quantity) {
		return pedidoDao.findByParameters(pedido, start, quantity);
	}

	public long count() {
		return pedidoDao.count(Pedido.class);
	}

	public long countByParameters(Pedido pedido) {
		return pedidoDao.countByParameters(pedido);
	}

	public void inserirPedido(Pedido pedido) {
		pedidoDao.create(pedido);
	}

	public void alterarPedido(Pedido pedido) {
		pedidoDao.edit(pedido);
	}

	public void excluirPedido(Integer idPedido) {
		pedidoDao.delete(Pedido.class, idPedido);
	}
	
}
