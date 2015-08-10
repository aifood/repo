package com.poc.dao.impl;


import javax.ejb.Stateless;

import com.poc.dao.CategoriaDao;
import com.poc.dao.PagueCertoBaseDaoImpl;
import com.poc.entity.Categoria;

@Stateless(name = "CategoriaDao")
public class CategoriaDaoImpl extends PagueCertoBaseDaoImpl<Categoria> implements CategoriaDao {

}
