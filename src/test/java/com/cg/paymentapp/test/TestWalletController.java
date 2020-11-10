package com.cg.paymentapp.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputException;

import com.cg.paymentapp.rest.WalletController;

import com.cg.paymentapp.service.WalletServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(WalletController.class)
public class TestWalletController {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private WalletServiceImp service;
	@Autowired
	private ObjectMapper mapper;

//-------------------Testing Add Wallet------------------------//
	@Test
	public void testAddWalletPositive() throws Exception {
		Wallet wallet = new Wallet(7, new BigDecimal("800"), null);

		Mockito.when(service.addWallet(wallet)).thenReturn(wallet);

		RequestBuilder builder = MockMvcRequestBuilders.post("/addWallet").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(wallet)).contentType(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isOk());
	}
	
	@Test
	public void testAddWalletNegative() throws Exception {
		Wallet wallet = new Wallet(0, new BigDecimal("200"), null);

		Mockito.when(service.addWallet(Mockito.any(Wallet.class))).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.post("/addWallet").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(wallet)).contentType(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

//-------------------Testing View Wallet------------------------//
	@Test
	public void testViewWalletPositive() throws Exception {
		Optional<Wallet> wallet = Optional.of(new Wallet(2, new BigDecimal("540"), null));

		Mockito.when(service.findWallet(2)).thenReturn(wallet);
		RequestBuilder builder = MockMvcRequestBuilders.get("/listWalletById?walletId=2")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void testViewWallettNegative() throws Exception {
		Mockito.when(service.findWallet(2)).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/listWalletById?walletId=2")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

//-------------------Testing View All Wallets------------------------//
	@Test
	public void TestViewAllWalletPositive() throws Exception {
		List<Wallet> listwall = new ArrayList<Wallet>();
		Wallet wallet = new Wallet(7, new BigDecimal("840"), null);
		Wallet wallet1 = new Wallet(8, new BigDecimal("900"), null);
		listwall.add(wallet);
		listwall.add(wallet1);
		Mockito.when(service.findAllWallet()).thenReturn(listwall);

		RequestBuilder builder = MockMvcRequestBuilders.get("/listWallet").accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	public void TestViewAllWalletsNegative() throws Exception {
		Mockito.when(service.findAllWallet()).thenThrow(InvalidInputException.class);

		RequestBuilder builder = MockMvcRequestBuilders.get("/listWallet").accept(MediaType.APPLICATION_JSON);

		mvc.perform(builder).andExpect(status().isNotFound());
	}

}
