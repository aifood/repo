package com.poc.entity;

public enum TipoPagamento {

	DINHEIRO(1, "Dinheiro"), 
	DEBITO_AUTOMATICO(2, "D�bito Autom�tico"),	
	CARTAO_CREDITO(3, "Cart�o Cr�dito"),
	CARTAO_DEBITO(4, "Cart�o D�bito"),
	BOLETO(5, "Boleto"),
	TRANSFERENCIA(6, "Transfer�ncia"),
	CHEQUE(7, "Cheque");

	/**
	 * Identificador da enumeração
	 */
	private final Integer id;

	/**
	 * Descrição da enumeração
	 */
	private final String descricao;

	private TipoPagamento(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Obter por id
	 * 
	 * @param id
	 * @return TipoPermissao
	 */
	public static TipoPagamento obterPorId(Integer id) {
		for (TipoPagamento acao : TipoPagamento.values()) {
			if (acao.getId().equals(id)) {
				return acao;
			}
		}
		return null;
	}
}
