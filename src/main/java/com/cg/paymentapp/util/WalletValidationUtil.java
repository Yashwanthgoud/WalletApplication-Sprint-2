package com.cg.paymentapp.util;

import java.math.BigDecimal;

import com.cg.paymentapp.exception.InvalidInputException;

public class WalletValidationUtil {

	public static boolean checkBalance(BigDecimal amount) throws InvalidInputException{
		if(amount.compareTo(new BigDecimal(500))==-1) {
			throw new InvalidInputException("Amount should be greater than 500");
		}else {
			return true;
		}
	}
}
