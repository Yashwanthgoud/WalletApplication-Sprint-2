package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.BankAccountNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.repo.IAccountRepository;
import com.cg.paymentapp.repo.WalletRepo;
import com.cg.paymentapp.util.BankAccountValidationUtil;

@Service

public class AccountService implements IAccountService {
	@Autowired
	private IAccountRepository repo;
	@Autowired
	private WalletRepo repo2;

	// ----------> Adding BankAccount details to wallet <----------

	public BankAccount addAccount(BankAccount bacc) {

		BankAccountValidationUtil.checknullorempty(bacc);

		if (repo.existsById(bacc.getAccountNo())) {
			throw new InvalidInputException("BankAccount with account no " + bacc.getAccountNo() + " already exists");
		}

		if (bacc.getWalletid() != 0) {
			Wallet wallet = new Wallet();
			wallet.setWalletId(bacc.getWalletid());
			bacc.setWallet(wallet);
		}

		return repo.save(bacc);
	}

	// ----------> Removing BankAccount with particular Account no <-----------
	public BankAccount removeAccount(int accno) throws InvalidInputException {
		BankAccount bank = new BankAccount();
		if (!repo.existsById(accno)) {
			throw new InvalidInputException("BankAccount with account no " + accno + " doesnot exist");
		} else {
			repo.deleteById(accno);
		}
		return bank;
	}

	// ----------> Viewing BankAccount details of a particular Account no---------->
	public Optional<BankAccount> viewAccount(int accno) throws InvalidInputException {
		Optional<BankAccount> bacc = Optional.of(repo.findById(accno).orElseThrow(
				() -> new InvalidInputException("BankAccount with account no " + accno + " doesnot exist")));
		return bacc;

	}

	// ----------> View all BankAccounts present in the table---------->
	public List<BankAccount> viewAllAccounts() throws BankAccountNotFoundException {
		List<BankAccount> accounts = repo.findAll();
		if (accounts == null || accounts.size() == 0) {
			throw new BankAccountNotFoundException("BankAccount is empty");
		}
		return accounts;
	}

	@Override
	public List<BankAccount> findByWallet(int walletid) {
		return repo.viewAllAccounts(walletid);
		// return null;
	}

}
