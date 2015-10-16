package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Pedido;

@Local
public interface PedidoDao extends PagueCertoBaseDao<Pedido> {

	public List<Pedido> findByParameters(Pedido pedido);

	public List<Pedido> findByParameters(Pedido pedido, int start,
			int quantity);

	public long countByParameters(Pedido pedido);

}
