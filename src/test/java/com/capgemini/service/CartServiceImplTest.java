package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import com.capgemini.repo.ICartRepository;

@SpringBootTest
class CartServiceImplTest {

	@MockBean
	ICartRepository repository;

	@Autowired
	CartServiceImpl service;

	FoodCart foodCart1;
	FoodCart foodCart2;
	FoodCart foodCart3;
	Customer customer;
	List<Item> itemList1 = new ArrayList<>();
	Item item1;
	Item item2;
	Category cat;
	List<Restaurant> res = new ArrayList<>();
	Address add;

	@BeforeEach
	public void initialize() {

		add = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
		Restaurant res1 = new Restaurant("R101", "ResA", add, itemList1, "9855598555", "1213512135");

		customer = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", "riyap23@domain.com", add);

		res.add(res1);
		cat = new Category("CT101", "Category1");

		item1 = new Item("I101", "ItemA", cat, 50, 50, res);
		item2 = new Item("I101", "ItemA", cat, 100, 50, res);

		itemList1.add(item1);

		foodCart1 = new FoodCart("FC101", customer, itemList1);
		foodCart2 = new FoodCart("FC101", customer, itemList1);
		foodCart3 = new FoodCart("FC101", customer, itemList1);
	}

	@Test
	void addItemToCartTest() {
		Mockito.when(repository.save(foodCart1)).thenReturn(foodCart1);

		Mockito.doReturn(Optional.of(foodCart1)).when(repository).findById(foodCart1.getCartId());

		assertThat(service.addItemToCart(foodCart1, item2)).isEqualTo(foodCart1);
	}

	@Test
	void IncreaseQuantityTest() {
		Mockito.when(repository.save(foodCart2)).thenReturn(foodCart2);

		Mockito.doReturn(Optional.of(foodCart2)).when(repository).findById(foodCart2.getCartId());

		assertThat(service.increaseQuantity(foodCart2, item1, 50)).isEqualTo(foodCart2);

	}

	@Test
	void DecreaseQuantityTest() {
		Mockito.when(repository.save(foodCart3)).thenReturn(foodCart3);

		Mockito.doReturn(Optional.of(foodCart3)).when(repository).findById(foodCart3.getCartId());

		assertThat(service.reduceQuantity(foodCart3, item1, 50)).isEqualTo(foodCart3);
	}

	@Test
	void removeItemTest() {
		Mockito.when(repository.save(foodCart2)).thenReturn(foodCart2);

		Mockito.doReturn(Optional.of(foodCart2)).when(repository).findById(foodCart2.getCartId());

		assertThat(service.removeItem(foodCart2, item1)).isEqualTo(foodCart2);
	}

	@Test
	void clearCartTest() {
		Mockito.when(repository.save(foodCart2)).thenReturn(foodCart2);

		Mockito.doReturn(Optional.of(foodCart2)).when(repository).findById(foodCart2.getCartId());

		assertThat(service.clearCart(foodCart2)).isEqualTo(foodCart2);
	}

}
