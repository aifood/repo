package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.ItemPedido;

@Local
public interface ItemPedidoDao extends PagueCertoBaseDao<ItemPedido> {

	public List<ItemPedido> findByParameters(ItemPedido itemPedido);

	public List<ItemPedido> findByParameters(ItemPedido itemPedido, int start,
			int quantity);

	public long countByParameters(ItemPedido itemPedido);

}
