package com.capgemini.service;

import javax.servlet.http.HttpServletRequest;

import com.capgemini.entities.Login;

public interface ILoginService {
	public Login signIn(Login login);

	public Login signOut(Login login);

	public boolean checkSession(HttpServletRequest request);

}
