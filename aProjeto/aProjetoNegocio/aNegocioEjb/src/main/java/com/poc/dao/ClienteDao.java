package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Cliente;

@Local
public interface ClienteDao extends PagueCertoBaseDao<Cliente> {

	public List<Cliente> findByParameters(Cliente cliente);

	public List<Cliente> findByParameters(Cliente cliente, int start,
			int quantity);

	public long countByParameters(Cliente cliente);

}
