package com.poc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CONTAS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CS_CONTA", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CONTA_GENERATOR")
	@SequenceGenerator(name = "ID_CONTA_GENERATOR", sequenceName = "SQ_CONTA", allocationSize = 1)
	@Column(name = "ID_CONTA")
	private Long id;

	@Column(name = "NM_CONTA")
	private String nome;

	@Column(name = "TE_DESCRICAO")
	private String descricao;

	//Não inclui o cascade nessa via do relacionamento, 
	//para que seja de responsabilidade da Pessoa incluir e excluir contas
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "contas")
	@OrderBy("nome")
	private List<Pessoa> pessoas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "conta")
	private List<Lancamento> lancamentos;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());

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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + "]";
	}

}
