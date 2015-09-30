package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.Produto;

public interface ProdutoService extends Serializable {
	
	List<Produto> getAll();

	Produto findById(Integer idProduto);
	
	List<Produto> findByParameters(Produto produto);

	List<Produto> findByParameters(Produto produto, int start,
			int quantity);

	long countByParameters(Produto produto);
	
	long count();
		
	void inserirProduto(Produto produto);

	void alterarProduto(Produto produto);

	void excluirProduto(Integer idProduto);
	
}