package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Restaurant;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {
	// using query
	@Query("select r from Restaurant r where r.address.area like %?1 or r.address.city like %?1 or r.address.streetNo like %?1")
	public List<Restaurant> viewNearByRestaurant(String location);

//	 using query
	@Query(value = "select * from restaurant where restaurant_id in" + "("
			+ "select restaurant_id from restaurant_item_table where "
			+ "item_id = (select item_id from item where item_name = ?1)" + ")", nativeQuery = true)
	public List<Restaurant> viewRestaurantByItemName(String name);
}
