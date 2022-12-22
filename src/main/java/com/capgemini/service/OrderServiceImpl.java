package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Customer;
import com.capgemini.entities.OrderDetails;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepo;

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		return orderRepo.save(order);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) {
		OrderDetails orderUpdate = null;
		if (orderRepo.existsById(order.getOrderId()) && orderRepo.findById(order.getOrderId()).get().isActive())
			orderUpdate = orderRepo.save(order);
		return orderUpdate;
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) {
		OrderDetails orderUpdate = order;
		if (orderRepo.existsById(order.getOrderId())) {
			order.setActive(false);
			orderRepo.save(order);
			return orderUpdate;
		}
		return null;
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) {
		OrderDetails showOrder = orderRepo.findById(order.getOrderId()).get();
		if (showOrder.isActive())
			return showOrder;
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant res) {
		List<OrderDetails> list = orderRepo.viewAllOrders(res.getRestaurantId(), res.getRestaurantName());
		List<OrderDetails> newList = new ArrayList<>();
		for (OrderDetails o : list) {
			if (o.isActive())
				newList.add(o);

		}
		return newList;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) {
		List<OrderDetails> list = orderRepo.viewAllOrders(customer.getCustomerId());
		List<OrderDetails> newList = new ArrayList<>();
		for (OrderDetails o : list) {
			if (o.isActive())
				newList.add(o);

		}
		return newList;
	}

}