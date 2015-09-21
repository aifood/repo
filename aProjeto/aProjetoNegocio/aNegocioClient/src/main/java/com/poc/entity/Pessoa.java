package com.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
 * The persistent class for the d003_usuario database table.
 * 
 */
@Entity
@Table(name="d003_usuario")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@Column(name = "cs_permissao")
	private Integer tipoPermissao;

	@Column(name = "dt_ultimo_acesso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimoAcesso;

	//bi-directional many-to-one association to D000Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	@Transient
	private Set<LoginData> sessionLogins = new HashSet<LoginData>();

	public Pessoa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPermissao getTipoPermissao() {
		return TipoPermissao.obterPorId(tipoPermissao);
	}

	public void setTipoPermissao(TipoPermissao tipoPermissao) {
		if (tipoPermissao != null) {
			this.tipoPermissao = tipoPermissao.getId();
		} else
			this.tipoPermissao = null;
	}

	public Date getDataUltimoAcesso() {
		return this.dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getSessions() {
		// Gerando descricao a partir das naturezas obtidas
		StringBuffer desc = new StringBuffer("[");
		Iterator<LoginData> it = sessionLogins.iterator();
		if (it.hasNext()) {
			LoginData ld = it.next();
			desc.append(ld.getSessionId() + "(" + ld.getFullDataLogin() + ")");
		}
		while (it.hasNext()) {
			LoginData ld = it.next();
			desc.append(", ");
			desc.append(ld.getSessionId() + "(" + ld.getFullDataLogin() + ")");
		}
		desc.append("]");
		return desc.toString();

	}

	public Set<LoginData> getSessionLogins() {
		return sessionLogins;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}