package com.poc.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LifeCyclePhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	protected static Logger log = LogManager.getLogger(SessionListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		log.debug("**** Terminando fase: " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		log.debug("**** Iniciando fase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}