package com.poc.dao.impl;


import javax.ejb.Stateless;

import com.poc.dao.LancamentoDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Lancamento;

@Stateless(name = "LancamentoDao")
public class LancamentoDaoImpl extends PagueCertoBaseDaoImpl<Lancamento> implements LancamentoDao {

}
