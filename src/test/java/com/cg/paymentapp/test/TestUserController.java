package com.cg.paymentapp.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.rest.UserRestController;
import com.cg.paymentapp.service.IUserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class TestUserController {
	
	@Autowired
	private MockMvc mvc;		
	@MockBean
	private IUserService service;
	
	private String mobileNumber;
	private String password;
	
	@Test
	public void TestAddCustomerDetails() throws Exception {
		Customer  customer = new Customer("9876543212","Keer@6754");
		when((service.validateLogin(mobileNumber,password))).thenReturn(customer);
		RequestBuilder builder = MockMvcRequestBuilders.post("/add")
				.accept(MediaType.APPLICATION_JSON).content(customer.toString())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(builder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getStatus());

	}
	
	
	@Test
	public void TestAddCustomerPositive() throws Exception {
		Customer customer = new Customer("9876654332","KsfK@8765");
		
		when(service.validateLogin(mobileNumber, password)).thenReturn(customer);
		
		mvc.perform(post("/add")
				.accept(MediaType.APPLICATION_JSON).content(customer.toString())
				.contentType(MediaType.APPLICATION_JSON));		
	}
	
	
	@Test
	public void TestAddCustomerNegative() throws Exception {
		Customer customer = new Customer("8765432192","YkyK@3119");
		
		when(service.validateLogin(mobileNumber,password)).thenThrow(InvalidInputException.class);
		
		mvc.perform(post("/add")
				.accept(MediaType.APPLICATION_JSON).content(customer.toString())
				.contentType(MediaType.APPLICATION_JSON));		
	}
	
}
