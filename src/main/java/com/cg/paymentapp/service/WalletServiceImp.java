package com.cg.paymentapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputException;
import com.cg.paymentapp.repo.WalletRepo;
import com.cg.paymentapp.util.WalletValidationUtil;

@Service
public class WalletServiceImp implements WalletService {
	@Autowired
	private WalletRepo repo;
	//private Customer customer;
	private Wallet wallet;
	
	public Wallet addWallet(Wallet wallet) {
		WalletValidationUtil.checkBalance(wallet.getBalance());
		return repo.save(wallet);
	}

	public Optional<Wallet> findWallet(int walletid) throws InvalidInputException{
		Optional<Wallet> optional=Optional.of(repo.findById(walletid).orElseThrow(() -> new InvalidInputException("Wallet with wallet ID "+walletid+" doesnot exist")));
		return optional;
	}

	public void removeWallet(int walletid) throws InvalidInputException{
		Wallet wallet=repo.findById(walletid).orElseThrow(() -> new InvalidInputException("Wallet with wallet ID "+walletid+" doesnot exist"));
		repo.deleteById(wallet.getWalletId());
	}

	@Override
	public List<Wallet> findAllWallet() throws InvalidInputException{
	   List<Wallet> list= repo.findAll();
	   if(list==null || list.size()==0) {
		   throw new InvalidInputException("No Existing Wallet");
	   }
	   return list;
	}
	
	

}
