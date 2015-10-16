package com.poc.mbeans.pedido;

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

import com.poc.entity.ItemPedido;
import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.mbeans.BaseBean;
import com.poc.service.ItemPedidoService;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class ItemPedidoBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private ItemPedido itemPedidoCadastro = new ItemPedido();
	
	//Utilizada na view "lista"
	private ItemPedido itemPedidoSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private ItemPedidoService itemPedidoService;
	
	@Inject
	private EmpresaService empresaService;

	public void cadastrarItemPedido() {

		//Insere a ItemPedido
		itemPedidoService.inserirItemPedido(getItemPedidoCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO);

		// Reinicia os valores do objeto, limpando os campos do formulário.
		itemPedidoCadastro = new ItemPedido();
	}
	
	public List<ItemPedido> getListaItemPedidos() {
		List<ItemPedido> listItemPedidos = new ArrayList<ItemPedido>();
		
		listItemPedidos = itemPedidoService.getAll();
		
		
		return listItemPedidos;
	}
	
	public void excluirItemPedidoSelecionada() {
		itemPedidoService.excluirItemPedido(getItemPedidoSelecionada().getId());	    	    
	}
	
	public void alterarItemPedidoSelecionada() {
		itemPedidoService.alterarItemPedido(itemPedidoSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public ItemPedido getItemPedidoCadastro() {
		return itemPedidoCadastro;
	}

	public void setItemPedidoCadastro(ItemPedido itemPedidoCadastro) {
		this.itemPedidoCadastro = itemPedidoCadastro;
	}

	public ItemPedido getItemPedidoSelecionada() {
		return itemPedidoSelecionada;
	}

	public void setItemPedidoSelecionada(ItemPedido itemPedidoSelecionada) {
		this.itemPedidoSelecionada = itemPedidoSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
}
