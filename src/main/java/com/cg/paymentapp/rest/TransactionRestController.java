package com.cg.paymentapp.rest;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.Transaction;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputTransactionException;
import com.cg.paymentapp.exception.NoSuchDateFoundException;
import com.cg.paymentapp.service.ITransactionService;

@RestController
public class TransactionRestController {
	
	@Autowired
	private ITransactionService service;
	
	
	
	//URL: http://localhost:8880/addt 
	@PostMapping(value = "/addt" , consumes = "application/json")
	public String addTransaction(@RequestBody Transaction transaction) throws InvalidInputTransactionException {
		service.add(transaction);
		return "Transaction add successfully";
	}
	
	
	
	////URL: http://localhost:8880/view
	@GetMapping(value = "/view" , produces = "application/json")
	public List<Transaction> view() throws InvalidInputTransactionException {
		return service.list();
	}
	
	//URL : http://localhost:8880/viewByDate
	@GetMapping(value = "/viewByDate/{transdate}" , produces = "application/json")
	public List<Transaction> ByDate(@PathVariable("transdate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date)throws NoSuchDateFoundException{
		return service.viewTransactionsByDate(date);
	}
	
	
	//URL : http://localhost:8880/viewByTransactionType
		@GetMapping(value = "/viewByTransactionType/{transtype}" , produces = "application/json")
		public List<Transaction> ByTransactionType(@PathVariable("transtype") String type) throws InvalidInputTransactionException{
			return service.viewTransactionByType(type);
		}
	

}
