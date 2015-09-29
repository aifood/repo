/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cicero.barreto
 */
@Entity
@Table(name = "d004_forma_pgto")
public class FormaPgto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_forma_pgto")
    private Integer id;
    
    @Column(name = "descricao")
    private String descricao;
    
    //bi-directional many-to-one association to D000Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
    
    public FormaPgto() {
    }

    public FormaPgto(Integer id) {
        this.id = id;
    }

    public FormaPgto(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;        
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

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FormaPgto)) {
            return false;
        }
        FormaPgto other = (FormaPgto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
