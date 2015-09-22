package com.poc.infra.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.poc.infra.cdi.qualifiers.MySqlDatabase;

@RequestScoped
public class DatabaseProducer {
	
	@PersistenceContext(unitName = "MySqlPU")
	private EntityManager mySql_EntityManager;

	@MySqlDatabase
	@Produces
	@RequestScoped
	public EntityManager getMySqlEntityManager() {
		return mySql_EntityManager;
	}

	public void closePostgresEntityManager(@Disposes @Any EntityManager entityManager) {
		entityManager.close();
	}

	/**
	 * Métodos realacionados à criação de um EntityManager referente ao
	 * perssitence unit que aponta para BD Oracle.
	 */

//	@PersistenceContext(unitName = "OraclePU")
//	private EntityManager oracle_entityManager;
//
//	@OracleDatabase
//	@Produces
//	@RequestScoped
//	public EntityManager getOracleEntityManager() {
//		return oracle_entityManager;
//	}
}
