package com.poc.dao;

import javax.ejb.Local;

import com.poc.entity.Conta;

@Local
public interface ContaDao extends PagueCertoBaseDao<Conta> {

}
