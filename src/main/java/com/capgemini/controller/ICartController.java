package com.capgemini.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.FoodCart;
import com.capgemini.entities.Item;
import com.capgemini.repo.IItemRepository;
import com.capgemini.service.ICartService;
import com.capgemini.service.ILoginService;

@RestController
public class ICartController {
	@Autowired
	ICartService service;

	@Autowired
	IItemRepository repo;

	@Autowired
	ILoginService loginService;

	@PostMapping("/addItemToCart/{itemId}")
	public ResponseEntity<FoodCart> addItemToCart(@RequestBody FoodCart foodCart, @PathVariable String itemId,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Item item = repo.findById(itemId).get();
		FoodCart addToCart = service.addItemToCart(foodCart, item);
		if (addToCart == null) {

			return new ResponseEntity("Sorry ! item not added to cart", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FoodCart>(addToCart, HttpStatus.OK);

	}

	@PutMapping("/increaseQuantity/{itemId}/{quantity}")
	public ResponseEntity<FoodCart> increaseQuantity(@RequestBody FoodCart foodCart, @PathVariable String itemId,
			@PathVariable int quantity, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Item item = repo.findById(itemId).get();
		FoodCart IncQntCart = service.increaseQuantity(foodCart, item, quantity);
		if (IncQntCart == null) {

			return new ResponseEntity("Sorry ! item not added to cart", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FoodCart>(IncQntCart, HttpStatus.OK);

	}

	@PutMapping("/reduceQuantity/{itemId}/{quantity}")
	public ResponseEntity<FoodCart> reduceQuantity(@RequestBody FoodCart foodCart, @PathVariable String itemId,
			@PathVariable int quantity, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Item item = repo.findById(itemId).get();
		FoodCart DecQntCart = service.reduceQuantity(foodCart, item, quantity);

		return new ResponseEntity<FoodCart>(DecQntCart, HttpStatus.OK);

	}

	@DeleteMapping("/removeItemFromCart/{itemId}")
	public ResponseEntity<FoodCart> removeItem(@RequestBody FoodCart foodCart, @PathVariable String itemId,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Item item = repo.findById(itemId).get();
		FoodCart removeFromCart = service.removeItem(foodCart, item);

		return new ResponseEntity<FoodCart>(removeFromCart, HttpStatus.OK);

	}

	@DeleteMapping("/clearCart")
	public ResponseEntity<FoodCart> clearCart(@RequestBody FoodCart foodCart, HttpServletRequest request) {
		// session checking

		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		FoodCart emptyCart = service.clearCart(foodCart);
		return new ResponseEntity<FoodCart>(emptyCart, HttpStatus.OK);

	}

}