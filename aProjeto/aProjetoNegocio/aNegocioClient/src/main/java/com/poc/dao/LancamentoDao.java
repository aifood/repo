package com.poc.dao;

import javax.ejb.Local;

import com.poc.entity.Lancamento;

@Local
public interface LancamentoDao extends PagueCertoBaseDao<Lancamento> {

}
