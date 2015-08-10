package com.poc.dao.impl;

import javax.ejb.Stateless;

import com.poc.dao.CartaoCreditoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.CartaoCredito;

@Stateless(name = "CartaoCreditoDao")
public class CartaoCreditoDaoImpl extends PagueCertoBaseDaoImpl<CartaoCredito> implements CartaoCreditoDao {

}
