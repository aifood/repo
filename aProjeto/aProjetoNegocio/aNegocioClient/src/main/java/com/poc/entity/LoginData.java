package com.poc.entity;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginData implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private Date dataLogin;
	private String sessionId;

	public LoginData(Pessoa pessoa, Date dataLogin, String sessionId) {
		this.dataLogin = dataLogin;
		this.pessoa = pessoa;
		this.sessionId= sessionId;
	}
	
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * @return the full dataLogin
	 */	
	public String getFullDataLogin() {
		String s = null;
		if (dataLogin != null) {
			Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			s = formatter.format(dataLogin);
		}
		return s;
	}	

	

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the dataLogin
	 */
	public Date getDataLogin() {
		return dataLogin;
	}

	/**
	 * @param dataLogin
	 *            the dataLogin to set
	 */
	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataLogin == null) ? 0 : dataLogin.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginData other = (LoginData) obj;
		if (dataLogin == null) {
			if (other.dataLogin != null)
				return false;
		} else if (!dataLogin.equals(other.dataLogin))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	
	
}