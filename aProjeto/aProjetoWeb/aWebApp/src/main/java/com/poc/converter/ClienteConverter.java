package com.poc.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.poc.entity.Cliente;
import com.poc.service.ClienteService;
 
@FacesConverter(forClass = com.poc.entity.Cliente.class)
public class ClienteConverter implements Converter {
 
	@Inject
	private ClienteService clienteService;
	
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        int idCliente;
        try {
        	idCliente = Integer.parseInt(arg2);
        } catch (NumberFormatException exception) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Dog and select it (or use the dropdow)", "Type the name of a Dog and select it (or use the dropdow)"));
        }
 
        return clienteService.findById(idCliente);
    }
 
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
 
        if (arg2 == null) {
            return "";
        }
        Cliente cliente = (Cliente) arg2;
        return String.valueOf(cliente.getId());
    }
}
