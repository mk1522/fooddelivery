package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Restaurant;

public interface IRestaurantService {
	public Restaurant addRestaurant(Restaurant res);

	public Restaurant updateRestaurant(Restaurant res);

	public Restaurant removeRestaurant(Restaurant res);

	public Restaurant viewRestaurant(Restaurant res);

	public List<Restaurant> viewNearByRestaurant(String location);

	public List<Restaurant> viewRestaurantByItemName(String name);

	public Restaurant viewRestaurant(String restaurantId);
}
