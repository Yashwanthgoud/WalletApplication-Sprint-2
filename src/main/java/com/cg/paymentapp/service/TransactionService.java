package com.cg.paymentapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.paymentapp.beans.Transaction;
import com.cg.paymentapp.beans.Wallet;
import com.cg.paymentapp.exception.InvalidInputTransactionException;
import com.cg.paymentapp.exception.NoSuchDateFoundException;
import com.cg.paymentapp.repo.ITransactionRepository;
import com.cg.paymentapp.repo.WalletRepo;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionRepository repo;
	
	@Autowired
	private WalletRepo repow;

	@Override
	public Transaction add(Transaction transaction) throws InvalidInputTransactionException {
		
		
            if (validateAdd(transaction) == 1) {
            	
				if(transaction.getWalletId()!= 0) {
				      transaction.setWallet(repow.findById(transaction.getWalletId()).get());
				}
				return repo.save(transaction);

			}
			else if (validateAdd(transaction) == 2) {
				throw new InvalidInputTransactionException("Transaction amount should be greater than 500");
				
			}

			else {
				throw new InvalidInputTransactionException("Transaction type must be withdraw or deposit only");
			}
		}

	// -------------------------------------------validationAdd-----------------------------------------//
	public int validateAdd(Transaction tran) {

		 if ((tran.getTransactionType().equalsIgnoreCase("deposit")
				|| tran.getTransactionType().equalsIgnoreCase("withdraw")) && tran.getAmount()>=500) {

			return 1;
		}

		else if (tran.getAmount() < 500) {
			return 2;
		}

		else {
			return 3;
		}

	}

	// ------------------------------------------------------------------------------------//

	@Override
	public List<Transaction> list() throws InvalidInputTransactionException {
		List<Transaction> tran = repo.findAll();
		return tran;
		
		
	}

	// ------------------------------------------------------------------------------------//

	public List<Transaction> viewTransactionsByDate(LocalDate date) throws NoSuchDateFoundException {

		List<Transaction> transactions = repo.viewByDate(date);
		
		if (transactions.isEmpty()) {
			throw new NoSuchDateFoundException("No transaction found of this Date");
		}

		else {
			return transactions;
		}

	}
	/// ----validateByDate---///

	// ------------------------------------------------------------------------------------//

	public List<Transaction> viewTransactionByType(String transactionType) throws InvalidInputTransactionException {
		     
		     
			 if (transactionType.equalsIgnoreCase("Deposit") || transactionType.equalsIgnoreCase("Withdraw")) {
				 List<Transaction> trans = repo.viewByType(transactionType);
				 return trans;
	
			    }
			     
			 else {
				     throw new InvalidInputTransactionException("The transaction type must be withdraw or deposit only");
			   }

		      }

	

   }


