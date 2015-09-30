package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.ProdutoDao;
import com.poc.entity.Produto;
import com.poc.service.ProdutoService;

//@Stateless
public class ProdutoServiceImpl implements ProdutoService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(ProdutoServiceImpl.class);

//	@EJB
	@Inject
	ProdutoDao produtoDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<Produto> getAll() {
		return produtoDao.findAll(Produto.class);
	}

	public Produto findById(Integer idProduto) {
		return produtoDao.find(Produto.class, idProduto);
	}

	public List<Produto> findByParameters(Produto produto) {
		return produtoDao.findByParameters(produto);
	}

	public List<Produto> findByParameters(Produto produto, int start, int quantity) {
		return produtoDao.findByParameters(produto, start, quantity);
	}

	public long count() {
		return produtoDao.count(Produto.class);
	}

	public long countByParameters(Produto produto) {
		return produtoDao.countByParameters(produto);
	}

	public void inserirProduto(Produto produto) {
		produtoDao.create(produto);
	}

	public void alterarProduto(Produto produto) {
		produtoDao.edit(produto);
	}

	public void excluirProduto(Integer idProduto) {
		produtoDao.delete(Produto.class, idProduto);
	}
	
}
