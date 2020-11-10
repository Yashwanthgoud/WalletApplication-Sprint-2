package com.cg.paymentapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.mockito.Mockito.when; // ...or...
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.InvalidAttributeValueException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.cg.paymentapp.beans.BenificiaryDetails;
import com.cg.paymentapp.rest.BenificiaryDetailsController;
import com.cg.paymentapp.exception.BenificiaryNotFoundException;
import com.cg.paymentapp.repo.IBenificiaryRepository;
import com.cg.paymentapp.service.BenificiaryService;
import com.cg.paymentapp.service.IBenificiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BenificiaryDetailsController.class)

public class BenificiaryDetailsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private BenificiaryService benificiarydetailsservice;
	@MockBean
	private IBenificiaryRepository benificiaryrepository;
	// @InjectMocks
	// BenificiaryDetailsController controller;
	// @Mock
	// BenificiaryDetails benificiarydetails;
	@MockBean
	private IBenificiaryService benificiaryservice;

//********************************ADD BENIFICIARY************************************************//
	@Test
	public void addBenificiaryTest() throws Exception {
		BenificiaryDetails bd = new BenificiaryDetails(999, "Pranya", "9090909090");
		Mockito.when(benificiarydetailsservice.addBenificiary(bd)).thenReturn(bd);// thenThrow(BenificiaryNotFoundException());
		mockMvc.perform(post("/details").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bd))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void addBenificiaryTestN() throws Exception {
		BenificiaryDetails bd = new BenificiaryDetails(0, "sowmya", null);
		when(benificiarydetailsservice.addBenificiary(Mockito.any(BenificiaryDetails.class)))
				.thenThrow(BenificiaryNotFoundException.class);
		mockMvc.perform(post("/details").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bd))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

//**********************************DELETE BENIFICIARY*******************************************//

	@Test
	public void testDeleteBenificiaryNotFound() throws Exception {
		when(benificiarydetailsservice.deleteBenificiary(154)).thenThrow(BenificiaryNotFoundException.class);
		mockMvc.perform(delete("/delete/154").accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isNotFound());
	}

//*********************************VIEW ALL BENIFICIARY*********************************************//+

	@Test
	public void testviewAllBenificiaryP() throws Exception {

		List<BenificiaryDetails> bd = new ArrayList<>();

		Mockito.when(benificiarydetailsservice.viewAllBenificiaryDetails()).thenReturn(bd);

		mockMvc.perform(get("/get").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	// *****************************VIEW BENIFICIARY************************************//

	@Test
	public void testviewBenificiary() throws Exception {
		BenificiaryDetails bd = new BenificiaryDetails(999, "Pranya", "9090909090");
		Mockito.when(benificiarydetailsservice.viewBenificiary(999)).thenReturn(bd);
		mockMvc.perform(get("/get/999").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bd))
				.contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk());

	}

	@Test
	public void testviewBenificiaryN() throws Exception {

		Mockito.when(benificiarydetailsservice.viewBenificiary(999)).thenThrow(BenificiaryNotFoundException.class);
		mockMvc.perform(get("/get/999").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}
	
	
//*************************************UPDATE BENIFICIARY***************************//
	
	
	@Test
	public void testupdateBenificiary() throws Exception {
		BenificiaryDetails bd = new BenificiaryDetails(999, "Pranya", "9090909090");
		Mockito.when(benificiarydetailsservice.updateBenificiary(bd)).thenThrow(BenificiaryNotFoundException.class);
		mockMvc.perform(put("/update").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bd))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testupdateBenificiaryN() throws Exception {
		BenificiaryDetails bd = new BenificiaryDetails(0,null,null);
		Mockito.when(benificiarydetailsservice.updateBenificiary(Mockito.any(BenificiaryDetails.class))).thenThrow(BenificiaryNotFoundException.class);
		mockMvc.perform(put("/update").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

}
