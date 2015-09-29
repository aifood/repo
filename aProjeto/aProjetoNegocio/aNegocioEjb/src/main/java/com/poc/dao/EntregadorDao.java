package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Entregador;

@Local
public interface EntregadorDao extends PagueCertoBaseDao<Entregador> {

	public List<Entregador> findByParameters(Entregador entregador);

	public List<Entregador> findByParameters(Entregador entregador, int start,
			int quantity);

	public long countByParameters(Entregador entregador);

}
