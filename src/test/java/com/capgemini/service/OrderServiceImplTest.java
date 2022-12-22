package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.entities.Address;
import com.capgemini.entities.Category;
import com.capgemini.entities.Customer;
import com.capgemini.entities.FoodCart;
import com.capgemini.entities.Item;
import com.capgemini.entities.OrderDetails;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IOrderRepository;

@SpringBootTest
class OrderServiceImplTest {
	@MockBean
	IOrderRepository repository;

	@Autowired
	IOrderService service;

	LocalDateTime now;
	Address add;
	Customer customer;

	List<Restaurant> res = new ArrayList<>();

	Category cat;
	List<Item> itemList = new ArrayList<>();

	FoodCart foodCart;
	OrderDetails orderObj, orderObj2;

	@BeforeEach
	public void initialize() {
		now = LocalDateTime.now();
		add = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
		customer = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", "riyap23@domain.com", add);

		Restaurant res1 = new Restaurant("R101", "ResA", add, itemList, "9855598555", "1213512135");
		res.add(res1);

		cat = new Category("CT101", "Category1");
		Item item1 = new Item("I101", "ItemA", cat, 50, 50, res);
		itemList.add(item1);

		foodCart = new FoodCart("FC101", customer, itemList);
		orderObj = new OrderDetails(1, now, foodCart, "pending");
	}

	@Test
	void addOrderTest() throws Exception {

		Mockito.when(repository.save(orderObj)).thenReturn(orderObj);
		assertThat(service.addOrder(orderObj)).isEqualTo(orderObj);
	}

	@Test
	void updateOrderTest() throws Exception {

		Mockito.when(repository.save(orderObj)).thenReturn(orderObj);
		service.addOrder(orderObj);

		orderObj.setOrderStatus("Success");
		Mockito.when(repository.existsById(orderObj.getOrderId())).thenReturn(true);
		Mockito.when(repository.findById(orderObj.getOrderId())).thenReturn(Optional.of(orderObj));

		assertThat(service.updateOrder(orderObj)).isEqualTo(orderObj);
	}

	@Test
	void removeOrderTest() throws Exception {
		ArgumentCaptor<OrderDetails> valueCapture = ArgumentCaptor.forClass(OrderDetails.class);
		// void method mocking
		Mockito.doNothing().when(repository).delete(valueCapture.capture());

		Mockito.when(repository.existsById(orderObj.getOrderId())).thenReturn(true);

		assertThat(service.removeOrder(orderObj)).isEqualTo(orderObj);
	}

	@Test
	void viewOrderTest() throws Exception {
		Mockito.doReturn(Optional.of(orderObj)).when(repository).findById(orderObj.getOrderId());
		assertThat(service.viewOrder(orderObj)).isEqualTo(orderObj);
	}
}