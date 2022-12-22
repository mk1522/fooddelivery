package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, String> {

	@Query(value = "select * from customer where customer_id in" + "("
			+ "select customer_customer_id from food_cart where cart_id in" + "("
			+ "select food_cart_cart_id from food_cart_item_list where item_list_item_id in" + "("
			+ "select item_id from restaurant_item_table where restaurant_id = ?1" + ")" + ")"
			+ ")", nativeQuery = true)
	public List<Customer> viewAllCustomers(String restaurantId);

}
