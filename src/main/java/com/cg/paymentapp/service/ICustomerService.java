package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InsufficientBalanceException;
import com.cg.paymentapp.exception.InvalidInputException;

public interface ICustomerService {
	public Customer createAccount(Customer customer);

	public Optional<Customer> showCustomer(String mobileno) throws InvalidInputException;

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException;

	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException;
}
