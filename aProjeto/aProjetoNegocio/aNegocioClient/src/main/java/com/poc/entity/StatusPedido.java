package com.poc.entity;

public enum StatusPedido {

	ORCAMENTO(1, "Orçamento"), 
	EMITIDO(2, "Emitido"), 
	CANCELADO(3, "Cancelado");
	
	private final int codigo;
	
	private String descricao;
	
	StatusPedido(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public static StatusPedido obterPorCodigo (int codigo){
		for (StatusPedido t:values()){
			if (t.getCodigo() == codigo){
				return t;
			}
		}
		return null;
	}
	
}
