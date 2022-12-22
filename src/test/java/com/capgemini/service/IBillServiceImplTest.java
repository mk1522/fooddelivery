package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.entities.Address;
import com.capgemini.entities.Bill;
import com.capgemini.entities.Category;
import com.capgemini.entities.Customer;
import com.capgemini.entities.FoodCart;
import com.capgemini.entities.Item;
import com.capgemini.entities.OrderDetails;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IBillRepository;

@DisplayName("Bill Service Test Cases")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class IBillServiceImplTest {

	@Autowired
	private IBillService service;

	@MockBean
	private IBillRepository repository;

	LocalDateTime now;
	LocalDate date;
	Address add;
	Customer customer;

	List<Restaurant> res = new ArrayList<>();

	Category cat;
	List<Item> itemList = new ArrayList<>();
	FoodCart cart;
	OrderDetails order;
	Bill bill;

	@BeforeEach
	public void initialize() {
		now = LocalDateTime.now();
		date = LocalDate.now();
		add = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
		customer = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", "riyap23@domain.com", add);

		Restaurant res1 = new Restaurant("R101", "ResA", add, itemList, "9855598555", "1213512135");
		res.add(res1);

		cat = new Category("CT101", "Category1");
		Item item1 = new Item("I101", "ItemA", cat, 50, 50, res);
		itemList.add(item1);

		cart = new FoodCart("FC101", customer, itemList);
		order = new OrderDetails(1, now, cart, "pending");

		bill = new Bill("B101", now, order, 4, 780.0);
	}

	@Test
	void testAddBill() {
		Mockito.when(repository.save(bill)).thenReturn(bill);
		assertThat(service.addBill(bill)).isEqualTo(bill);
	}

	@Test
	void testUpdateBill() {
		Mockito.when(repository.save(bill)).thenReturn(bill);
		service.addBill(bill);

		bill.setTotalItem(10);
		Mockito.when(repository.existsById(bill.getBillId())).thenReturn(true);

		assertThat(service.updateBill(bill)).isEqualTo(bill);
	}

	@Test
	void testRemoveBill() {
		ArgumentCaptor<Bill> valueCapture = ArgumentCaptor.forClass(Bill.class);
		// void method mocking
		Mockito.doNothing().when(repository).delete(valueCapture.capture());

		Mockito.when(repository.existsById(bill.getBillId())).thenReturn(true);

		assertThat(service.removeBill(bill)).isEqualTo(bill);
	}

	@Test
	void testViewBill() {
		Mockito.doReturn(Optional.of(bill)).when(repository).findById(bill.getBillId());
		assertThat(service.viewBill(bill)).isEqualTo(bill);
	}

	@Test
	void testViewBills() {
		Bill bill2 = new Bill("B102", now, order, 7, 5200.3);
		List<Bill> billList = new ArrayList<>();
		billList.add(bill2);
		billList.add(bill);

		LocalDate localDate = LocalDate.parse("2023-01-04");

		when(repository.viewBills(now.toLocalDate(), localDate)).thenReturn(billList);

		List<Bill> res = service.viewBills(now.toLocalDate(), localDate);

		assertThat(res.size()).isEqualTo(2);
	}

	@Test
	void testCalculateTotalCost() {
		double c = 0;
		Mockito.when(repository.calculateTotalCost(bill)).thenReturn(c);
		assertThat(service.calculateTotalCost(bill)).isEqualTo(c);
	}

}