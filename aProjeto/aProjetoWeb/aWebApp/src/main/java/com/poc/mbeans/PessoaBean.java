package com.poc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;

import com.poc.entity.Empresa;
import com.poc.entity.LoginData;
import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.service.EmpresaService;
import com.poc.service.PessoaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;
import com.poc.util.SessionManager;

@Named
@ViewScoped
@Transactional
public class PessoaBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected Logger log;

	//Utilizada na view "cadastro"
	private Pessoa pessoaCadastro = new Pessoa();
	
	//Utilizada na view "lista"
	private Pessoa pessoaSelecionada = new Pessoa();
	
	private List<Empresa> listaEmpresas;

	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private EmpresaService empresaService;
	
	public String cadastrar() {

		//Insere a Pessoa
		pessoaCadastro.setDataUltimoAcesso(new Date());
		pessoaService.inserirPessoa(getPessoaCadastro());

		// Adiciona a pessoa à sessão
		setSessionUser(getPessoaCadastro());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		SessionManager.getInstance().registerLogin(request.getSession().getId(),
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
		Pessoa pessoa = new Pessoa();
		List<Pessoa> listPessoas = new ArrayList<Pessoa>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			pessoa.setEmpresa(getSessionUser().getEmpresa());
			listPessoas = pessoaService.findByParameters(pessoa);
		} else {
			listPessoas = pessoaService.getAll();
		}
		
		return listPessoas;
	}
	
	public void excluirPessoaSelecionada() {
		if (getSessionUser() != null) {
			if (getPessoaSelecionada().equals(getSessionUser())) {
				FacesUtil.adicionarMensagemAtencao(ApplicationResources.PESSOA_ERRO_REMOCAO_LOGADO);
			}else {
				pessoaService.excluirPessoa(getPessoaSelecionada().getId());
			}
		}
	}
	
	public void alterarPessoaSelecionada() {
		pessoaService.alterarPessoa(pessoaSelecionada);
		
		FacesUtil.adicionarMensagemSucesso("Pessoa Alterada.");
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}
	
	public boolean isExlusaoHabilitada(Pessoa pessoa) {
		if (getSessionUser() != null) {
			if (pessoa.equals(getSessionUser())) {
				return false;
			}
		}
		return true;
	}

	// Get & Set
	public Pessoa getPessoaCadastro() {
		if (!isSessionUserAdm()) {
			if (pessoaCadastro != null && getSessionUser() != null) {
				pessoaCadastro.setTipoPermissao(TipoPermissao.USUARIO);
				pessoaCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
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

	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
}
