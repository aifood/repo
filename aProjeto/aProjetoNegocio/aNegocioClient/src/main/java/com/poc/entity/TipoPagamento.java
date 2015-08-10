package com.poc.entity;

public enum TipoPagamento {

	DINHEIRO(1, "Dinheiro"), 
	DEBITO_AUTOMATICO(2, "DÈbito Autom·tico"),	
	CARTAO_CREDITO(3, "Cart„o CrÈdito"),
	CARTAO_DEBITO(4, "Cart„o DÈbito"),
	BOLETO(5, "Boleto"),
	TRANSFERENCIA(6, "TransferÍncia"),
	CHEQUE(7, "Cheque");

	/**
	 * Identificador da enumera√ß√£o
	 */
	private final Integer id;

	/**
	 * Descri√ß√£o da enumera√ß√£o
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
