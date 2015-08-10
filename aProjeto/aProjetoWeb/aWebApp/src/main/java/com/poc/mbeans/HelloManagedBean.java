package com.poc.mbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poc.ejb.HelloWorld;
import com.poc.entity.Pessoa;
import com.poc.entity.TipoPermissao;
import com.poc.service.PessoaService;
 
@ManagedBean
@SessionScoped
public class HelloManagedBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LogManager.getLogger(HelloManagedBean.class);
 
	private String name;
	
	@EJB
	private HelloWorld ejbHelloWorld;
	
	@EJB
	private PessoaService pessoaService;
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEjbHello(){
		return ejbHelloWorld.sayHello();
	}
	
	public String criarPessoa(){
		log.info("**** MBEAN: criarPessoa()");
		Pessoa p = new Pessoa();
		p.setDataUltimoAcesso(new Date());
		p.setEmail("rafael@dasdas.com");
		p.setNome("Rafael");
		p.setSenha("dasdsad");
		p.setTipoPermissao(TipoPermissao.ADMINISTRADOR);
		
		pessoaService.inserirPessoa(p);
		
		return null;
	}
}