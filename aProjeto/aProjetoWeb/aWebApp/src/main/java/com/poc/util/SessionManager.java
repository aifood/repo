package com.poc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.poc.entity.LoginData;
import com.poc.entity.Pessoa;


public class SessionManager {
	public static Integer loggedUsersNum = 0;
	private static Map<String, LoginData> usersMap = new HashMap<String, LoginData>();
	private static SessionManager instance = new SessionManager();

	public static SessionManager getInstace() {
		if (instance != null) {
			instance = new SessionManager();
		}
		return instance;

	}

	private SessionManager() {

	}

	public synchronized void registerLogout(String sessionId) {
		usersMap.remove(sessionId);

	}

	public synchronized List<LoginData> listAll() {
		return new ArrayList<LoginData>(usersMap.values());
	}

	public synchronized List<Pessoa> listAllLoggedUsers() {
		List<Pessoa> loggedUsers = new ArrayList<Pessoa>();

		List<LoginData> map = new ArrayList<LoginData>(usersMap.values());
		for (LoginData loginData : map) {
			Pessoa pessoa = null;
			if (loginData != null) {
				if (loggedUsers.contains(loginData.getPessoa())) {
					int index = loggedUsers.indexOf(loginData.getPessoa());
					pessoa = loggedUsers.get(index);
					pessoa.getSessionLogins().add(loginData);
					loggedUsers.set(index,pessoa);
				} else {
					pessoa = loginData.getPessoa();
					pessoa.getSessionLogins().add(loginData);
					loggedUsers.add(pessoa);
				}
				
			}
		}

		return loggedUsers;
	}

	public synchronized Integer getTotalNumberOfSessions() {
		return usersMap.size();
	}

	public synchronized Integer getNumberOfAuthenticatedUsers() {
		int number = 0;
		Set<Map.Entry<String, LoginData>> entries = usersMap.entrySet();
		for (Map.Entry<String, LoginData> entry : entries) {
			if (null != entry.getValue()) {
				number++;
			}
		}
		return number;
	}

	public synchronized void registerLogin(String sessionId, LoginData user) {
		usersMap.put(sessionId, user);

	}

}