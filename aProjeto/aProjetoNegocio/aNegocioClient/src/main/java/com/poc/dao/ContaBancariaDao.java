package com.poc.dao;

import javax.ejb.Local;

import com.poc.entity.ContaBancaria;

@Local
public interface ContaBancariaDao extends PagueCertoBaseDao<ContaBancaria> {

}
