//package com.capgemini.repo;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.capgemini.entities.Address;
//import com.capgemini.entities.Item;
//import com.capgemini.entities.Restaurant;
//
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//class IRestaurantRepositoryTest {
//	@Autowired
//	private IRestaurantRepository repo;
//
//	@Autowired
//	private TestEntityManager entityManager;
//
//	List<Item> itemsList = new ArrayList<>();
//
//	Address address1;
//
//	Restaurant restaurant1;
//
//	@Test
//	void saveRestaurantTest() {
//		this.itemsList.add(new Item("item1", "item1", null, 10, 12.3, null));
//
//		Address address1 = new Address("1", "building1", "street1", "area1", "city1", "state1", "country1", "pincode1");
//
//		Restaurant restaurant1 = new Restaurant("R1", "reasturant1", address1, this.itemsList, "manager1", "contact1");
//
//		Restaurant getFromDb = repo.findById(entityManager.persist(restaurant1).getRestaurantId()).get();
//
//		assertThat(getFromDb).isEqualTo(restaurant1);
//
//	}
//
//}
