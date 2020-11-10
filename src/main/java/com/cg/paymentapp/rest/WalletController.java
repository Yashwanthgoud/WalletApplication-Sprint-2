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
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.service.WalletService;

@RestController
public class WalletController {
	@Autowired
	private WalletService service;
	
//ADD WALLET
	@PostMapping(value = "/addWallet")
	public String addWallet(@RequestBody Wallet wallet) {
		service.addWallet(wallet);
		return "Wallet added Successfully";
	}
//LIST WALLET BY WALLET ID
	@GetMapping(value = "/listWalletById")
	public Optional<Wallet> viewWallet(@RequestParam("walletId") int walletId) throws InvalidInputException{
		return service.findWallet(walletId);
	}
//DELETE WALLET BY WALLET ID
	@DeleteMapping(value = "/removeWallet/{walletid}")
	public String removeWallet(@PathVariable("walletid") int walletid) throws InvalidInputException{
		service.removeWallet(walletid);
		return "Removed Successfully";
	}
//LIST ALL WALLETS
	@GetMapping(value = "/listWallet")
	public List<Wallet> viewAllWallet() throws InvalidInputException{
		return service.findAllWallet();
	}
}
