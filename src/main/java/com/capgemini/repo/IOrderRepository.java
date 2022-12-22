package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

	@Query(value = "select * from order_details where cart_id in "
			+ "(select food_cart_cart_id from food_cart_item_list where "
			+ "item_list_item_id  in (select item_id from restaurant_item_table where restaurant_id in "
			+ "(select restaurant_id from restaurant where restaurant_id=?1)))", nativeQuery = true)
	public List<OrderDetails> viewAllOrders(String resId, String resName);

	@Query(value = "select * from order_details where cart_id in " + "(select cart_id from food_cart where "
			+ "customer_customer_id  in (select customer_id from customer where customer_id=?1"
			+ "))", nativeQuery = true)
	public List<OrderDetails> viewAllOrders(String customerId);

}