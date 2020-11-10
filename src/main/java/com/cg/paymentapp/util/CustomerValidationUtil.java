package com.cg.paymentapp.util;

import com.cg.paymentapp.exception.InvalidInputException;

public class CustomerValidationUtil {

	
	public static boolean checknullorempty(String name, String mobileno) throws InvalidInputException {
		if(name==null||name.isEmpty()) {
			throw new InvalidInputException("Name is empty or is null");
		}else if(mobileno.length()<10||mobileno.length()>10) {
			throw new InvalidInputException("Mobile number is invalid");
		}else{
			return true;
		}
}
}
