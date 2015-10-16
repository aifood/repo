/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author cicero.barreto
 */
@Entity
@Table(name = "d007_pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;
    
    @Column(name = "situacao")
    private Integer situacao = StatusPedido.ORCAMENTO.getCodigo();
    
    @Column(name = "dt_pedido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido;
    
    @Column(name = "vl_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    
    //bi-directional many-to-one association to D000Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	//bi-directional many-to-one association to D003Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Pessoa pessoa;
	
	//bi-directional many-to-one association to D001Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	//bi-directional many-to-one association to D004FormaPgto
	@ManyToOne
	@JoinColumn(name="id_forma_pgto")
	private FormaPgto formaPgto;
	
	//bi-directional many-to-one association to D005Entregador
	@ManyToOne
	@JoinColumn(name="id_entregador")
	private Entregador entregador;
	
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, String descricao) {
        this.id = id;                
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public StatusPedido getSituacao() {
		if (situacao == null) {
			 return null;
		}
		return StatusPedido.obterPorCodigo(situacao);
	}

	public void setSituacao(StatusPedido situacao) {
		this.situacao = situacao.getCodigo();
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		if (empresa != null) {
			total = total.add(empresa.getValorEntrega());
		}
		
		for (ItemPedido item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorItem());
			}
		}
		
		this.setValorTotal(total);
	}
    
    public void adicionarItemVazio() {
		if (this.isOrcamento()) {
			Produto produto = new Produto();
			
			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setPedido(this);
			
			this.getItens().add(0, item);
		}
	}
    
    @Transient
	public BigDecimal getValorSubtotal() {
    	if (this.empresa != null) {
    		return this.getValorTotal().subtract(this.empresa.getValorEntrega());
    	} else {
    		return this.getValorTotal();
    	}
	}

	@Transient
	public boolean isOrcamento() {
		return StatusPedido.ORCAMENTO.equals(this.getSituacao());
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

}
