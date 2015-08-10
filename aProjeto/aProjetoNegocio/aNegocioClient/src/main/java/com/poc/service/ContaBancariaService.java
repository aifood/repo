package com.poc.service;


import javax.ejb.Remote;

import com.poc.entity.ContaBancaria;

@Remote
public interface ContaBancariaService extends ContaService{
	
	ContaBancaria findContaBancariaById(long id);
	
	long countContasBancarias();
}