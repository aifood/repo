package com.poc.service.impl;


import javax.ejb.Stateless;

import com.poc.entity.ContaBancaria;
import com.poc.service.ContaBancariaService;

@Stateless(name = "ContaBancariaService")
public class ContaBancariaServiceImpl extends ContaServiceImpl implements ContaBancariaService {
	
	public ContaBancaria findContaBancariaById(long id) {
		return contaBancariaDao.find(ContaBancaria.class, id);
	}
	
	public long countContasBancarias() {
		return contaBancariaDao.count(ContaBancaria.class);
	}
}
