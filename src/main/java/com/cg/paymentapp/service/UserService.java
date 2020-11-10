package com.cg.paymentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.repo.IUserRepo;
@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepo repo;
	
	@Override
	public Customer validateLogin(String mobileNumber, String password)throws InvalidInputException 
	{
		Customer c= new Customer();
		c.getMobileNo();
		c.getPassword();
		if(mobileNumber != null && password != null) 
		{
			if (c.getMobileNo().equals(mobileNumber) && c.getPassword().equals(password)) 
			{
				return repo.save(c);
			}
		}
		throw new InvalidInputException("User Not Found") ;

	}
}
