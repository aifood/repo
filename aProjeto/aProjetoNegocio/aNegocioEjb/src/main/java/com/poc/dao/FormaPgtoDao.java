package com.poc.dao;

import java.util.List;

import javax.ejb.Local;

import com.poc.entity.FormaPgto;

@Local
public interface FormaPgtoDao extends PagueCertoBaseDao<FormaPgto> {

	public List<FormaPgto> findByParameters(FormaPgto formaPgto);

	public List<FormaPgto> findByParameters(FormaPgto formaPgto, int start,
			int quantity);

	public long countByParameters(FormaPgto formaPgto);

}
