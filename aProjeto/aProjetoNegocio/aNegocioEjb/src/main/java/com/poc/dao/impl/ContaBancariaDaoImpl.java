package com.poc.dao.impl;


import javax.ejb.Stateless;

import com.poc.dao.ContaBancariaDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.ContaBancaria;

@Stateless(name = "ContaBancariaDao")
public class ContaBancariaDaoImpl extends PagueCertoBaseDaoImpl<ContaBancaria> implements ContaBancariaDao {

}
