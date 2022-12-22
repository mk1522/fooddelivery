package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Customer;
import com.capgemini.entities.OrderDetails;
import com.capgemini.entities.Restaurant;

public interface IOrderService {
	public OrderDetails addOrder(OrderDetails order);

	public OrderDetails updateOrder(OrderDetails order);

	public OrderDetails removeOrder(OrderDetails order);

	public OrderDetails viewOrder(OrderDetails order);

	public List<OrderDetails> viewAllOrders(Restaurant res);

	public List<OrderDetails> viewAllOrders(Customer customer);
}