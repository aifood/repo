package com.poc.mbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.entity.LoginData;
import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.service.PessoaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;
import com.poc.util.SessionManager;

@Named
@ViewScoped
@Transactional
public class PessoaBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Pessoa pessoaCadastro = new Pessoa();
	
	//Utilizada na view "lista"
	private Pessoa pessoaSelecionada;

	@Inject
	private PessoaService pessoaService;

	public String cadastrar() {

		//Insere a Pessoa
		pessoaCadastro.setDataUltimoAcesso(new Date());
		pessoaService.inserirPessoa(getPessoaCadastro());

		// Adiciona a pessoa à sessão
		setSessionUser(getPessoaCadastro());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		SessionManager.getInstace().registerLogin(request.getSession().getId(),
				new LoginData(getPessoaCadastro(), getPessoaCadastro().getDataUltimoAcesso(), request.getSession().getId()));


		FacesUtil.adicionarMensagemSucesso(ApplicationResources.PESSOA_SUCESSO_INSERCAO,
				pessoaCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		pessoaCadastro = new Pessoa();

		return "index";
	}
	
	public void cadastrarUsuario() {

		//Insere a Pessoa
		pessoaCadastro.setDataUltimoAcesso(new Date());
		pessoaService.inserirPessoa(getPessoaCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.PESSOA_SUCESSO_INSERCAO,
				pessoaCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		pessoaCadastro = new Pessoa();
	}
	
	public List<Pessoa> getListaPessoas() {
		return pessoaService.getAll();
	}
	
	public void excluirPessoaSelecionada() {
		pessoaService.excluirPessoa(getPessoaSelecionada().getId());	    	    
	}
	
	public void alterarPessoaSelecionada() {
		pessoaService.alterarPessoa(pessoaSelecionada);
		
		FacesUtil.adicionarMensagemSucesso("Pessoa Alterada.");
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Pessoa getPessoaCadastro() {
		if (!isSessionUserAdm()) {
			pessoaCadastro.setTipoPermissao(TipoPermissao.USUARIO);
		}
		return pessoaCadastro;
	}

	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
}
