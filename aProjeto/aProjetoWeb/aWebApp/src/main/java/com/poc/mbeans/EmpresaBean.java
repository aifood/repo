package com.poc.mbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class EmpresaBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Empresa empresaCadastro = new Empresa();
	
	//Utilizada na view "lista"
	private Empresa empresaSelecionada;

	@Inject
	private EmpresaService empresaService;

	public void cadastrarEmpresa() {

		//Insere a Empresa
		empresaService.inserirEmpresa(getEmpresaCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.EMPRESA_SUCESSO_INSERCAO,
				empresaCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		empresaCadastro = new Empresa();
	}
	
	public List<Empresa> getListaEmpresas() {
		return empresaService.getAll();
	}
	
	public void excluirEmpresaSelecionada() {
		empresaService.excluirEmpresa(getEmpresaSelecionada().getId());	    	    
	}
	
	public void alterarEmpresaSelecionada() {
		empresaService.alterarEmpresa(empresaSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.EMPRESA_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Empresa getEmpresaCadastro() {
		return empresaCadastro;
	}

	public void setEmpresaCadastro(Empresa empresaCadastro) {
		this.empresaCadastro = empresaCadastro;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
}
