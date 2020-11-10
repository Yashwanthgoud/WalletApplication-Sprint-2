package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputException;

public interface WalletService {
	public Optional<Wallet> findWallet(int walletid) throws InvalidInputException;

	public Wallet addWallet(Wallet wallet);

	public void removeWallet(int walletid) throws InvalidInputException;

	public List<Wallet> findAllWallet() throws InvalidInputException;

}
