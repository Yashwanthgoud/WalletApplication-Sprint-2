package com.cg.paymentapp.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.BankAccountNotFoundException;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.service.IAccountService;

@RestController
public class BankAccountController {
	@Autowired
	private IAccountService accountService;

	// ----------> Adding BankAccount to wallet <----------
	// URL-http://localhost:8585/add
	@PostMapping(value = "/add")
	public String addAccount(@RequestBody BankAccount bank) {
		accountService.addAccount(bank);
		return "added successfully";
	}

	// ----------> Viewing BankAccount details of a particular Account no<----------
	// URL-http://localhost:8585/list/id
	@GetMapping(value = "/list/{accountNo}")
	public Optional<BankAccount> viewAccount(@PathVariable("accountNo") int accountNo) throws InvalidInputException {
		return accountService.viewAccount(accountNo);
	}

	// ----------> Viewing all BankAccounts present in the table <----------
	// URL-http://localhost:8585/list
	@GetMapping(value = "/listall")
	public List<BankAccount> viewAllAccount() throws BankAccountNotFoundException {
		return accountService.viewAllAccounts();
	}

	// ----------> Removing a BankAccount based on account no <----------
	// URL-http://localhost:8585/remove/accno
	@DeleteMapping(value = "/remove/{accountNo}")
	public BankAccount removeAccount(@PathVariable int accountNo) throws InvalidInputException {
		return accountService.removeAccount(accountNo);
		
	}

	@GetMapping(value = "/wallet/{walletid}", produces = "application/json")
	public List<BankAccount> byWallet(@PathVariable("walletid") int walletid) {
		return accountService.findByWallet(walletid);
	}

}
