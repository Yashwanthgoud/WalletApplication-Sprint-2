package com.cg.paymentapp.service;

import java.util.List;
import java.util.Optional;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.exception.BillPaymentNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;

public interface IBillPaymentService {

	public BillPayment addBillPayment(BillPayment payment);

	public List<BillPayment> viewBillPayment() throws BillPaymentNotFoundException;

	public BillPayment deleteBillId(int billid) throws InvalidInputException;

	public Optional<BillPayment> viewById(int billId) throws InvalidInputException;
}
