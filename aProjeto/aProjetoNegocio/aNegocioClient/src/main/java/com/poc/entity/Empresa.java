package com.poc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;


/**
 * The persistent class for the d000_empresa database table.
 * 
 */
@Entity
@Table(name="d000_empresa")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="D000_EMPRESA_IDEMPRESA_GENERATOR", sequenceName="SQ_EMPRESA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="D000_EMPRESA_IDEMPRESA_GENERATOR")
	@Column(name="id_empresa")
	private long idEmpresa;

	private Boolean ativa;

	private String cnpj;

	@Column(name="hr_fim_atendimento")
	private Time hrFimAtendimento;

	@Column(name="hr_ini_atendimento")
	private Time hrIniAtendimento;

	@Column(name="id_endereco")
	private BigDecimal idEndereco;

	private String nome;

	@Column(name="valor_entrega")
	private BigDecimal valorEntrega;

	public Empresa() {
	}

	public long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Boolean getAtiva() {
		return this.ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Time getHrFimAtendimento() {
		return this.hrFimAtendimento;
	}

	public void setHrFimAtendimento(Time hrFimAtendimento) {
		this.hrFimAtendimento = hrFimAtendimento;
	}

	public Time getHrIniAtendimento() {
		return this.hrIniAtendimento;
	}

	public void setHrIniAtendimento(Time hrIniAtendimento) {
		this.hrIniAtendimento = hrIniAtendimento;
	}

	public BigDecimal getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(BigDecimal idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorEntrega() {
		return this.valorEntrega;
	}

	public void setValorEntrega(BigDecimal valorEntrega) {
		this.valorEntrega = valorEntrega;
	}

}