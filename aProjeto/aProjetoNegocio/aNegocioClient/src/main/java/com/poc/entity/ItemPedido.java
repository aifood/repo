/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author cicero.barreto
 */
@Entity
@Table(name = "d007_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
    private Integer id;
    
    @Column(name = "qtd_item")
    private Integer qtdItem = 1;
    
    @Column(name = "valor_item")
    private BigDecimal valorItem;
    
    @ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
    public ItemPedido() {
    }

    public ItemPedido(Integer id) {
        this.id = id;
    }

    public ItemPedido(Integer id, String descricao) {
        this.id = id;                
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public Integer getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(Integer qtdItem) {
		this.qtdItem = qtdItem;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Transient
	public BigDecimal getValorTotal() {
		return this.getValorItem().multiply(new BigDecimal(this.getQtdItem()));
	}
	
	@Transient
	public boolean isProdutoAssociado() {
		return this.getProduto() != null && this.getProduto().getId() != null;
	}
}
