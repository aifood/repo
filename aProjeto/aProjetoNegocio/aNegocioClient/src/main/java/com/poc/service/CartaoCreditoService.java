package com.poc.service;

import javax.ejb.Remote;

import com.poc.entity.CartaoCredito;

@Remote
public interface CartaoCreditoService extends ContaService{
	
	CartaoCredito findCartaoCreditoById(long id);
	
	long countCartoesCredito();
}