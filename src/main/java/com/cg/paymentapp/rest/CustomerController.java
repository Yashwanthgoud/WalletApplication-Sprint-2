package com.cg.paymentapp.rest;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InsufficientBalanceException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.service.ICustomerService;

@RestController
public class CustomerController {
	@Autowired
	private ICustomerService service;
	
//ADD CUSTOMER
	@PostMapping(value = "/addCustomer")
	public String addCustomer(@RequestBody Customer customer) {
		service.createAccount(customer);
		return "Customer added Successfully";
	}
//VIEW CUSTOMER BY MOBILE NUMBER
	@GetMapping(value = "/viewCustomerByMobileNo/{mobileNo}")
	public Optional<Customer> viewCustomerByMobileNo(@PathVariable("mobileNo") String mobileNo)
			throws InvalidInputException {
		return service.showCustomer(mobileNo);
	}

	// URL: http://localhost:4321/depositAmount
	@PostMapping(value = "/depositAmount/{mobileNo}/{amount}", consumes = "applicationd/json")
	public Customer depositAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) throws InvalidInputException 
	{
		return service.depositAmount(mobileNo, amount);
	}
	
	// URL: http://localhost:4321/withdrawAmount
	@PostMapping(value = "/withdrawAmount/{mobileNo}/{amount}", consumes = "application/json")
	public Customer withdrawAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		return service.withdrawAmount(mobileNo, amount);

	}
}
