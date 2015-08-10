package com.poc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poc.entity.Pessoa;

import static com.poc.util.ConstantesWeb.LABEL_USUARIO_SESSION;



public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
		ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = hreq.getSession();

		hreq.getPathInfo();
		String paginaAtual = new String(hreq.getRequestURL());

		// dont filter login.jsp because otherwise an endless loop.
		// & only filter .jsp otherwise it will filter all images etc as well.
		if (paginaAtual != null && (!paginaAtual.endsWith("login.xhtml"))
				&& (!paginaAtual.endsWith("pessoa/cadastro.xhtml"))
				&& (!paginaAtual.endsWith("pessoa/error.xhtml"))
				&& (paginaAtual.endsWith(".xhtml"))) {

			// If the session bean is not null get the session bean property
			// username.
			Pessoa usuario = null;
			if (((Pessoa) session.getAttribute(LABEL_USUARIO_SESSION)) != null) {
				usuario = ((Pessoa) session.getAttribute(LABEL_USUARIO_SESSION));
			}

			if (usuario == null) {
				String s = hreq.getContextPath() + "/pages/login.xhtml";
				hresp.sendRedirect(s);
				return;
			}
		}

		chain.doFilter(request, response);
	}
}