package com.cg.paymentapp.service;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InvalidInputException;

public interface IUserService {

	public Customer validateLogin(String mobileNumber,String password) throws InvalidInputException;
}
