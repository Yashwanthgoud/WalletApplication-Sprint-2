package com.cg.paymentapp.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.BillPayment;
import com.cg.paymentapp.exception.BillPaymentNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.service.IBillPaymentService;

@RestController
public class BillPaymentController {

	@Autowired
	private IBillPaymentService billpaymentservice;
	
//ADD BILL PAYMENT	
	@PostMapping(value="/addBillPayment",consumes="application/json")
	public String addBill(@RequestBody BillPayment payment) {
		billpaymentservice.addBillPayment(payment);
		return "Bill Payment added Successfully";
	}
//VIEW ALL PAYMENTS
	@GetMapping(value="/viewAllPayments",produces="application/json")
		public List<BillPayment> viewAllBillPayments() throws BillPaymentNotFoundException
		{
			return billpaymentservice.viewBillPayment();
		}
//VIEW BILL PAYMENT BY BILL ID
	@GetMapping(value="/viewByBillId")
	public Optional<BillPayment> payment(@RequestParam("billId") int billId) throws InvalidInputException {
		return billpaymentservice.viewById(billId);
	}
//DELETE BILL PAYMENT BY ID
	@DeleteMapping(value="/deleteByBillId")
	public void deleteById(@RequestParam("billId") int billId) throws InvalidInputException {
		billpaymentservice.deleteBillId(billId);
	}
	}
