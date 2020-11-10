package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.BankAccountNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;

public interface IAccountService {

	public BankAccount addAccount(BankAccount bacc);

	public BankAccount removeAccount(int accno) throws InvalidInputException;

	public Optional<BankAccount> viewAccount(int accno) throws InvalidInputException;

	public List<BankAccount> viewAllAccounts() throws BankAccountNotFoundException;

	public List<BankAccount> findByWallet(int walletid);


}
