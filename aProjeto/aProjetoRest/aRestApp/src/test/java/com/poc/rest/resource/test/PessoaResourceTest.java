package com.poc.rest.resource.test;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBException;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.poc.entity.TipoPermissao;
import com.poc.rest.to.PessoaTO;

public class PessoaResourceTest {

	private static URI uriPessoa = UriBuilder.fromUri("http://localhost/appService/rest/pessoa/")
			.port(8081).build();
	
	private Client client = ClientBuilder.newClient()
			.register(JacksonJsonProvider.class)
			.register(new MoxyJsonFeature());
			//	.register(MOXyJsonProvider.class)

	@Test
	public void createPessoaTest() throws JAXBException {

		PessoaTO pessoa = new PessoaTO();
		pessoa.setNome("Fulano");
		pessoa.setEmail("fulano@teste.com");
		pessoa.setSenha("SenhaFulano");
		pessoa.setTipoPermissao(TipoPermissao.ADMINISTRADOR.getId());

		// Post Pessoa
		Response response = client.target(uriPessoa).request()
				.post(Entity.entity(pessoa, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusInfo()
				.getStatusCode());

		// With the location, GETs Pessoa
		URI pessoaURI = response.getLocation();
		response = client.target(pessoaURI).request().accept(MediaType.APPLICATION_JSON).get();
		PessoaTO pessoaResultado = response.readEntity(PessoaTO.class);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		assertEquals("Fulano", pessoaResultado.getNome());

		// Gets the Pessoa id and DELETEs it
		// String idPessoa = pessoaURI.toString().split("/")[6];
		response = client.target(pessoaURI).request().delete();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatusInfo()
				.getStatusCode());
		// GETs the Pessoa and checks it has been deleted
		response = client.target(pessoaURI).request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusInfo()
				.getStatusCode());
	}

	@Test
	public void shouldNotCreateANullPessoa() throws JAXBException {
		// POSTs a Null Pessoa
		Response response = client.target(uriPessoa).request()
				.post(Entity.entity(null, MediaType.APPLICATION_XML));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusInfo()
				.getStatusCode());
	}

	@Test
	public void shouldNotFindThePessoaID() throws JAXBException {
		// GETs a Pessoa with an unknown ID
		Response response = client.target(uriPessoa).path("9987897").request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusInfo()
				.getStatusCode());
	}
}