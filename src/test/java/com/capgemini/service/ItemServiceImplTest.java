package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IItemRepository;

@SpringBootTest
class ItemServiceImplTest {

	@MockBean
	IItemRepository repository;

	@Autowired
	IItemService service;

	List<Restaurant> res = new ArrayList<>();
	Address add;
	Category cat;

	Item item1;

	@BeforeEach
	public void initialize() {
		add = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
		Restaurant res1 = new Restaurant("R101", "ResA", add, null, "9855598555", "1213512135");
		res.add(res1);

		cat = new Category("CT101", "Category1");
		item1 = new Item("I101", "ItemA", cat, 50, 50, res);
	}

	@Test
	void addItemTest() throws Exception {

		Mockito.when(repository.save(item1)).thenReturn(item1);
		assertThat(service.addItem(item1)).isEqualTo(item1);
	}

	@Test
	void updateItemTest() throws Exception {

		Mockito.when(repository.save(item1)).thenReturn(item1);
		service.addItem(item1);

		Mockito.when(repository.existsById(item1.getItemId())).thenReturn(true);
		Mockito.when(repository.findById(item1.getItemId())).thenReturn(Optional.of(item1));

		item1.setQuantity(40);
		assertThat(service.updateItem(item1)).isEqualTo(item1);
	}

	@Test
	void removeItemTest() throws Exception {
		ArgumentCaptor<Item> valueCapture = ArgumentCaptor.forClass(Item.class);
		// void method mocking
		Mockito.doNothing().when(repository).delete(valueCapture.capture());

		Mockito.when(repository.existsById(item1.getItemId())).thenReturn(true);

		assertThat(service.removeItem(item1)).isEqualTo(item1);
	}

	@Test
	void viewItemTest() throws Exception {
		Mockito.doReturn(Optional.of(item1)).when(repository).findById(item1.getItemId());
		assertThat(service.viewItem(item1)).isEqualTo(item1);
	}

}