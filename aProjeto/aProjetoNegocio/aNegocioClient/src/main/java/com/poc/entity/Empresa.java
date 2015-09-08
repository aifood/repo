/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cicero.barreto
 */
@Entity
@Table(name = "d000_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.id = :id"),
    @NamedQuery(name = "Empresa.findByNome", query = "SELECT e FROM Empresa e WHERE e.nome = :nome"),
    @NamedQuery(name = "Empresa.findByCnpj", query = "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Empresa.findByAtiva", query = "SELECT e FROM Empresa e WHERE e.ativa = :ativa"),
    @NamedQuery(name = "Empresa.findByValorEntrega", query = "SELECT e FROM Empresa e WHERE e.valorEntrega = :valorEntrega"),
    @NamedQuery(name = "Empresa.findByHrIniAtendimento", query = "SELECT e FROM Empresa e WHERE e.hrIniAtendimento = :hrIniAtendimento"),
    @NamedQuery(name = "Empresa.findByHrFimAtendimento", query = "SELECT e FROM Empresa e WHERE e.hrFimAtendimento = :hrFimAtendimento")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="D000_EMPRESA_IDEMPRESA_GENERATOR", sequenceName="SQ_EMPRESA",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "D000_EMPRESA_IDEMPRESA_GENERATOR")
    @Column(name = "id_empresa")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativa")
    private boolean ativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_entrega")
    private BigDecimal valorEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hr_ini_atendimento")
    @Temporal(TemporalType.TIME)
    private Date hrIniAtendimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hr_fim_atendimento")
    @Temporal(TemporalType.TIME)
    private Date hrFimAtendimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rua")
    private String rua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "complemento")
    private String complemento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    
    public Empresa() {
    }

    public Empresa(long id) {
        this.id = id;
    }

    public Empresa(long id, String nome, String cnpj, boolean ativa, BigDecimal valorEntrega, Date hrIniAtendimento, Date hrFimAtendimento) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ativa = ativa;
        this.valorEntrega = valorEntrega;
        this.hrIniAtendimento = hrIniAtendimento;
        this.hrFimAtendimento = hrFimAtendimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public BigDecimal getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(BigDecimal valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public Date getHrIniAtendimento() {
        return hrIniAtendimento;
    }

    public void setHrIniAtendimento(Date hrIniAtendimento) {
        this.hrIniAtendimento = hrIniAtendimento;
    }

    public Date getHrFimAtendimento() {
        return hrFimAtendimento;
    }

    public void setHrFimAtendimento(Date hrFimAtendimento) {
        this.hrFimAtendimento = hrFimAtendimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return cnpj + " - " + nome;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
