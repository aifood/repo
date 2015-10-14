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

import com.poc.entity.Cliente;
import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.service.ClienteService;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class ClienteBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Cliente clienteCadastro = new Cliente();
	
	//Utilizada na view "lista"
	private Cliente clienteSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private ClienteService clienteService;
	
	@Inject
	private EmpresaService empresaService;

	public void cadastrarCliente() {

		//Insere a Cliente
		clienteService.inserirCliente(getClienteCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO,
				clienteCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		clienteCadastro = new Cliente();
	}
	
	public List<Cliente> getListaClientes() {
		Cliente cliente = new Cliente();
		List<Cliente> listClientes = new ArrayList<Cliente>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			cliente.setEmpresa(getSessionUser().getEmpresa());
			listClientes = clienteService.findByParameters(cliente);
		} else {
			listClientes = clienteService.getAll();
		}
		
		return listClientes;
	}
	
	public void excluirClienteSelecionada() {
		clienteService.excluirCliente(getClienteSelecionada().getId());	    	    
	}
	
	public void alterarClienteSelecionada() {
		clienteService.alterarCliente(clienteSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Cliente getClienteCadastro() {
		if (!isSessionUserAdm()) {
			if (clienteCadastro != null && getSessionUser() != null) {
				clienteCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
		}
		return clienteCadastro;
	}

	public void setClienteCadastro(Cliente clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public Cliente getClienteSelecionada() {
		return clienteSelecionada;
	}

	public void setClienteSelecionada(Cliente clienteSelecionada) {
		this.clienteSelecionada = clienteSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
}
