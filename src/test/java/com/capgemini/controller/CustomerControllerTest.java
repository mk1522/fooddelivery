//package com.capgemini.controller;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.capgemini.entities.Address;
//import com.capgemini.entities.Customer;
//import com.capgemini.service.ICustomerService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//
//@DisplayName("Customer Controller Test Cases")
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class JUnit5MockitoTest {
//}
//
//public class CustomerControllerTest {
//
//	private MockMvc mockMvc;
//
//	ObjectMapper objectMapper = new ObjectMapper();
//	ObjectWriter objectWriter = objectMapper.writer();
////	
//	@InjectMocks
//	private CustomerController controller;
//
//	@Mock
//	private ICustomerService service;
//
//	Address add1;
//	Address add2;
//
//	Customer c1;
//	Customer c2;
//
//	@BeforeEach
//	public void mockitoSetUp() {
//		MockitoAnnotations.openMocks(this);
//		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}
//
//	@BeforeEach
//	public void initialize() {
//		add1 = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
//		c1 = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", add1, "riyap23@domain.com");
//
//		add2 = new Address("ADD102", "Gokul Park", "R57", "M.K. Road", "Mumbai", "Maharashtra", "India", "401107");
//		c2 = new Customer("C102", "Alina", "Shaikh", 24, "Female", "7856942324", add2, "alinashaikh@domain.com");
//	}
//
////	@Test
////	public void testAddCustomer() throws Exception{
////		MockHttpServletRequest request = new MockHttpServletRequest();
////        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
////        
////        when(service.addCustomer(any(Customer.class))).thenReturn(true);
////        ResponseEntity<Customer> c=controller.addCustomer(c1);
////        
////        assertThat(c.getStatusCodeValue()).isEqualTo(200);
////        
////	}
//////
////	@Test
////	public void testUpdateCustomer() {
////		fail("Not yet implemented");
////	}
////
////	@Test
////	public void testRemoveCustomer() {
////		fail("Not yet implemented");
////	}
//
//	@Test
//	public void testViewCustomer() throws Exception {
//
//		add1 = new Address("ADD101", "Ketki Complex", "2", "Poonam Nagar", "Mumbai", "Maharashtra", "India", "401303");
//		c1 = new Customer("C101", "Riya", "Patel", 22, "Female", "9236142754", add1, "riyap23@domain.com");
//
//		Mockito.when(service.viewCustomer(c1)).thenReturn(c1);
//
////		doReturn(c1).when(service).viewCustomer(c1);
////		ResponseEntity<Customer> c=controller.viewCustomer(c1);
////		
////		assertThat(c.getStatusCodeValue()).isEqualTo(200);
////		//assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
//
//		String content = objectWriter.writeValueAsString(c1);
//
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/customer")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
//
//		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
//				.andExpect(jsonPath("$.customerId", is("C101")));
//	}
//
//}
