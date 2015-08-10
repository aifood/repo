package com.poc.service;


import java.util.List;

import javax.ejb.Remote;

import com.poc.entity.Categoria;

@Remote
public interface CategoriaService {
	
	Categoria findById(long idCategoria);
	
	void inserirCategoria(Categoria Categoria);
	
	void excluirCategoria(long idCategoria);

	void alterarCategoria(Categoria Categoria);
	
	List<Categoria> findAll();

	long count();
}