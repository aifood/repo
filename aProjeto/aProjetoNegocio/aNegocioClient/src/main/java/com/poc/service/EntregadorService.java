package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.Entregador;

public interface EntregadorService extends Serializable {
	
	List<Entregador> getAll();

	Entregador findById(Integer idEntregador);
	
	List<Entregador> findByParameters(Entregador entregador);

	List<Entregador> findByParameters(Entregador entregador, int start,
			int quantity);

	long countByParameters(Entregador entregador);
	
	long count();
		
	void inserirEntregador(Entregador entregador);

	void alterarEntregador(Entregador entregador);

	void excluirEntregador(Integer idEntregador);
	
}