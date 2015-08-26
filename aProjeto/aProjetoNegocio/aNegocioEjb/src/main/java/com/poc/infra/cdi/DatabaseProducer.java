package com.poc.infra.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DatabaseProducer {
 
    @Produces
    @PersistenceContext(unitName = "projetoPU")    
    private EntityManager em;
}
