package com.cg.paymentapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.exception.BillPaymentNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;

import com.cg.paymentapp.repo.IBillPaymentRepository;
import com.cg.paymentapp.util.BillPaymentValidationUtil;

@Service
public class BillPaymentService implements IBillPaymentService {
	@Autowired
	private IBillPaymentRepository repo;

	public BillPayment addBillPayment(BillPayment payment) {
		BillPaymentValidationUtil.checkValidation(payment);
		return repo.save(payment);
	}

	public List<BillPayment> viewBillPayment() throws BillPaymentNotFoundException {
		List<BillPayment> billPayments = repo.findAll();
		if (billPayments == null || billPayments.size() == 0) {
			throw new BillPaymentNotFoundException("BillPayment Entered is Incorrect");
		}
		return billPayments;
	}

	@Override
	public BillPayment deleteBillId(int billid) throws InvalidInputException {
		BillPayment billPayment = repo.findById(billid)
				.orElseThrow(() -> new InvalidInputException("BillPayment with ID " + billid + " doesnot Exist"));
		repo.deleteById(billPayment.getBillId());
		return billPayment;
	}

	@Override
	public Optional<BillPayment> viewById(int billId) throws InvalidInputException {
		Optional<BillPayment> billPayment = Optional.of(repo.findById(billId)
				.orElseThrow(() -> new InvalidInputException("BillPayment with ID " + billId + " doesnot Exist")));
		return billPayment;
	}

}
