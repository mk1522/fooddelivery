package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

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
import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IRestaurantRepository;

@SpringBootTest
class RestaurantServiceImplTest {

	@MockBean
	IRestaurantRepository repository;

	@Autowired
	IRestaurantService service;

	List<Item> itemsList = new ArrayList<>();

	Address address1;
	Address address2;
	Address address3;
	Address address4;
	Address address5;

	Restaurant restaurant1;
	Restaurant restaurant2;
	Restaurant restaurant3;
	Restaurant restaurant4;
	Restaurant restaurant5;

	@BeforeEach
	public void initialize() {
		this.itemsList.add(new Item("item1", "item1", null, 10, 12.3, null));

		address1 = new Address("1", "building1", "street1", "area1", "city1", "state1", "country1", "pincode1");
		address2 = new Address("2", "building2", "street2", "area2", "city2", "state2", "country2", "pincode2");
		address3 = new Address("3", "building3", "street3", "area3", "city3", "state3", "country3", "pincode3");
		address4 = new Address("4", "building4", "street4", "area4", "city4", "state4", "country4", "pincode4");
		address5 = new Address("5", "building5", "street5", "area5", "city5", "state5", "country5", "pincode5");

		restaurant1 = new Restaurant("R1", "reasturant1", address1, this.itemsList, "manager1", "contact1");
		restaurant2 = new Restaurant("R2", "reasturant2", address2, this.itemsList, "manager2", "contact2");
		restaurant3 = new Restaurant("R3", "reasturant3", address3, this.itemsList, "manager3", "contact3");
		restaurant4 = new Restaurant("R4", "reasturant4", address4, this.itemsList, "manager4", "contact4");
		restaurant5 = new Restaurant("R5", "reasturant5", address5, this.itemsList, "manager5", "contact5");
	}

	@Test
	void addRestaurantTest() throws Exception {

		Mockito.when(repository.save(restaurant1)).thenReturn(restaurant1);
		Mockito.when(repository.save(restaurant2)).thenReturn(restaurant2);

		assertThat(service.addRestaurant(restaurant2)).isEqualTo(restaurant2);
	}

	@Test
	void updateRestaurantTest() throws Exception {

		Mockito.when(repository.save(restaurant1)).thenReturn(restaurant1);
		Mockito.when(repository.existsById(restaurant1.getRestaurantId())).thenReturn(true);
		Mockito.when(repository.findById(restaurant1.getRestaurantId())).thenReturn(Optional.of(restaurant1));

		restaurant1.setContactNumber("updated number");
		assertThat(service.updateRestaurant(restaurant1)).isEqualTo(restaurant1);
	}

	@Test
	void removeRestaurantTest() throws Exception {
		ArgumentCaptor<Restaurant> valueCapture = ArgumentCaptor.forClass(Restaurant.class);

		doNothing().when(repository).delete(valueCapture.capture());

		Mockito.when(repository.existsById(restaurant1.getRestaurantId())).thenReturn(true);

		assertThat(service.removeRestaurant(restaurant1)).isEqualTo(restaurant1);
	}

	@Test
	void viewRestaurantTest() throws Exception {
		Mockito.doReturn(Optional.of(restaurant1)).when(repository).findById(restaurant1.getRestaurantId());
		assertThat(service.viewRestaurant(restaurant1)).isEqualTo(restaurant1);
	}

	@Test
	void viewNearbyRestaurantTest() throws Exception {
		String location = "India";
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
		restaurants.add(restaurant3);
		restaurants.add(restaurant4);
		Mockito.when(repository.viewNearByRestaurant(location)).thenReturn(restaurants);

		assertThat(service.viewNearByRestaurant(location)).isEqualTo(restaurants);
	}

	@Test
	void viewRestaurantByItemNameTest() throws Exception {
		String name = "tempName";
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
		restaurants.add(restaurant3);
		restaurants.add(restaurant4);
		Mockito.when(repository.viewRestaurantByItemName(name)).thenReturn(restaurants);

		assertThat(service.viewRestaurantByItemName(name)).isEqualTo(restaurants);
	}

}
