package com.cg.paymentapp.util;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.beans.BillType;
import com.cg.paymentapp.exception.InvalidInputException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class BillPaymentValidationUtil {

	public static boolean checkValidation(BillPayment payment) throws InvalidInputException {

		if (payment.getAmount() == 0)
			throw new InvalidInputException("Amount cannot be 0");
		else
			return true;
	}
}
