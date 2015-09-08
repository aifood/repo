package com.poc.mbeans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.util.SessionManager;

import static com.poc.util.ConstantesWeb.LABEL_USUARIO_SESSION;

//import br.gov.dataprev.infra.view.jsf.application.ViewContext;


public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String RESOURCE_BASE_NAME = "msg";

	public Pessoa getSessionUser() {
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = req.getSession();
		return ((Pessoa) session.getAttribute(LABEL_USUARIO_SESSION));
	}
	
	public boolean getUsuarioLogado(Pessoa pessoa) {
		if (getSessionUser() != null) {
			if (pessoa.equals(getSessionUser())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSessionUserAdm() {
		boolean userAdmLogado = false;
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = req.getSession();
		Pessoa userLogado = ((Pessoa) session.getAttribute(LABEL_USUARIO_SESSION));
		if (userLogado != null) {
			userAdmLogado = userLogado.getTipoPermissao().equals(TipoPermissao.ADMINISTRADOR);
		}
		return userAdmLogado;
	}

	protected void setSessionUser(Pessoa pessoa) {
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().setAttribute(LABEL_USUARIO_SESSION, pessoa);
	}

	protected void cleanSessionUser() {
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().removeAttribute(LABEL_USUARIO_SESSION);
	}
	
	public int getUsuariosAtivos() {
		return SessionManager.getInstace().listAllLoggedUsers().size();
	}

	public List<Pessoa> getListaUsuariosLogados() {
		return SessionManager.getInstace().listAllLoggedUsers();
	}
	
	/**
	 * Adiciona uma mensagem de erro ao FacesContext
	 * 
	 * @param key
	 *            - key referente ao arquivo properties
	 */
	public static void adicionarMensagemErro(String key, Object... argumentos) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, getMensagemErro(key, argumentos));
	}
	
	public static FacesMessage getMensagemErro(String key, Object... argumentos) {
		String mensagem = getMensagem(key, argumentos);
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
	}
	
	public static String getMensagem(String key, Object... argumentos) {
		String msg = null;
		try {
			msg = (String) getBundle().getString(key);
		} catch (MissingResourceException e) {
			msg = key;
		}
		if (argumentos != null && argumentos.length > 0) {
			return MessageFormat.format(msg, argumentos);
		}
		return msg;
	}
	
	public static ResourceBundle getBundle() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application application = ctx.getApplication();
		ResourceBundle resource = application.getResourceBundle(ctx,
				RESOURCE_BASE_NAME);
		return resource;
	}
	
	public void returnFromDialog(){
//		ViewContext.getCurrentInstance().returnFromDialog();
	}
	
	public void returnFromDialog(Object value){
//		ViewContext.getCurrentInstance().returnFromDialog(value);
	}

}
