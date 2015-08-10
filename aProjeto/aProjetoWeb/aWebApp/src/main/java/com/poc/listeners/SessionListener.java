package com.poc.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.util.SessionManager;

public class SessionListener implements HttpSessionListener {
	protected static Logger log = LogManager.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		if (sc != null) {
			// que controla as sessões ativas
			SessionManager.getInstace().registerLogin(se.getSession().getId(),
					null);
			log.debug("Sess�o iniciada - sem autentica��o ["
					+ se.getSession().getId()
					+ "] - N�mero de sess�es: "
					+ SessionManager.getInstace().getTotalNumberOfSessions()
					+ " -  N�mero de usu�rios logados: "
					+ SessionManager.getInstace()
							.getNumberOfAuthenticatedUsers());
		}

	}

	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		if (sc != null) {
			// SessionManager é um singleton do spring que controla as
			// sessões ativas
			SessionManager.getInstace().registerLogout(se.getSession().getId());
			// decrementa o contador de sess�es ativas
			log.debug("Sess�o finalizada ["
					+ se.getSession().getId()
					+ "]. N�mero de sess�es: "
					+ SessionManager.getInstace().getTotalNumberOfSessions()
					+ " - N�mero de usu�rios logados: "
					+ SessionManager.getInstace()
							.getNumberOfAuthenticatedUsers());
		}

	}

}