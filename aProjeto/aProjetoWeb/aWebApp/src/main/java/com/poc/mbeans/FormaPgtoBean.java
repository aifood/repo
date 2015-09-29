package com.poc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.entity.FormaPgto;
import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.service.FormaPgtoService;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class FormaPgtoBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private FormaPgto formaPgtoCadastro = new FormaPgto();
	
	//Utilizada na view "lista"
	private FormaPgto formaPgtoSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private FormaPgtoService formaPgtoService;
	
	@Inject
	private EmpresaService empresaService;

	public void cadastrarFormaPgto() {

		//Insere a FormaPgto
		formaPgtoService.inserirFormaPgto(getFormaPgtoCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO,
				formaPgtoCadastro.getDescricao());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		formaPgtoCadastro = new FormaPgto();
	}
	
	public List<FormaPgto> getListaFormaPgtos() {
		FormaPgto formaPgto = new FormaPgto();
		List<FormaPgto> listFormaPgtos = new ArrayList<FormaPgto>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			formaPgto.setEmpresa(getSessionUser().getEmpresa());
			listFormaPgtos = formaPgtoService.findByParameters(formaPgto);
		} else {
			listFormaPgtos = formaPgtoService.getAll();
		}
		
		return listFormaPgtos;
	}
	
	public void excluirFormaPgtoSelecionada() {
		formaPgtoService.excluirFormaPgto(getFormaPgtoSelecionada().getId());	    	    
	}
	
	public void alterarFormaPgtoSelecionada() {
		formaPgtoService.alterarFormaPgto(formaPgtoSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public FormaPgto getFormaPgtoCadastro() {
		if (!isSessionUserAdm()) {
			if (formaPgtoCadastro != null && getSessionUser() != null) {
				formaPgtoCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
		}
		return formaPgtoCadastro;
	}

	public void setFormaPgtoCadastro(FormaPgto formaPgtoCadastro) {
		this.formaPgtoCadastro = formaPgtoCadastro;
	}

	public FormaPgto getFormaPgtoSelecionada() {
		return formaPgtoSelecionada;
	}

	public void setFormaPgtoSelecionada(FormaPgto formaPgtoSelecionada) {
		this.formaPgtoSelecionada = formaPgtoSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
}
