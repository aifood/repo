package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.Pedido;

public interface PedidoService extends Serializable {
	
	List<Pedido> getAll();

	Pedido findById(Integer idPedido);
	
	List<Pedido> findByParameters(Pedido pedido);

	List<Pedido> findByParameters(Pedido pedido, int start,
			int quantity);

	long countByParameters(Pedido pedido);
	
	long count();
		
	void inserirPedido(Pedido pedido);

	void alterarPedido(Pedido pedido);

	void excluirPedido(Integer idPedido);
	
}