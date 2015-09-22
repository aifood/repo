package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.Empresa;


public interface EmpresaService extends Serializable {
	
	List<Empresa> getAll();

	Empresa findById(Integer idEmpresa);
	
	List<Empresa> findByParameters(Empresa empresa);

	List<Empresa> findByParameters(Empresa empresa, int start,
			int quantity);

	long countByParameters(Empresa empresa);
	
	long count();

	void inserirEmpresa(Empresa empresa);

	void alterarEmpresa(Empresa empresa);

	void excluirEmpresa(Integer idEmpresa);		
}