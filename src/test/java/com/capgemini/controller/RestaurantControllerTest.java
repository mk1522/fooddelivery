//package com.capgemini.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.fail;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.capgemini.entities.Address;
//import com.capgemini.entities.Category;
//import com.capgemini.entities.Item;
//import com.capgemini.entities.Restaurant;
//import com.capgemini.service.IRestaurantService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//
//@DisplayName("Customer Controller Test Cases")
//@ExtendWith(MockitoExtension.class)
//class RestaurantControllerTest {
//
//	private MockMvc mockMvc;
//
//	@InjectMocks
//	RestaurantController controller;
//
//	@Mock
//	IRestaurantService service;
//
//	ObjectMapper objectMapper = new ObjectMapper();
//	ObjectWriter objectWriter = objectMapper.writer();
//
//	List<Item> itemsList = new ArrayList<>();
//	List<Restaurant> restaurants = new ArrayList<>();
////	List<Restaurant> restaurants = new ArrayList<>();
//
//	Address address1;
//	Address address2;
//	Address address3;
//	Address address4;
//	Address address5;
//
//	Restaurant restaurant1;
//	Restaurant restaurant2;
//	Restaurant restaurant3;
//	Restaurant restaurant4;
//	Restaurant restaurant5;
//
//	Category category;
//
//	@BeforeEach
//	public void mockitoSetUp() {
//		MockitoAnnotations.openMocks(this);
//		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}
//
//	@BeforeEach
//	public void initialize() {
//		category = new Category("catId1", "catName1");
//		this.itemsList.add(new Item("item1", "item1", category, 10, 12.3, this.restaurants));
//
//		address1 = new Address("1", "building1", "street1", "area1", "city1", "state1", "country1", "pincode1");
//		address2 = new Address("2", "building2", "street2", "area2", "city2", "state2", "country2", "pincode2");
//		address3 = new Address("3", "building3", "street3", "area3", "city3", "state3", "country3", "pincode3");
//		address4 = new Address("4", "building4", "street4", "area4", "city4", "state4", "country4", "pincode4");
//		address5 = new Address("5", "building5", "street5", "area5", "city5", "state5", "country5", "pincode5");
//
//		restaurant1 = new Restaurant("R1", "reasturant1", address1, this.itemsList, "manager1", "contact1");
//		restaurant2 = new Restaurant("R2", "reasturant2", address2, this.itemsList, "manager2", "contact2");
//		restaurant3 = new Restaurant("R3", "reasturant3", address3, this.itemsList, "manager3", "contact3");
//		restaurant4 = new Restaurant("R4", "reasturant4", address4, this.itemsList, "manager4", "contact4");
//		restaurant5 = new Restaurant("R5", "reasturant5", address5, this.itemsList, "manager5", "contact5");
//
//	}
//
////	@Test
////	void testViewRestaurantByMockito() throws Exception {
////		Mockito.when(service.viewRestaurant(restaurant1)).thenReturn(restaurant1);
////
////		ResponseEntity<Restaurant> restuarant = controller.viewRestaurant(restaurant1);
////
////		assertThat(restuarant.getStatusCodeValue()).isEqualTo(200);
////
////		assertThat(restuarant.getBody().getRestaurantName()).isEqualTo(restaurant1.getRestaurantName());
////	}
//
//	@Test
//	void testViewRestaurant() throws Exception {
////		Mockito.when(service.viewRestaurant(restaurant1)).thenReturn(restaurant1);
////
////		String content = objectWriter.writeValueAsString(restaurant1);
////
////		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/restaurant")
////				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
////
////		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
////				.andExpect(jsonPath("$.restaurantName", is("reasturant1")));
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		when(service.viewRestaurant(restaurant1)).thenReturn(restaurant1);
//
//		ResponseEntity<Restaurant> re = controller.viewRestaurant(restaurant1, request);
//
//		assertThat(re.getStatusCodeValue()).isEqualTo(200);
//
//	}
//
//	@Test
//	void testAddRestaurant() throws Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		when(service.addRestaurant(restaurant1)).thenReturn(restaurant1);
//
//		ResponseEntity<Restaurant> re = controller.addRestaurant(restaurant1, request);
//
//		assertThat(re.getStatusCodeValue()).isEqualTo(200);
//	}
//
//	@Test
//	void testUpdateRestaurant() throws Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		when(service.updateRestaurant(restaurant1)).thenReturn(restaurant1);
//
//		restaurant1.setRestaurantName("new_name");
//
//		ResponseEntity<Restaurant> re = controller.updateRestaurant(restaurant1, request);
//
//		assertThat(re.getStatusCodeValue()).isEqualTo(200);
//	}
//
//	@Test
//	void testRemoveRestaurant() throws Exception {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		when(service.removeRestaurant(restaurant1)).thenReturn(restaurant1);
//
//		ResponseEntity<Restaurant> re = controller.removeRestaurant(restaurant1, request);
//
//		assertThat(re.getStatusCodeValue()).isEqualTo(200);
//	}
//
//	@Test
//	public void testViewRestaurantByItemName() {
////		return repo.viewRestaurantByItemName(name);
//		fail("not yet implemented");
//	}
//
//	@Test
//	public void testViewNearbyRestaurant() {
////		return repo.findById(restaurantId).get();
//		fail("not yet implemented");
//	}
//
//}