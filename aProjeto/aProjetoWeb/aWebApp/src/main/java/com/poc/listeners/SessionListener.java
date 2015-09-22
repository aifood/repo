package com.poc.listeners;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.Logger;

import com.poc.util.SessionManager;

public class SessionListener implements HttpSessionListener {
	
	@Inject
	protected Logger log;

	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		if (sc != null) {
			// que controla as sessÃµes ativas
			SessionManager.getInstance().registerLogin(se.getSession().getId(),
					null);
			log.debug("Sessão iniciada - sem autenticação ["
					+ se.getSession().getId()
					+ "] - Número de sessões: "
					+ SessionManager.getInstance().getTotalNumberOfSessions()
					+ " -  Número de usuários logados: "
					+ SessionManager.getInstance()
							.getNumberOfAuthenticatedUsers());
		}

	}

	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		if (sc != null) {
			// SessionManager Ã© um singleton do spring que controla as
			// sessÃµes ativas
			SessionManager.getInstance().registerLogout(se.getSession().getId());
			// decrementa o contador de sessões ativas
			log.debug("Sessão finalizada ["
					+ se.getSession().getId()
					+ "]. Número de sessões: "
					+ SessionManager.getInstance().getTotalNumberOfSessions()
					+ " - Número de usuários logados: "
					+ SessionManager.getInstance()
							.getNumberOfAuthenticatedUsers());
		}

	}

}