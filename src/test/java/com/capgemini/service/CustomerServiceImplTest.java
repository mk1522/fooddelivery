package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.ICustomerRepository;

@DisplayName("Customer Service Test Cases")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CustomerServiceImplTest {

	@Autowired
	private ICustomerService service;

	@MockBean
	private ICustomerRepository repository;

	Address add1;
	Address add2;

	Customer c1;
	Customer c2;

	Restaurant rest1;
	List<Restaurant> rest = new ArrayList<>();

	Category cat;
	Item item1;
	List<Item> itemList = new ArrayList<>();

	FoodCart cart;

	@BeforeEach
	public void initialize() {
		add1 = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
		c1 = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", "riyap23@domain.com", add1);

		add2 = new Address("ADD102", "Gokul Park", "R57", "M.K. Road", "Mumbai", "Maharashtra", "India", "401107");
		c2 = new Customer("C102", "Alina", "Shaikh", 24, "Female", "7856942324", "alinashaikh@domain.com", add2);

		cat = new Category("CT101", "Category1");
		item1 = new Item("I101", "ItemA", cat, 50, 50, rest);
		itemList.add(item1);

		rest1 = new Restaurant("R101", "ResA", add1, itemList, "9855598555", "1213512135");
		rest.add(rest1);
		item1.setRestaurants(rest);

		cart = new FoodCart("FC101", c1, itemList);
	}

	@Test
	@Order(1)
	public void testAddCustomer() throws Exception {
		when(repository.save(c1)).thenReturn(c1);
		assertEquals(c1, service.addCustomer(c1));
	}

	@Test
	@Order(3)
	void testUpdateCustomer() throws Exception {
		Mockito.when(repository.save(c1)).thenReturn(c1);
		Mockito.when(repository.existsById(c1.getCustomerId())).thenReturn(true);

		Mockito.when(repository.findById(c1.getCustomerId())).thenReturn(Optional.of(c1));
		assertThat(service.updateCustomer(c1)).isEqualTo(c1);
	}

	@Test
	@Order(4)
	public void testRemoveCustomer() {
		ArgumentCaptor<Customer> valueCapture = ArgumentCaptor.forClass(Customer.class);
		doNothing().when(repository).delete(valueCapture.capture());
		Mockito.when(repository.existsById(c1.getCustomerId())).thenReturn(true);
		assertThat(service.removeCustomer(c1)).isEqualTo(c1);
	}

	@Test
	@Order(2)
	public void testViewCustomer() {
		Mockito.doReturn(Optional.of(c1)).when(repository).findById(c1.getCustomerId());
		assertThat(service.viewCustomer(c1)).isEqualTo(c1);
	}

	@Test
	@Order(5)
	void testViewAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(c1);

		Mockito.when(repository.viewAllCustomers("R101")).thenReturn(customerList);

		List<Customer> res = service.viewAllCustomer(rest1);

		assertThat(res.size()).isEqualTo(1);
	}

}
