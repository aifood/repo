package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.ItemPedido;

public interface ItemPedidoService extends Serializable {
	
	List<ItemPedido> getAll();

	ItemPedido findById(Integer idItemPedido);
	
	List<ItemPedido> findByParameters(ItemPedido itemPedido);

	List<ItemPedido> findByParameters(ItemPedido itemPedido, int start,
			int quantity);

	long countByParameters(ItemPedido itemPedido);
	
	long count();
		
	void inserirItemPedido(ItemPedido itemPedido);

	void alterarItemPedido(ItemPedido itemPedido);

	void excluirItemPedido(Integer idItemPedido);
	
}