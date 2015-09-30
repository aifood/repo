package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Produto;

@Local
public interface ProdutoDao extends PagueCertoBaseDao<Produto> {

	public List<Produto> findByParameters(Produto produto);

	public List<Produto> findByParameters(Produto produto, int start,
			int quantity);

	public long countByParameters(Produto produto);

}
