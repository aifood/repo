package com.poc.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.poc.dao.CartaoCreditoDao;
import com.poc.entity.CartaoCredito;
import com.poc.service.CartaoCreditoService;

@Stateless(name = "CartaoCreditoService")
public class CartaoCreditoServiceImpl extends ContaServiceImpl implements CartaoCreditoService {
	
	@EJB
	CartaoCreditoDao cartaoCreditoDao;

	public CartaoCredito findCartaoCreditoById(long id) {
		return cartaoCreditoDao.find(CartaoCredito.class, id);
	}
	
	public long countCartoesCredito() {
		return cartaoCreditoDao.count(CartaoCredito.class);
	}
}
