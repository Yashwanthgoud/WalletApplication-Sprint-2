package com.cg.paymentapp.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.Mapping;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.beans.BillType;
import com.cg.paymentapp.exception.BillPaymentNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.rest.BillPaymentController;
import com.cg.paymentapp.service.IBillPaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BillPaymentController.class)
public class TestBillPaymentController {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IBillPaymentService service;

	@Autowired
	private ObjectMapper mapper;

//-----------------Testing Add Bill Payment--------------------//
	@Test
	public void testAddBillPaymentPositive() throws Exception {
		BillPayment payment = new BillPayment(9, null, BillType.LPG, 890, LocalDate.now());
		Mockito.when(service.addBillPayment(payment)).thenReturn(payment);
		RequestBuilder builder = MockMvcRequestBuilders.post("/addBillPayment")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(payment))
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isOk());

	}

	@Test
	public void testAddBillPaymentNegative() throws Exception {
		BillPayment payment = new BillPayment(9, null, BillType.LPG, 0, LocalDate.now());

		Mockito.when(service.addBillPayment(Mockito.any(BillPayment.class)))
		.thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.post("/addBillPayment")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(payment))
				.contentType(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

//-----------------Testing Remove Bill Payment--------------------//
	@Test
	public void testDeleteBillPaymentNegative() throws Exception {
		Mockito.when(service.deleteBillId(1))
		.thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.delete("/deleteByBillId?billId=1")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteBillPaymentPositive() throws Exception {
		BillPayment payment = new BillPayment(1, null, BillType.LPG, 890, LocalDate.now());

		Mockito.when(service.deleteBillId(1)).thenReturn(payment);

		RequestBuilder builder = MockMvcRequestBuilders
				.delete("/deleteByBillId?billId=1")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isOk());
	}

//-------------------Testing View Bill Payment------------------------//
	@Test
	public void testViewBillPaymentPositive() throws Exception {
		Optional<BillPayment> payment = Optional.of(new BillPayment(9, null, BillType.LPG, 890, LocalDate.now()));
		//BillPayment paymentNew = new BillPayment(9, null, BillType.LPG, 890, LocalDate.now());
		Mockito.when(service.viewById(9)).thenReturn(payment);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewByBillId?billId=9")
				.accept(MediaType.APPLICATION_JSON);

		 mvc.perform(builder).andExpect(status().isOk());

		//System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void testViewBillPaymentNegative() throws Exception {
		Mockito.when(service.viewById(45)).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewByBillId?billId=45")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

//-------------------Testing View All Bill Payments------------------------//
	@Test
	public void TestViewAllBillPaymentPositive() throws Exception {
		List<BillPayment> listbp = new ArrayList<BillPayment>();
		BillPayment billpayment = new BillPayment(12, null, BillType.DTH, 47851, LocalDate.now());
		BillPayment billpayment1 = new BillPayment(12, null, BillType.DTH, 47851, LocalDate.now());
		listbp.add(billpayment);
		listbp.add(billpayment1);
		Mockito.when(service.viewBillPayment()).thenReturn(listbp);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewAllPayments")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void TestViewAllBillPaymentsNegative() throws Exception {
		Mockito.when(service.viewBillPayment()).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/viewAllPayments")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

}
