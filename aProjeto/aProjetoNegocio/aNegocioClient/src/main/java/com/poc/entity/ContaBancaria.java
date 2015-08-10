package com.poc.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONTAS_BANCARIAS")
@DiscriminatorValue("1")
public class ContaBancaria extends Conta {
	private static final long serialVersionUID = 1L;
}
