package com.poc.infra.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.poc.infra.cdi.qualifiers.MySqlDatabase;

public class DatabaseProducer {

	/**
	 * Métodos realacionados à criação de um EntityManager referente ao
	 * perssitence unit que aponta para BD Postgres.
	 */
	@PersistenceUnit(unitName = "MySqlPU")
	EntityManagerFactory postgresEntityManagerFactory;

	@Produces
	@RequestScoped
	@MySqlDatabase
	public EntityManager getPostgresEntityManager() throws Exception {
		return postgresEntityManagerFactory.createEntityManager();
	}

	public void closePostgresEntityManager(@MySqlDatabase @Disposes EntityManager entityManager) {
		entityManager.close();
	}

	/**
	 * Métodos realacionados à criação de um EntityManager referente ao
	 * perssitence unit que aponta para BD Oracle.
	 */

//	@PersistenceUnit(unitName = "OraclePU")
//	EntityManagerFactory oracleEntityManagerFactory;
//
//	@Produces
//	@RequestScoped
//	@OracleDatabase
//	public EntityManager getOracleEntityManager() throws Exception {
//		return oracleEntityManagerFactory.createEntityManager();
//	}
//
//	public void closeOracleEntityManager(@OracleDatabase @Disposes EntityManager entityManager) {
//		entityManager.close();
//	}
}
