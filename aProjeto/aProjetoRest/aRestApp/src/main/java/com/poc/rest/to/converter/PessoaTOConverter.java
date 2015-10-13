package com.poc.rest.to.converter;

import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.rest.to.PessoaTO;

public class PessoaTOConverter {

	public static Pessoa toEntity(PessoaTO to) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(to.getId());
		pessoa.setNome(to.getNome());
		pessoa.setEmail(to.getEmail());
		pessoa.setSenha(to.getSenha());
		pessoa.setTipoPermissao(TipoPermissao.obterPorId(to.getTipoPermissao()));

		// Empresa empresa=EmpresaTOConverter.toEntity(to.getEmpresa)

		return pessoa;
	}

	public static PessoaTO fromEntity(Pessoa entity) {
		PessoaTO pessaoTO = new PessoaTO();
		pessaoTO.setId(entity.getId());
		pessaoTO.setNome(entity.getNome());
		pessaoTO.setEmail(entity.getEmail());
		pessaoTO.setSenha(entity.getSenha());
		pessaoTO.setTipoPermissao(entity.getTipoPermissao().getId());

		return pessaoTO;
	}
}