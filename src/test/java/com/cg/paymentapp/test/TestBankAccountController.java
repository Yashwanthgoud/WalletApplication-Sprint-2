package com.cg.paymentapp.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
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


import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.rest.BankAccountController;
import com.cg.paymentapp.service.IAccountService;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
public class TestBankAccountController {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IAccountService service;
	@Autowired
	private ObjectMapper mapper;

	
	//----------> View method-Positive test <------------
	@Test
	public void testViewBankAccountPositive() throws Exception {
		Optional<BankAccount> bankacc = Optional.of(new BankAccount(54789, "hdfc123", "hdfc", 65412, null));
		//BankAccount bank=new BankAccount(549,"hdfc123","hdfc",65412,null);
		Mockito.when(service.viewAccount(453)).thenReturn(bankacc);

		RequestBuilder builder = MockMvcRequestBuilders.get("/list/453").
				accept(MediaType.APPLICATION_JSON);

		 mvc.perform(builder).andExpect(status().isOk());

	}
	
	//----------> View method-Negative test <------------
	@Test
	public void testViewBankAccountNegative() throws Exception {
		Mockito.when(service.viewAccount(549)).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/list?accountNo=549").accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}
	
	//----------> Add method-Positive test <------------
	@Test
	public void testAddBankAccountPositive() throws Exception {
		BankAccount bank=new BankAccount(0,"ko21","kota",7000.0,null);

		Mockito.when(service.addAccount(Mockito.any(BankAccount.class))).thenReturn(bank);

		RequestBuilder builder = MockMvcRequestBuilders.post("/add").accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bank)).contentType(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isOk());
	}
	
	//----------> Add method-Negative test <------------
	@Test
	public void testAddBankAccountNegative() throws Exception {
		BankAccount bank=new BankAccount(0,"kota123","kota",7000.0,null);

		Mockito.when(service.addAccount(Mockito.any(BankAccount.class))).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.post("/add").accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bank)).contentType(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isNotFound());
	}
	
	//----------> ViewAll method-Positive test <------------
	@Test
	public void TestViewAllPositive() throws Exception{
		List<BankAccount> listacc=new ArrayList<BankAccount>();
		BankAccount bank1=new BankAccount(98745,"ko21","kota",7000.0,null);
		BankAccount bank2=new BankAccount(54789,"hdfc123","hdfc",65412,null);
		listacc.add(bank1);
		listacc.add(bank2);
		Mockito.when(service.viewAllAccounts()).thenReturn(listacc);

		RequestBuilder builder = MockMvcRequestBuilders.get("/listall").accept(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isOk());
	}
	
	//----------> ViewAll method-Negative test <------------
	@Test
	public void TestViewAllNegative() throws Exception{
		Mockito.when(service.viewAllAccounts()).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/listall").accept(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isNotFound());
	}
	
	//----------> Delete method-Negative test <------------
	@Test
	public void TestDeleteBankAccountNegative() throws Exception{
		Mockito.when(service.removeAccount(1)).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.delete("/remove/1").accept(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isNotFound());
	}
	
	//----------> Delete method-Positive test <------------
	@Test
	public void TestDeleteBankAccountPositive() throws Exception{
		BankAccount bank=new BankAccount(85745,"ko21","kota",7000.0,null);
		
		Mockito.when(service.removeAccount(85745)).thenReturn(bank);

		RequestBuilder builder = MockMvcRequestBuilders.delete("/remove/85745").accept(MediaType.APPLICATION_JSON);
		
		mvc.perform(builder).andExpect(status().isOk());
	}
	

}
