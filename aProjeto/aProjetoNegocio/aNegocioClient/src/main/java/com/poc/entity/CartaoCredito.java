package com.poc.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CARTOES_CREDITO")
@DiscriminatorValue("2")
public class CartaoCredito extends Conta {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NU_LIMITE")
	private BigDecimal limite;

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
}
