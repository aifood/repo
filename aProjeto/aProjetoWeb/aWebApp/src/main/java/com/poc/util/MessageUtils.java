package com.poc.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

//import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Classe utilizada para recuperar mensagens e Strings. 
 * Carrega ou do Message Bundle de uma aplicação ou do
 * properties default (<code>br.gov.dataprev.infra.view.jsf.message.Messages</code>)
 *
 */
public final class MessageUtils {
	
	//ocultando construtor
	private MessageUtils() {
		//default
	}
	
	private static final String DEFAULT_BUNDLE = "br.gov.dataprev.infra.view.jsf.message.Messages";
	
	private static ClassLoader getCurrentClassLoader(Object fallbackClass) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = fallbackClass.getClass().getClassLoader();
		}
		return loader; 
	}
	
	/**
	 * Obtem uma string a partir de um resource bundle, correspondente a chave informada.
	 * As strings podem utilizar tokens no formato {i}, onde 'i' corresponde ao indice
	 * no array de params.
	 *  
	 * @param loader ClassLoader para carregamento do resource bundle
	 * @param bundleName nome completo do resource bundle	 
	 * @param locale o locale a ser utilizado	 
	 * @param key chave a ser pesquisada
	 * @param defaultValue valor default caso a chave nao seja encontrada
	 * @param params array de argumentos a serem substituidos na string
	 * @return texto formatado
	 */
	public static String getStringFromBundle(ClassLoader loader, String bundleName, Locale locale, 
			String key, String defaultValue, Object... params) {
		String text = null;		
				
		if (bundleName != null) {
			text = loadStringFromBundle(loader, bundleName, locale, key, null);
		}
		
		if (text == null) {			
			text = loadStringFromBundle(loader, DEFAULT_BUNDLE, locale, key, defaultValue);						
		}
		
		if (text == null) {			
			return key;
		}
		
		//adicionar parametros a mensagem
		if (params != null && params.length > 0) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params);
		}		
		
		return text;
	}
	
	/**
	 * Obtem uma string a partir do message bundle da aplicacao.
	 * Equivale a:
	 * <code>getStringFromBundle(Thread.currentThread().getContextClassLoader(), context.getApplication().getMessageBundle(), 
				context.getViewRoot().getLocale(), key, defaultValue, params)</code>
	 * 
	 * @see MessageUtils#getStringFromBundle(String, Locale, String, String, Object[])
	 * @param key chave a ser pesquisada
	 * @param defaultValue valor default caso a chave nao seja encontrada	 
	 * @param params array de argumentos a serem substituidos na mensagem
	 * @return texto formatado
	 */
	public static String getStringFromBundle(String key, String defaultValue, Object... params) {
		FacesContext context = FacesContext.getCurrentInstance();
		return getStringFromBundle(getCurrentClassLoader(context), context.getApplication().getMessageBundle(), 
				context.getViewRoot().getLocale(), key, defaultValue, params);
	}
	
	private static String loadStringFromBundle(ClassLoader loader, String bundleName, 
			Locale locale, String key, String defaultValue) {		
		String result = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, loader);
		if (bundle != null) {
			try {
				result = bundle.getString(key);
				
			} catch (MissingResourceException e) {
				result = defaultValue;
			}
		}
		return result;
	}
	
	/**
	 * Obtem uma mensagem a partir de um resource bundle, correspondente a chave informada.
	 * As mensagens podem utilizar tokens no formato {i}, onde 'i' corresponde ao indice
	 * no array de params.
	 *	 
	 * @param severity severidade da mensagem
	 * @param key chave a ser pesquisada
	 * @param params array de argumentos a serem substituidos na mensagem
	 * @return mensagem formatada
	 */
	public static FacesMessage getMessageFromBundle(FacesMessage.Severity severity, String key, Object... params) {
		String summary = getStringFromBundle(key, null, params);
		String detail = getStringFromBundle(key + "_detail", summary, params);
		FacesMessage message = new FacesMessage(severity, summary, detail);
		
		return message;
	}	
	
	/**
	 * Obtem o label do componente. Se nao existir, retorna o ID.
	 * 
	 * @param facesContext contexto
	 * @param component componente
	 * @return label
	 */
	public static String getLabel(FacesContext facesContext, UIComponent component) {
        Object label = component.getAttributes().get("label");
        if(label != null) {
            return label.toString();
        }
        
//        ValueExpression expression = component.getValueExpression("label");
//        if(expression != null) {
//            return expression.getExpressionString();
//        }
        
        //Se nao existir label definido, usar o clientId
        return component.getClientId(facesContext);
    }

}
