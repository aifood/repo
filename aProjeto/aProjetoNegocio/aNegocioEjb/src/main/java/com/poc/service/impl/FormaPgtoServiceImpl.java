package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.FormaPgtoDao;
import com.poc.entity.FormaPgto;
import com.poc.service.FormaPgtoService;

//@Stateless
public class FormaPgtoServiceImpl implements FormaPgtoService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(FormaPgtoServiceImpl.class);

//	@EJB
	@Inject
	FormaPgtoDao formaPgtoDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<FormaPgto> getAll() {
		return formaPgtoDao.findAll(FormaPgto.class);
	}

	public FormaPgto findById(Integer idFormaPgto) {
		return formaPgtoDao.find(FormaPgto.class, idFormaPgto);
	}

	public List<FormaPgto> findByParameters(FormaPgto formaPgto) {
		return formaPgtoDao.findByParameters(formaPgto);
	}

	public List<FormaPgto> findByParameters(FormaPgto formaPgto, int start, int quantity) {
		return formaPgtoDao.findByParameters(formaPgto, start, quantity);
	}

	public long count() {
		return formaPgtoDao.count(FormaPgto.class);
	}

	public long countByParameters(FormaPgto formaPgto) {
		return formaPgtoDao.countByParameters(formaPgto);
	}

	public void inserirFormaPgto(FormaPgto formaPgto) {
		formaPgtoDao.create(formaPgto);
	}

	public void alterarFormaPgto(FormaPgto formaPgto) {
		formaPgtoDao.edit(formaPgto);
	}

	public void excluirFormaPgto(Integer idFormaPgto) {
		formaPgtoDao.delete(FormaPgto.class, idFormaPgto);
	}
	
}
