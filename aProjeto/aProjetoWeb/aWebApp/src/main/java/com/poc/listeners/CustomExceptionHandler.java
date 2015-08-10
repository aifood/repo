package com.poc.listeners;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.exception.PocBaseException;
import com.poc.exception.PocExceptionMessage;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private static final String ERROR_MSG = "errorMsg";

	protected static Logger log = LogManager.getLogger(CustomExceptionHandler.class);

	private ExceptionHandler wrapped;

	public CustomExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents()
				.iterator(); it.hasNext();) {
			Throwable t = it.next().getContext().getException();

			while ((t instanceof FacesException || t instanceof EJBException || t instanceof ELException)
					&& t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException) t).getTargetException();
			}

			final FacesContext facesContext = FacesContext.getCurrentInstance();
			final ExternalContext externalContext = facesContext.getExternalContext();

			try {
				if (t instanceof PocBaseException) {
					log.error("Exceção com tratamento", t);
					addMessages((PocBaseException) t);
				}
				// TODO: ver como tratar esse tipo de exceção
				// ViewExpiredException
				// else if (t instanceof ViewExpiredException) {
				// final String viewId = ((ViewExpiredException) t).getViewId();
				// message = "View is expired. <a href='/pages/login.xhtml" +
				// viewId
				// + "'>Back</a>";
				// }
				// Exceções não esperadas são redirecionadas para a página de
				// erro
				else {
					log.error("Exceção não esperada: " + t);
					log.error("MSG Exceção não esperada: " + t.getMessage());
					// Adiciona o erro da Exceção no contexto 'flash', jsf2.
					String message = t.getMessage();
					externalContext.getFlash().put(ERROR_MSG, message);

					// Redireciona para a página de erro
					NavigationHandler navigationHandler = facesContext.getApplication()
							.getNavigationHandler();
					navigationHandler.handleNavigation(facesContext, null,
							ApplicationResources.OUTCOME_ERRO_GERAL);
					facesContext.renderResponse();
				}
			} finally {
				it.remove();
			}
		}

		// Let the parent handle the rest
		getWrapped().handle();
	}

	/**
	 * Adiciona as mensagens no contexto JSF. Se a mensagem possuir uma chave,
	 * procuramos a mensagem associada; caso contrário, apenas adicionamos a
	 * mensagem.
	 * 
	 * @param exception
	 *            Mensagens de erro
	 */
	private final void addMessages(final PocBaseException exception) {
		if (exception.getMessages() != null) {
			for (PocExceptionMessage exceptionMessage : exception.getMessages()) {
				log.error("Adicionando mensagem:" + exceptionMessage.getKey() + " "
						+ exceptionMessage.getDebugMessage());

				FacesUtil.adicionarMensagemErro(exceptionMessage.getKey(),
						exceptionMessage.getParams());
			}
		}
	}
}
