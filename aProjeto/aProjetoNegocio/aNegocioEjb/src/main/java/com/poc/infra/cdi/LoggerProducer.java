package com.poc.infra.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProducer {

	@Produces
	public Logger produceLogger(InjectionPoint ip) {
		// Usando log4j
		return LogManager.getLogger((ip.getMember().getDeclaringClass().getName()));
		
		// Usando o java log API do Java 7
		// return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}
}
