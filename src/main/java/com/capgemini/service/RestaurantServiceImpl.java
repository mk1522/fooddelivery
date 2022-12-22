package com.capgemini.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IRestaurantRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
	@Autowired
	IRestaurantRepository repo;

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		return repo.save(res);
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) {
		Restaurant updated = null;
		if (repo.existsById(res.getRestaurantId()) && repo.findById(res.getRestaurantId()).get().isActive()) {
			updated = repo.save(res);
		}

		return updated;
	}

	@Override
	public Restaurant removeRestaurant(Restaurant res) {
		if (repo.existsById(res.getRestaurantId())) {
			res.setActive(false);
			repo.save(res);
			return res;
		}

		return null;
	}

	@Override
	public Restaurant viewRestaurant(Restaurant res) {
		Restaurant restaurant = repo.findById(res.getRestaurantId()).get();
		if (restaurant != null && restaurant.isActive()) {
			return restaurant;
		}
		return null;
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) {
		List<Restaurant> restaurants = repo.viewNearByRestaurant(location);
		List<Restaurant> activeRestaurants = restaurants.stream().filter(x -> x.isActive())
				.collect(Collectors.toList());
		return activeRestaurants;
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) {
		List<Restaurant> restaurants = repo.viewRestaurantByItemName(name);
		List<Restaurant> activeRestaurants = restaurants.stream().filter(x -> x.isActive())
				.collect(Collectors.toList());
		return activeRestaurants;
	}

	@Override
	public Restaurant viewRestaurant(String restaurantId) {
		Restaurant restaurant = repo.findById(restaurantId).get();
		if (restaurant != null && restaurant.isActive()) {
			return restaurant;
		}
		return null;
	}

}
