package com.poc.service;


import java.io.Serializable;
import java.util.List;

import com.poc.entity.FormaPgto;

public interface FormaPgtoService extends Serializable {
	
	List<FormaPgto> getAll();

	FormaPgto findById(Integer idFormaPgto);
	
	List<FormaPgto> findByParameters(FormaPgto formaPgto);

	List<FormaPgto> findByParameters(FormaPgto formaPgto, int start,
			int quantity);

	long countByParameters(FormaPgto formaPgto);
	
	long count();
		
	void inserirFormaPgto(FormaPgto formaPgto);

	void alterarFormaPgto(FormaPgto formaPgto);

	void excluirFormaPgto(Integer idFormaPgto);
	
}