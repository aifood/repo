package com.poc.entity;

public enum TipoPermissao {

	ADMINISTRADOR(1, "Administrador"), 
	USUARIO(2, "Usu�rio");

	/**
	 * Identificador da enumeração
	 */
	private final Integer id;

	/**
	 * Descrição da enumeração
	 */
	private final String descricao;

	private TipoPermissao(Integer id, String descricao) {
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
	public static TipoPermissao obterPorId(Integer id) {
		for (TipoPermissao acao : TipoPermissao.values()) {
			if (acao.getId().equals(id)) {
				return acao;
			}
		}
		return null;
	}
}
