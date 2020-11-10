package com.cg.paymentapp.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.beans.BillType;
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.rest.CustomerController;
import com.cg.paymentapp.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class TestCustomerController {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private ICustomerService service;

	@Autowired
	private ObjectMapper mapper;

//-------------------Testing Add Customer------------------------//
	@Test

	public void testAddCustomerPositive() throws Exception {
		Customer customer = new Customer("yosh", "996391342", "yoshyosh");
		Mockito.when(service.createAccount(customer)).thenReturn(customer);
		RequestBuilder builder = MockMvcRequestBuilders.post("/addCustomer")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customer))
				.contentType(MediaType.APPLICATION_JSON);
		mvc.perform(builder).andExpect(status().isOk());

	}

	@Test
	public void testAddBillPaymentNegative() throws Exception {
		Customer customer = new Customer("yosh", "996", "yoshyosh");
		Mockito.when(service.createAccount(Mockito.any(Customer.class)))
		.thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.post("/addCustomer")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customer))
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

//-------------------Testing View Customer By Mobile Number------------------------//
	@Test
	public void testViewCustomerByMobileNoPositive() throws Exception {
		Optional<Customer> customer = Optional.of(new Customer("yosh", "996391342", "yoshyosh"));
		Customer customerNew = new Customer("yosh", "996391342", "yoshyosh");
		Mockito.when(service.showCustomer("9963912342")).thenReturn(customer);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewCustomerByMobileNo/9963912342")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(builder).
				andExpect(status().isOk())
				.andExpect(jsonPath("mobileNo", is(customerNew.getMobileNo())))
				.andReturn();

		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void testViewCustomerByMobileNoNegative() throws Exception {
		Mockito.when(service.showCustomer("9989022232")).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewCustomerByMobileNo/9989022232")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

}
