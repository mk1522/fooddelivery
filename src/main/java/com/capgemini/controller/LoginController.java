package com.capgemini.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Login;
import com.capgemini.service.ILoginService;

@RestController
public class LoginController {

	@Autowired
	ILoginService loginService;

	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody Login login, HttpServletRequest request) {
		login = loginService.signIn(login);
		if (login == null) {
			throw new IllegalArgumentException("User not found with given credential");
		}
		if (login.isLoggedIn()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userDetails", login);
			return new ResponseEntity<String>("LOGGED_IN", HttpStatus.FOUND);
		}
		return new ResponseEntity<String>("USER NOT FOUND", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/signout")
	public ResponseEntity<String> signOut(HttpServletRequest request) {
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		HttpSession session = request.getSession();
		Login currentUser = (Login) session.getAttribute("userDetails");

		currentUser = loginService.signOut(currentUser);

		request.getSession().invalidate();
		return new ResponseEntity<String>("LOGGED_OUT", HttpStatus.OK);
	}

}
