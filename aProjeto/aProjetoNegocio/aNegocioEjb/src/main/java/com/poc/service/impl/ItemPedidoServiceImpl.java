package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.ItemPedidoDao;
import com.poc.entity.ItemPedido;
import com.poc.service.ItemPedidoService;

//@Stateless
public class ItemPedidoServiceImpl implements ItemPedidoService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(ItemPedidoServiceImpl.class);

//	@EJB
	@Inject
	ItemPedidoDao itemPedidoDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<ItemPedido> getAll() {
		return itemPedidoDao.findAll(ItemPedido.class);
	}

	public ItemPedido findById(Integer idItemPedido) {
		return itemPedidoDao.find(ItemPedido.class, idItemPedido);
	}

	public List<ItemPedido> findByParameters(ItemPedido itemPedido) {
		return itemPedidoDao.findByParameters(itemPedido);
	}

	public List<ItemPedido> findByParameters(ItemPedido itemPedido, int start, int quantity) {
		return itemPedidoDao.findByParameters(itemPedido, start, quantity);
	}

	public long count() {
		return itemPedidoDao.count(ItemPedido.class);
	}

	public long countByParameters(ItemPedido itemPedido) {
		return itemPedidoDao.countByParameters(itemPedido);
	}

	public void inserirItemPedido(ItemPedido itemPedido) {
		itemPedidoDao.create(itemPedido);
	}

	public void alterarItemPedido(ItemPedido itemPedido) {
		itemPedidoDao.edit(itemPedido);
	}

	public void excluirItemPedido(Integer idItemPedido) {
		itemPedidoDao.delete(ItemPedido.class, idItemPedido);
	}
	
}
