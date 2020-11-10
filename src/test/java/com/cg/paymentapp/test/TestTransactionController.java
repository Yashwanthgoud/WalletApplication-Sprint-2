package com.cg.paymentapp.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.cg.paymentapp.beans.Transaction;
import com.cg.paymentapp.exception.InvalidInputTransactionException;
import com.cg.paymentapp.exception.NoSuchDateFoundException;
import com.cg.paymentapp.rest.TransactionRestController;
import com.cg.paymentapp.service.ITransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionRestController.class)

public class TestTransactionController {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ITransactionService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testAddTransactionP() throws Exception {

		Transaction transaction = new Transaction(17,"deposit",LocalDate.of(2019, 11, 07),2000.0,"Bonus",null);
		
		        when(service.add(transaction)).thenReturn(transaction);
		 
		 RequestBuilder builder = MockMvcRequestBuilders.post("/addt")
				 .accept(MediaType.APPLICATION_JSON)
				 .content(mapper.writeValueAsString(transaction))
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult mvcResult = mvc.perform(builder).andReturn();
		 System.out.println(mvcResult.getResponse().getStatus());	
	}
	
	@Test
	public void testAddTransactionN() throws Exception {

		Transaction transaction = new Transaction(13,"deposit",LocalDate.of(2020, 12, 12),50.0,"gift",null);
		
		        when(service.add(Mockito.any(Transaction.class))).thenThrow(InvalidInputTransactionException.class);
		 
		 RequestBuilder builder = MockMvcRequestBuilders.post("/addt")
				 .accept(MediaType.APPLICATION_JSON)
				 .content(mapper.writeValueAsString(transaction))
				 .contentType(MediaType.APPLICATION_JSON);
		 
		 MvcResult mvcResult = mvc.perform(builder).andReturn();
		 System.out.println(mvcResult.getResponse().getStatus());	
	}
	
	@Test
	public void testViewAllTransactionP() throws Exception{
		Transaction tran = new Transaction(17,"deposit",LocalDate.of(2019, 11, 07),2000.0,"Bonus",null);
		Transaction tran1 = new Transaction(18,"deposit",LocalDate.of(2029, 11, 07),2000.0,"bonus",null);
		
		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(tran1);
		transaction.add(tran);
		when(service.list()).thenReturn(transaction);
		
		mvc.perform(get("http://localhost:8880/view"))
		.andExpect(status().isOk());
		
		
	}
	

	@Test
	public void testViewAllTransactionN() throws Exception{
		Transaction tran = new Transaction();
		Transaction tran1 = new Transaction();
		
		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(tran1);
		transaction.add(tran);
		when(service.list()).thenThrow(InvalidInputTransactionException.class);
		
		mvc.perform(get("http://localhost:8880/view"))
		.andExpect(status().isInternalServerError());
		
		
	}
	
	
	
	
	
	@Test
	public void testViewByTransactionTypeP() throws Exception{
		List<Transaction> tran = new ArrayList<>();
		when(service.viewTransactionByType("deposit")).thenReturn(tran);
		
		mvc.perform(get("http://localhost:8880/viewByTransactionType/deposit"))
		.andExpect(status().isOk());
		
		
	}
	
	@Test
	public void testViewByTransactionTypeN() throws Exception{
		
		when(service.viewTransactionByType("withd")).thenThrow(InvalidInputTransactionException.class);
		
		mvc.perform(get("http://localhost:8880/viewByTransactionType/withd"))
		.andExpect(status().isInternalServerError());
		
	}
	
	@Test
	public void testViewByTransactionDateP() throws Exception{
		List<Transaction> tran = new ArrayList<Transaction>();
		when(service.viewTransactionsByDate(LocalDate.of(2020, 11, 04))).thenReturn(tran);
		
		mvc.perform(get("http://localhost:8880/viewByDate/2020-11-04"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testViewByTransactionDateN() throws Exception{
		
		when(service.viewTransactionsByDate(LocalDate.of(2020, 10, 04))).thenThrow(NoSuchDateFoundException.class);
		
		mvc.perform(get("http://localhost:8880/viewByDate/2020-10-04"))
		.andExpect(status().isInternalServerError());
	}
	
	


}
