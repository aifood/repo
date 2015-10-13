package com.poc.rest.resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {
	private final Set<Class<?>> classes;

	public ApplicationConfig() {
		HashSet<Class<?>> c = new HashSet<>();
		c.add(PessoaResource.class);
		c.add(MoxyJsonFeature.class);
		// c.add(JacksonFeature.class);
//		c.add(MOXyJsonProvider.class);
		classes = Collections.unmodifiableSet(c);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

//	@Override
//	public Set<Object> getSingletons() {
//		MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
//
//		moxyJsonProvider.setAttributePrefix("@");
//		moxyJsonProvider.setFormattedOutput(true);
//		moxyJsonProvider.setIncludeRoot(true);
//		moxyJsonProvider.setMarshalEmptyCollections(false);
//		moxyJsonProvider.setValueWrapper("$");
//
////		Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
////		namespacePrefixMapper.put("http://www.example.org/customer", "cust");
////		moxyJsonProvider.setNamespacePrefixMapper(namespacePrefixMapper);
////		moxyJsonProvider.setNamespaceSeparator(':');
//
//		HashSet<Object> set = new HashSet<Object>(1);
//		set.add(moxyJsonProvider);
//		return set;
//	}

}