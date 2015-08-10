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
			// que controla as sessÃµes ativas
			SessionManager.getInstace().registerLogin(se.getSession().getId(),
					null);
			log.debug("Sessão iniciada - sem autenticação ["
					+ se.getSession().getId()
					+ "] - Número de sessões: "
					+ SessionManager.getInstace().getTotalNumberOfSessions()
					+ " -  Número de usuários logados: "
					+ SessionManager.getInstace()
							.getNumberOfAuthenticatedUsers());
		}

	}

	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		if (sc != null) {
			// SessionManager Ã© um singleton do spring que controla as
			// sessÃµes ativas
			SessionManager.getInstace().registerLogout(se.getSession().getId());
			// decrementa o contador de sessões ativas
			log.debug("Sessão finalizada ["
					+ se.getSession().getId()
					+ "]. Número de sessões: "
					+ SessionManager.getInstace().getTotalNumberOfSessions()
					+ " - Número de usuários logados: "
					+ SessionManager.getInstace()
							.getNumberOfAuthenticatedUsers());
		}

	}

}