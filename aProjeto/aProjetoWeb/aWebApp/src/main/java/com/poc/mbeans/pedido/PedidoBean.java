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
import org.primefaces.event.SelectEvent;

import com.poc.entity.Cliente;
import com.poc.entity.Empresa;
import com.poc.entity.FormaPgto;
import com.poc.entity.ItemPedido;
import com.poc.entity.Pedido;
import com.poc.entity.Produto;
import com.poc.entity.TipoPermissao;
import com.poc.mbeans.BaseBean;
import com.poc.service.ClienteService;
import com.poc.service.EmpresaService;
import com.poc.service.FormaPgtoService;
import com.poc.service.PedidoService;
import com.poc.service.ProdutoService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class PedidoBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Pedido pedidoCadastro = new Pedido();
	
	//Utilizada na view "lista"
	private Pedido pedidoSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private PedidoService pedidoService;
	
	@Inject
	private EmpresaService empresaService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private FormaPgtoService formaPgtoService;
	
	private Produto produtoLinhaEditavel;
	
	public PedidoBean(){
		System.out.println("Entrou");
	}

	public void cadastrarPedido() {

		//Insere a Pedido
		pedidoService.inserirPedido(getPedidoCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO);

		// Reinicia os valores do objeto, limpando os campos do formulário.
		pedidoCadastro = new Pedido();
	}
	
	public List<Pedido> getListaPedidos() {
		Pedido pedido = new Pedido();
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			pedido.setEmpresa(getSessionUser().getEmpresa());
			listPedidos = pedidoService.findByParameters(pedido);
		} else {
			listPedidos = pedidoService.getAll();
		}
		
		return listPedidos;
	}
	
	public void excluirPedidoSelecionada() {
		pedidoService.excluirPedido(getPedidoSelecionada().getId());	    	    
	}
	
	public void alterarPedidoSelecionada() {
		pedidoService.alterarPedido(pedidoSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Pedido getPedidoCadastro() {
		if (!isSessionUserAdm()) {
			if (pedidoCadastro != null && getSessionUser() != null) {
				pedidoCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
		}
		return pedidoCadastro;
	}

	public void setPedidoCadastro(Pedido pedidoCadastro) {
		this.pedidoCadastro = pedidoCadastro;
	}

	public Pedido getPedidoSelecionada() {
		return pedidoSelecionada;
	}

	public void setPedidoSelecionada(Pedido pedidoSelecionada) {
		this.pedidoSelecionada = pedidoSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
	
	public List<Cliente> completarCliente(String nome) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		return this.clienteService.findByParameters(cliente);
	}
	
	public List<Produto> completarProduto(String nome) {
		Produto produto = new Produto();
		produto.setNome(nome);
		return this.produtoService.findByParameters(produto );
	}
	
	public List<FormaPgto> getFormasPagamento() {
		return formaPgtoService.getAll();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.pedidoCadastro.adicionarItemVazio();
			this.recalcularPedido();
		}
	}
	
	public void recalcularPedido() {
		if (this.pedidoCadastro != null) {
			this.pedidoCadastro.recalcularValorTotal();
		}
	}
	
	public void handleSelect(SelectEvent event) {
		this.pedidoCadastro.setCliente((Cliente)event.getObject()); 
	}
	
	public void carregarProdutoLinhaEditavel(SelectEvent event) {
		this.setProdutoLinhaEditavel((Produto)event.getObject());
		ItemPedido item = this.pedidoCadastro.getItens().get(0);
		
		if (this.getProdutoLinhaEditavel() != null) {
			if (this.existeItemComProduto(this.getProdutoLinhaEditavel())) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.getProdutoLinhaEditavel());
				item.setValorItem(this.getProdutoLinhaEditavel().getValor());
				
				this.pedidoCadastro.adicionarItemVazio();
				this.setProdutoLinhaEditavel(null);
				
				this.pedidoCadastro.recalcularValorTotal();
			}
		}
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedidoCadastro().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQtdItem() < 1) {
			if (linha == 0) {
				item.setQtdItem(1);
			} else {
				this.getPedidoCadastro().getItens().remove(linha);
			}
		}
		
		this.pedidoCadastro.recalcularValorTotal();
	}
}
