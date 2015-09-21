package com.poc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.poc.dao.CartaoCreditoDao;
//import com.poc.dao.ContaBancariaDao;
import com.poc.dao.EmpresaDao;
import com.poc.entity.Empresa;
import com.poc.service.EmpresaService;

//@Stateless
public class EmpresaServiceImpl implements EmpresaService {

	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(EmpresaServiceImpl.class);

//	@EJB
	@Inject
	EmpresaDao empresaDao;

//	@EJB
//	ContaBancariaDao contaBancariaDao;
//
//	@EJB
//	CartaoCreditoDao cartaoCreditoDao;

	public List<Empresa> getAll() {
		return empresaDao.findAll(Empresa.class);
	}

	public Empresa findById(Integer idEmpresa) {
		return empresaDao.find(Empresa.class, idEmpresa);
	}

	public List<Empresa> findByParameters(Empresa empresa) {
		return empresaDao.findByParameters(empresa);
	}

	public List<Empresa> findByParameters(Empresa empresa, int start, int quantity) {
		return empresaDao.findByParameters(empresa, start, quantity);
	}

	public long count() {
		return empresaDao.count(Empresa.class);
	}

	public long countByParameters(Empresa empresa) {
		return empresaDao.countByParameters(empresa);
	}

	public void inserirEmpresa(Empresa empresa) {
		empresaDao.create(empresa);
	}

	public void alterarEmpresa(Empresa empresa) {
		empresaDao.edit(empresa);
	}

	public void excluirEmpresa(Integer idEmpresa) {
		empresaDao.delete(Empresa.class, idEmpresa);
	}
	
}
