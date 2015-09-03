package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.Empresa;

@Local
public interface EmpresaDao extends PagueCertoBaseDao<Empresa> {

	public List<Empresa> findByParameters(Empresa empresa);

	public List<Empresa> findByParameters(Empresa empresa, int start,
			int quantity);

	public long countByParameters(Empresa empresa);

}
