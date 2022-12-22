package com.capgemini.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Item;
import com.capgemini.entities.Login;
import com.capgemini.entities.Restaurant;
import com.capgemini.service.IItemService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.IRestaurantService;

@RestController
public class RestaurantController {
	@Autowired
	IRestaurantService restaurantService;

	@Autowired
	IItemService itemService;

	@Autowired
	ILoginService loginService;

	@GetMapping("/viewRestaurant")
	public ResponseEntity<Restaurant> viewRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		Restaurant newRestaurant = restaurantService.viewRestaurant(restaurant);
		if (newRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available with give ID!", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		// owner check
		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
		if (!currentUser.isOwner()) {
			throw new IllegalArgumentException("Operation not allowed");
		}

		Restaurant saved = null;
		saved = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@PutMapping("/updateRestaurant")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		// owner check
		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
		if (!currentUser.isOwner()) {
			throw new IllegalArgumentException("Operation not allowed");
		}

		Restaurant newRestaurant = restaurantService.updateRestaurant(restaurant);
		if (newRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(newRestaurant, HttpStatus.OK);
	}

	@DeleteMapping("/removeRestaurant")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		// owner check
		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
		if (!currentUser.isOwner()) {
			throw new IllegalArgumentException("Operation not allowed");
		}

		Restaurant delRestaurant = restaurantService.removeRestaurant(restaurant);
		if (delRestaurant == null) {
			return new ResponseEntity("Sorry! Restaurant not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(delRestaurant, HttpStatus.OK);
	}

	@GetMapping("/restaurant/{location}")
	public ResponseEntity<List<Restaurant>> viewNearByRestaurant(@PathVariable String location) {
		List<Restaurant> nearbyRestaurants = restaurantService.viewNearByRestaurant(location);
		if (nearbyRestaurants == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nearbyRestaurants, HttpStatus.OK);
	}

	@GetMapping("/restaurantByItemName/{name}")
	public ResponseEntity<List<Restaurant>> viewRestaurantByItemName(@PathVariable String name) {
		List<Restaurant> nearbyRestaurantsByItemName = restaurantService.viewRestaurantByItemName(name);
		if (nearbyRestaurantsByItemName == null) {
			return new ResponseEntity("Sorry! Restaurant not available to Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nearbyRestaurantsByItemName, HttpStatus.OK);
	}

	@PutMapping("/restaurant/{restaurantId}/item/{itemId}")
	public ResponseEntity<Restaurant> addItemToRestaurant(@PathVariable String restaurantId,
			@PathVariable String itemId, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		// owner check
		Login currentUser = (Login) request.getSession().getAttribute("userDetails");
		if (!currentUser.isOwner()) {
			throw new IllegalArgumentException("Operation not allowed");
		}

		Restaurant restaurantFromRepo = restaurantService.viewRestaurant(restaurantId);
		Item item = itemService.viewItem(itemId);
		restaurantFromRepo.addItem(item);
		restaurantFromRepo = restaurantService.updateRestaurant(restaurantFromRepo);
		return new ResponseEntity<>(restaurantFromRepo, HttpStatus.OK);

	}
}
