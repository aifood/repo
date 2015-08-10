package com.poc.dao.impl;


import javax.ejb.Stateless;

import com.poc.dao.ContaDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Conta;

@Stateless(name = "ContaDao")
public class ContaDaoImpl extends PagueCertoBaseDaoImpl<Conta> implements ContaDao{

}
