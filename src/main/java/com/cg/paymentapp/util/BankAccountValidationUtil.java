package com.cg.paymentapp.util;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.exception.InvalidInputException;

public class BankAccountValidationUtil {

	public static boolean checknullorempty(BankAccount bacc) throws InvalidInputException{
		int length=Integer.toString(bacc.getAccountNo()).length();
		if(length<5 || length>5) {
			throw new InvalidInputException("Bank Number is invalid");
		}
	else if (bacc.getBankName() == null || bacc.getBankName().isEmpty()) {
			throw new InvalidInputException("Bank name is null or is empty");
		} else if (bacc.getIfscCode() == null || bacc.getIfscCode().isEmpty()) {
			throw new InvalidInputException("IFSC code is null or is empty");
		} else if(bacc.getBalance()<500){
			throw new InvalidInputException("Balance should be minimum 500");
		}else {
			return true;
		}
	}
}
