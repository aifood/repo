package com.poc.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.poc.entity.LoginData;
import com.poc.entity.Pessoa;
import com.poc.service.PessoaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;
import com.poc.util.SessionManager;

@Named
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	@Inject
	protected Logger log;

	private static final long serialVersionUID = 1L;

	private Pessoa pessoaLogIn = new Pessoa();
	private Boolean rememberMe;

//	@EJB
	@Inject
	private PessoaService pessoaService;

	public String login() {
		pessoaLogIn = pessoaService.findByEmailESenha(pessoaLogIn);

		if (pessoaLogIn == null) {
			FacesUtil.adicionarMensagemErro(ApplicationResources.USUARIO_INVALIDO);
			
			//Reinicializa o objeto. 
			pessoaLogIn = new Pessoa();
			return null;
		} else {
			pessoaLogIn.setDataUltimoAcesso(new Date());

			// Adiciona Pessoa à sessão
			setSessionUser(pessoaLogIn);
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			SessionManager.getInstance().registerLogin(
					request.getSession().getId(),
					new LoginData(pessoaLogIn, pessoaLogIn.getDataUltimoAcesso(), request
							.getSession().getId()));

			return "index";
		}
	}

	public String logout() throws IOException {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		SessionManager.getInstance().registerLogout(req.getSession().getId());
		cleanSessionUser();

		String s = req.getContextPath() + "/pages/login.xhtml";
		HttpServletResponse hresp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		hresp.sendRedirect(s);

		return "login";
	}

	public String cadastrar() {
		return null;
	}

	// Get & Set

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Pessoa getPessoaLogIn() {
		return pessoaLogIn;
	}

	public void setPessoaLogIn(Pessoa pessoaLogIn) {
		this.pessoaLogIn = pessoaLogIn;
	}
}
