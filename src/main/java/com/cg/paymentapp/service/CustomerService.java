package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InsufficientBalanceException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.repo.ICustomerRepo;
import com.cg.paymentapp.repo.WalletRepo;
import com.cg.paymentapp.util.CustomerValidationUtil;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private ICustomerRepo repo;
	@Autowired
	private WalletRepo walletRepo;
	private Customer customer;
	private Wallet w;

	@Override
	public Customer createAccount(Customer customer) {
		CustomerValidationUtil.checknullorempty(customer.getName(), customer.getMobileNo());
		return repo.save(customer);

	}

	@Override
	public Optional<Customer> showCustomer(String mobileNo) throws InvalidInputException {
		Optional<Customer> optional = Optional.of(repo.findById(mobileNo).orElseThrow(
				() -> new InvalidInputException("Customer with Mobile Number " + mobileNo + " doesnot Exist")));
		return optional;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException {

		w = customer.getWallet();
		w.setBalance(w.getBalance().add(amount));
		customer.setWallet(w);
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException {

		w = customer.getWallet();
		if (w.getBalance().compareTo(amount) >= 500) {
			w.setBalance(w.getBalance().subtract(amount));
			customer.setWallet(w);
			walletRepo.save(customer);
		} else
			throw new InsufficientBalanceException("Insufficient Balance");
		return customer;
	}
}
