package com.cg.paymentapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.service.IUserService;

@RestController
public class UserRestController {

	@Autowired
	private IUserService service;
	
	@PostMapping(value = "/validateLogin/{validateLogin}", consumes = "application/json")
	public Customer validateLogin(@PathVariable("validateLogin") String mobileNumber,String password) throws InvalidInputException{
		return service.validateLogin(mobileNumber, password);
	}
}
