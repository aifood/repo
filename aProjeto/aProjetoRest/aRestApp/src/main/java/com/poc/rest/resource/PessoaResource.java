package com.poc.rest.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.poc.entity.Pessoa;
import com.poc.rest.to.PessoaTO;
import com.poc.rest.to.converter.PessoaTOConverter;
import com.poc.service.PessoaService;

@Produces({ APPLICATION_XML, APPLICATION_JSON })
@Consumes({ APPLICATION_XML, APPLICATION_JSON })
@Path("/pessoa")
public class PessoaResource {

	@Context
	private UriInfo uriInfo;
	@Inject
	private PessoaService pessoaService;

	@POST
	public Response createPessoa(PessoaTO pessoaTo) {
		if (pessoaTo == null)
			throw new BadRequestException();

		Pessoa pessoa = PessoaTOConverter.toEntity(pessoaTo);

		pessoaService.inserirPessoa(pessoa);

		URI pessoaUri = uriInfo.getAbsolutePathBuilder().path(pessoa.getId().toString()).build();

		return Response.created(pessoaUri).build();
	}

	@GET
	@Path("{idPessoa}")
	public Response getPessoaById(@PathParam("idPessoa") long idPessoa) {
		Pessoa pessoa = pessoaService.findById(idPessoa);

		if (pessoa == null) {
			throw new NotFoundException();
		}

		PessoaTO pessoaTO = PessoaTOConverter.fromEntity(pessoa);

		return Response.ok(pessoaTO).build();
	}

	@DELETE
	@Path("{idPessoa}")
	public Response deletePessoa(@PathParam("idPessoa") long idPessoa) {
		Pessoa pessoa = pessoaService.findById(idPessoa);

		if (pessoa == null) {
			throw new NotFoundException();
		}

		pessoaService.excluirPessoa(idPessoa);

		return Response.noContent().build();
	}
	
	@PUT
	public Response updatePessoa(PessoaTO pessoaTO) {
		Pessoa pessoa = pessoaService.findById(pessoaTO.getId());

		if (pessoa == null) {
			throw new NotFoundException();
		}

		pessoaService.alterarPessoa(PessoaTOConverter.toEntity(pessoaTO));

		return Response.ok().build();
	}
}