package com.capgemini.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Login;
import com.capgemini.repo.ILoginRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	ILoginRepository repository;

	@Override
	public Login signIn(Login login) {
		Login repoObj = repository.findById(login.getUserid()).get();
		if (login.getPassword().equals(repoObj.getPassword())) {
			repoObj.setLoggedIn(true);
			login.setLoggedIn(true);
			repository.save(repoObj);
			return repoObj;
		}
		return null;
	}

	@Override
	public Login signOut(Login login) {
		Login repoObj = repository.findById(login.getUserid()).get();
		if (login.getPassword().equals(repoObj.getPassword())) {
			repoObj.setLoggedIn(false);
			login.setLoggedIn(false);
			repository.save(repoObj);
			return repoObj;
		}
		return null;
	}

	@Override
	public boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			Login currentUser = (Login) session.getAttribute("userDetails");
			if (currentUser.isLoggedIn()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
