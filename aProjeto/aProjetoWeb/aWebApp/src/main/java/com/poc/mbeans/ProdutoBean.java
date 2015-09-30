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

import com.poc.entity.Produto;
import com.poc.entity.Empresa;
import com.poc.entity.TipoPermissao;
import com.poc.service.ProdutoService;
import com.poc.service.EmpresaService;
import com.poc.util.ApplicationResources;
import com.poc.util.FacesUtil;

@Named
@ViewScoped
@Transactional
public class ProdutoBean extends BaseBean implements Serializable {

	protected final Logger log = LogManager.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	//Utilizada na view "cadastro"
	private Produto produtoCadastro = new Produto();
	
	//Utilizada na view "lista"
	private Produto produtoSelecionada;
	
	private List<Empresa> listaEmpresas;

	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private EmpresaService empresaService;

	public void cadastrarProduto() {

		//Insere a Produto
		produtoService.inserirProduto(getProdutoCadastro());
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_INSERCAO,
				produtoCadastro.getNome());

		// Reinicia os valores do objeto, limpando os campos do formulário.
		produtoCadastro = new Produto();
	}
	
	public List<Produto> getListaProdutos() {
		Produto produto = new Produto();
		List<Produto> listProdutos = new ArrayList<Produto>();
		
		if (!isSessionUserAdm() && getSessionUser() != null) {
			produto.setEmpresa(getSessionUser().getEmpresa());
			listProdutos = produtoService.findByParameters(produto);
		} else {
			listProdutos = produtoService.getAll();
		}
		
		return listProdutos;
	}
	
	public void excluirProdutoSelecionada() {
		produtoService.excluirProduto(getProdutoSelecionada().getId());	    	    
	}
	
	public void alterarProdutoSelecionada() {
		produtoService.alterarProduto(produtoSelecionada);
		
		FacesUtil.adicionarMensagemSucesso(ApplicationResources.CLIENTE_SUCESSO_ALTERACAO);
	}
	
	public List<TipoPermissao> getTiposPermissao() {
		return Arrays.asList(TipoPermissao.values());
	}

	// Get & Set
	public Produto getProdutoCadastro() {
		if (!isSessionUserAdm()) {
			if (produtoCadastro != null && getSessionUser() != null) {
				produtoCadastro.setEmpresa(getSessionUser().getEmpresa());
			}
		}
		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public Produto getProdutoSelecionada() {
		return produtoSelecionada;
	}

	public void setProdutoSelecionada(Produto produtoSelecionada) {
		this.produtoSelecionada = produtoSelecionada;
	}
	
	public List<Empresa> getListaEmpresas() {
		listaEmpresas = empresaService.getAll();
		return listaEmpresas;
	}
}
