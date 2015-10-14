package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.ClienteDao;
import com.poc.entity.Cliente;
import com.poc.service.ClienteService;

//@Stateless
public class ClienteServiceImpl implements ClienteService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(ClienteServiceImpl.class);

//	@EJB
	@Inject
	ClienteDao clienteDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<Cliente> getAll() {
		return clienteDao.findAll(Cliente.class);
	}

	public Cliente findById(Integer idCliente) {
		return clienteDao.find(Cliente.class, idCliente);
	}

	public List<Cliente> findByParameters(Cliente cliente) {
		return clienteDao.findByParameters(cliente);
	}

	public List<Cliente> findByParameters(Cliente cliente, int start, int quantity) {
		return clienteDao.findByParameters(cliente, start, quantity);
	}

	public long count() {
		return clienteDao.count(Cliente.class);
	}

	public long countByParameters(Cliente cliente) {
		return clienteDao.countByParameters(cliente);
	}

	public void inserirCliente(Cliente cliente) {
		clienteDao.create(cliente);
	}

	public void alterarCliente(Cliente cliente) {
		clienteDao.edit(cliente);
	}

	public void excluirCliente(Integer idCliente) {
		clienteDao.delete(Cliente.class, idCliente);
	}
	
}
