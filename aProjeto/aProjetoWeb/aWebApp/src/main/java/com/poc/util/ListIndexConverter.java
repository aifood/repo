package com.poc.util;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.omnifaces.util.selectitems.SelectItemsUtils;

@FacesConverter(value="ListIndexConverter")
public class ListIndexConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value!=null && !value.isEmpty()){
			List<Object> items = SelectItemsUtils.collectAllValuesFromSelectItems(context, component);
			int index = Integer.valueOf(value);
			if(index>=0 && index<items.size()){
				return items.get(index);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		List<Object> items = SelectItemsUtils.collectAllValuesFromSelectItems(context, component);
		int index = items.indexOf(value);
		if(index==-1){
			return "";
		}
		return String.valueOf(index);
	}

}