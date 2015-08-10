package com.poc.service.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.poc.dao.CategoriaDao;
import com.poc.entity.Categoria;
import com.poc.service.CategoriaService;

@Stateless(name = "CategoriaService")
public class CategoriaServiceImpl implements CategoriaService {

	@EJB
	CategoriaDao categoriaDao;

	public Categoria findById(long idCategoria) {
		return categoriaDao.find(Categoria.class, idCategoria);
	}

	public void inserirCategoria(Categoria categoria) {
		categoriaDao.create(categoria);
	}

	public void excluirCategoria(long idCategoria) {
		Categoria categoria = categoriaDao.find(Categoria.class, idCategoria);
		categoriaDao.delete(categoria);
	}

	public void alterarCategoria(Categoria categoria) {
		categoriaDao.edit(categoria);
	}

	public long count() {
		return categoriaDao.count(Categoria.class);
	}

	public List<Categoria> findAll() {
		return categoriaDao.findAll(Categoria.class);
	}
}
