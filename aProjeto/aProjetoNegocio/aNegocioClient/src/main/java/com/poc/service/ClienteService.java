package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.Cliente;

public interface ClienteService extends Serializable {
	
	List<Cliente> getAll();

	Cliente findById(Integer idCliente);
	
	List<Cliente> findByParameters(Cliente cliente);

	List<Cliente> findByParameters(Cliente cliente, int start,
			int quantity);

	long countByParameters(Cliente cliente);
	
	long count();
		
	void inserirCliente(Cliente cliente);

	void alterarCliente(Cliente cliente);

	void excluirCliente(Integer idCliente);
	
}