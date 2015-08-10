package com.poc.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;


public class FacesUtil {

	private static final String RESOURCE_BASE_NAME = "msg";
	
	/**
	 * Adiciona uma mensagem de atenção ao FacesContext
	 * 
	 * @param key
	 *            - key referente ao arquivo properties
	 */
	public static void adicionarMensagemAtencao(String key,
			Object... argumentos) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, getMensagemAtencao(key, argumentos));
	}

	/**
	 * Adiciona mensagem de atenção a um componente específico<br />
	 * 
	 * A partir do componente fornecido é determinado seu id para a associação
	 * da mensagem. Caso o componente seja null, a mensagem será adicionada ao
	 * FacesContext.<br />
	 * 
	 * @param component
	 *            - Componente que receberá a mensagem
	 * @param key
	 *            - key referente ao arquivo properties
	 * @param argumentos
	 *            - Argumentos da mensagem no arquivo properties
	 */
	public static void adicionarMensagemAtencaoComponenteEspecifico(
			UIComponent component, String key, Object... argumentos) {

		FacesContext ctx = FacesContext.getCurrentInstance();

		String idComponent = component == null ? null : component
				.getContainerClientId(ctx);

		ctx.addMessage(idComponent, getMensagemAtencao(key, argumentos));
	}

	public static FacesMessage getMensagemAtencao(String key,
			Object... argumentos) {
		String mensagem = getMensagem(key, argumentos);
		return new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "");
	}

	/**
	 * Adiciona uma mensagem de sucesso ao FacesContext
	 * 
	 * @param key
	 *            - key referente ao arquivo properties
	 */
	public static void adicionarMensagemSucesso(String key,
			Object... argumentos) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, getMensagemSucesso(key, argumentos));
	}

	public static FacesMessage getMensagemSucesso(String key,
			Object... argumentos) {
		String mensagem = getMensagem(key, argumentos);
		return new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
	}

	/**
	 * Adiciona uma mensagem de erro ao FacesContext
	 * 
	 * @param key
	 *            - key referente ao arquivo properties
	 */
	public static void adicionarMensagemErro(String key, Object... argumentos) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, getMensagemErro(key, argumentos));
	}

	public static FacesMessage getMensagemErro(String key, Object... argumentos) {
		String mensagem = getMensagem(key, argumentos);
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
	}

	public static String getMensagem(String key, Object... argumentos) {
		String msg = null;
		try {
			msg = (String) getBundle().getString(key);
		} catch (MissingResourceException e) {
			msg = key;
		}
		if (argumentos != null && argumentos.length > 0) {
			return MessageFormat.format(msg, argumentos);
		}
		return msg;
	}
	
	public static String getMensagem(String key) {
		Object[] argumentos = null;
		return getMensagem(key, argumentos);
	}

	public static ResourceBundle getBundle() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application application = ctx.getApplication();
		ResourceBundle resource = application.getResourceBundle(ctx,
				RESOURCE_BASE_NAME);
		return resource;
	}

	/**
	 * Este método seta um atributo de um bean com escopo de request com o valor
	 * passado como parâmetro.
	 * 
	 * @param beanAttribute
	 *            Uma string no seguinte formato nomeDoBean.atributo
	 * @param value
	 *            O valor do atributo a ser setado
	 * @param clazz
	 *            O tipo do atributo
	 */
	public static void setAttribute(String beanAttribute, Object value) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();

		String separator = ".";
		String beanName = StringUtils.substringBefore(beanAttribute, separator);
		String propertyName = StringUtils.substring(beanAttribute, beanName
				.length()
				+ separator.length());
		Object object = requestMap.get(beanName);
		try {
			PropertyUtils.setNestedProperty(object, propertyName, value);
		} catch (Exception e) {
		}
	}

	/**
	 * Este método recupera o valor de qualquer atributo passado através da
	 * <i>tag</i> <code>f:attribute</code> quando da chamada de um método de
	 * ação.
	 * 
	 * @param event
	 *            <code>ActionEvent</code> disparado.
	 * @param name
	 *            <code>String</code> do nome do atributo.
	 * @return <code>Object</code> representando o valor do atributo.
	 */
	public static Object getActionAttribute(ActionEvent event, String name) {
		return event.getComponent().getAttributes().get(name);
	}

	/**
	 * Este método recupera o valor de qualquer atributo passado através da
	 * <i>tag</i> <code>f:param</code> quando da chamada de um método de ação
	 * por meio de um <i>commandLink</i>.
	 * 
	 * @param name
	 *            nome do parâmetro.
	 * @return <code>String</code> representando o valor do parâmetro.
	 */
	public static String getRequestParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public static void adicionarValueExpression(UIComponent component,
			String expressao) {
		adicionarValueExpression(component, expressao, String.class);
	}

	public static void adicionarValueExpression(UIComponent component,
			String expressao, Class<? extends Object> tipoAtributo) {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		ValueExpression exp = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory().createValueExpression(
						context, expressao, tipoAtributo);
		component.setValueExpression("value", exp);
	}

	public static void adicionarMethodExpression(UICommand command,
			String expressao) {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		MethodExpression exp = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory()
				.createMethodExpression(context, expressao, Void.class,
						new Class[0]);
		command.setActionExpression(exp);
	}

	public static void adicionarMethodExpressionComParam(UICommand command,
			String expressao, String nomeParam, String valorParam) {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		MethodExpression exp = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory()
				.createMethodExpression(context, expressao, Void.class,
						new Class[0]);
		command.setActionExpression(exp);

		UIParameter param = new UIParameter();
		param.setName(nomeParam);
		param.setValue(valorParam);
		param.setRendered(true);

		command.getChildren().add(param);
	}

//	/**
//	 * Método de tratamento de exceção para Managed Beans. Deverá mostrar
//	 * mensagem de erro padrão para exceções que não sejam de negócio, além de
//	 * mostrar os stacktraces das mesmas. Se for uma exceção de negócio (
//	 * {@link NegocioException}), o stacktrace não será impresso e a mensagem a
//	 * ser exibida será a própria mensagem da exceção. Porém, se for exceção de
//	 * negócio com uma causa definida (e.getCause()) o stacktrace desta será
//	 * impresso, exibindo na tela o sequencial da exceção no log. Retorna por
//	 * padrão {@link TipoOutcome}.ERRO
//	 * 
//	 * 
//	 * @param e
//	 *            - exceção origem
//	 */
//	public static TipoOutcome tratarExcecao(AgeperException e) {
//
//		// se for exceção de negócio, não precisa de stacktrace (a confirmar)
//		if (e.getMessage() != null && !e.getMessage().equals("")) {
//			adicionarMensagemErro(e.getMessage(), e.getArguments());
//			if (e.getCause() != null) {
//				adicionarMensagemErro("ver log para detalhes.", e
//						.getArguments());
//			}
//		} else if (e.getCause() != null) {
//			adicionarMensagemErro(
//					"mensagem.erro.negocio.naoespecificadocomcausa", e
//							.getArguments());
//		} else {
//			adicionarMensagemErro("mensagem.erro.negocio.naoespecificado", e
//					.getArguments());
//		}
//
//		return TipoOutcome.ERRO;
//	}

	/**
	 * Retorna o valor de uma expressão.
	 * 
	 * @param expression
	 *            Expressão.
	 * @param type
	 *            Tipo do objeto esperado.
	 * @return Objeto avaliado da expressão.
	 */
	public static Object evaluateExpression(String expression, Class<?> type) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getApplication().evaluateExpressionGet(
				facesContext, expression, type);
	}

	/**
	 * Retorna uma lista contendo todos os componentes do tipo 'classeProcurada'
	 * que seja filho(neto, bisneto...) de 'componente'
	 * 
	 * @param componente
	 *            Componente qualquer da árvore de componentes
	 * @param classeProcurada
	 *            Classe procurada
	 * @return Lista com os componentes encontrados
	 */
	@SuppressWarnings("unchecked")
	public static <E extends UIComponent> List<E> getListaComponentesDoTipo(
			UIComponent componente, Class<? extends E> classeProcurada) {

		List<E> listaComponentesEncontrados = new ArrayList<E>();

		for (UIComponent componenteFilho : componente.getChildren()) {
			if (classeProcurada.isInstance(componenteFilho)) {
				listaComponentesEncontrados.add((E) componenteFilho);
			}
			if (componenteFilho.getChildCount() > 0) {
				listaComponentesEncontrados.addAll(getListaComponentesDoTipo(
						componenteFilho, classeProcurada));
			}
		}

		return listaComponentesEncontrados;
	}

	/**
	 * Executa a fase de atualização de modelo do JSF para os componentes do
	 * tipo HtmlDataTable. Pode ser util porque quando algum botão com
	 * immediate=true é chamado, os valores digitados são perdidos. Dessa forma
	 * ele força a atualização do bean, consequentemente efetua a validação para
	 * os inputs que sejam filho da dataTable.
	 * 
	 * @param componente
	 */
	public static void atualizarDataTables(UIComponent componente) {
		List<HtmlDataTable> listaDataTables;
		listaDataTables = getListaComponentesDoTipo(componente,
				HtmlDataTable.class);
		for (HtmlDataTable component : listaDataTables) {
			component.processUpdates(FacesContext.getCurrentInstance());
		}
	}

	/**
	 * Verifica se na arvore, a partir do componente especificado, tem algum
	 * campo de preenchimento obrigatório
	 * 
	 * @param componente
	 * @return
	 */
	public static boolean temInputRequired(UIComponent componente) {

		for (UIComponent componenteFilho : componente.getChildren()) {
			if (componenteFilho instanceof UIInput
					&& ((UIInput) componenteFilho).isRequired()) {
				return true;
			}
			if (componenteFilho.getChildCount() > 0) {
				if (temInputRequired(componenteFilho)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Seta um valor numa expression language
	 * 
	 * @param expressionLanguage
	 * @param obj
	 */
	public static void setValueEL(String expressionLanguage, Object obj) {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		ValueExpression valueExpression = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory().createValueExpression(
						elContext, expressionLanguage, Object.class);
		valueExpression.setValue(FacesContext.getCurrentInstance()
				.getELContext(), obj);
	}

	/**
	 * Adiciona um parâmetro em {@link HttpServletRequest} de acordo com nome e
	 * valor informados.
	 * 
	 * @param parametro
	 *            nome do parametro guardado no {@link Map}.
	 * @param valor
	 *            objeto que será mantido no {@link Map}.
	 */
	public static void adicionarParametroRequest(String parametro, Object valor) {
		getRequest().setAttribute(parametro, valor);
	}

	/**
	 * Obtem um objeto nos parâmetros em {@link HttpServletRequest} de acordo
	 * com nome informado.
	 * 
	 * @param parametro
	 *            nome do parametro recuperado no {@link Map}.
	 * @return objeto mantido no {@link Map}.
	 */
	public static Object obterParametroRequest(String parametro) {
		return getRequest().getAttribute(parametro);
	}

	/**
	 * Recupera o {@link HttpServletRequest} do {@link FacesContext}.
	 */
	private static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
}
