package com.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@NamedQueries({
//	@NamedQuery(name = "Pessoa.findAllByParameters", query = "select o from Pessoa o "
//			+ " where (:id is NULL or o.id = :id) "
//			+ " and (:nome is NULL or upper(o.nome) like :nome)"
//			+ " and (:email is NULL or upper(o.email) like :email) "
//			+ " and (:senha is NULL or upper(o.senha) like :senha) "
//			+ " and (:tipoPermissao is NULL or o.tipoPermissao = :tipoPermissao) "
//			+ " ORDER BY o.nome "),
//	@NamedQuery(name = "Pessoa.findQuantityAllByParameters", query = "select count(o.id) from Pessoa o "
//			+ "where (:id is NULL or o.id = :id) "
//			+ " and (:nome is NULL or upper(o.nome) like :nome) "
//			+ " and (:email is NULL or upper(o.email) like :email) "
//			+ " and (:senha is NULL or upper(o.senha) like :senha) "
//			+ " and (:tipoPermissao is NULL or o.tipoPermissao = :tipoPermissao) "
//			+ " ORDER BY o.nome "),
//	@NamedQuery(name = "Pessoa.findByTipoPermissao", query = "select o from Pessoa o "
//			+ "where o.tipoPermissao = :tipoPermissao ") })
@Entity
@Table(name = "PESSOAS")
public class Pessoa implements Serializable {

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PESSOA_GENERATOR")
@SequenceGenerator(name = "ID_PESSOA_GENERATOR", sequenceName = "SQ_PESSOA", allocationSize = 1)
@Column(name = "ID_PESSOA")
private Long id;

@Column(name = "NM_PESSOA")
private String nome;

@Column(name = "NM_EMAIL")
private String email;

@Column(name = "NM_SENHA")
private String senha;

@Column(name = "CS_PERMISSAO")
private Integer tipoPermissao;

@Column(name = "DT_ULTIMO_ACESSO")
@Temporal(TemporalType.TIMESTAMP)
private Date dataUltimoAcesso;

@ManyToOne
@JoinColumn(name = "ID_CONTA_PADRAO", referencedColumnName = "ID_CONTA")
private Conta contaPadrao;

@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinTable(name = "PESSOAS_CONTAS", joinColumns = { @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA") }, inverseJoinColumns = { @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID_CONTA") })
@OrderBy("nome")
private List<Conta> contas;

@Transient
private Set<LoginData> sessionLogins = new HashSet<LoginData>();

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public void setEmail(String email) {
	this.email = email;
}

public String getEmail() {
	return email;
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

public void setSenha(String senha) {
	this.senha = senha;
}

public String getSenha() {
	return senha;
}

public void setDataUltimoAcesso(Date dataUltimoAcesso) {
	this.dataUltimoAcesso = dataUltimoAcesso;
}

public Date getDataUltimoAcesso() {
	return dataUltimoAcesso;
}

public Conta getContaPadrao() {
	return contaPadrao;
}

public void setContaPadrao(Conta contaPadrao) {
	this.contaPadrao = contaPadrao;
}

public List<Conta> getContas() {
	return contas;
}

public void setContas(List<Conta> contas) {
	this.contas = contas;
}

public void setSessionLogins(Set<LoginData> sessionLogins) {
	this.sessionLogins = sessionLogins;
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
