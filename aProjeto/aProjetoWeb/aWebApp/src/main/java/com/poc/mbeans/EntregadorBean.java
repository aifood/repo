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

import com.poc.entity.Entregador;
import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.service.EntregadorService;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class EntregadorBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Entregador entregadorCadastro = new Entregador();
	
	//Utilizada na view "lista"
	private Entregador entregadorSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private EntregadorService entregadorService;
	
	@Inject
	private EmpresaService empresaService;

	public void cadastrarEntregador() {

		//Insere a Entregador
		entregadorService.inserirEntregador(getEntregadorCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO,
				entregadorCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		entregadorCadastro = new Entregador();
	}
	
	public List<Entregador> getListaEntregadors() {
		Entregador entregador = new Entregador();
		List<Entregador> listEntregadors = new ArrayList<Entregador>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			entregador.setEmpresa(getSessionUser().getEmpresa());
			listEntregadors = entregadorService.findByParameters(entregador);
		} else {
			listEntregadors = entregadorService.getAll();
		}
		
		return listEntregadors;
	}
	
	public void excluirEntregadorSelecionada() {
		entregadorService.excluirEntregador(getEntregadorSelecionada().getId());	    	    
	}
	
	public void alterarEntregadorSelecionada() {
		entregadorService.alterarEntregador(entregadorSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Entregador getEntregadorCadastro() {
		if (!isSessionUserAdm()) {
			if (entregadorCadastro != null && getSessionUser() != null) {
				entregadorCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
		}
		return entregadorCadastro;
	}

	public void setEntregadorCadastro(Entregador entregadorCadastro) {
		this.entregadorCadastro = entregadorCadastro;
	}

	public Entregador getEntregadorSelecionada() {
		return entregadorSelecionada;
	}

	public void setEntregadorSelecionada(Entregador entregadorSelecionada) {
		this.entregadorSelecionada = entregadorSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
}
