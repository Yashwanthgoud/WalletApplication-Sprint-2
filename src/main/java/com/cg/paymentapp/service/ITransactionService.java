package com.cg.paymentapp.service;

import java.time.LocalDate;

import java.util.List;

import com.cg.paymentapp.beans.Transaction;
import com.cg.paymentapp.exception.InvalidInputTransactionException;
import com.cg.paymentapp.exception.NoSuchDateFoundException;

public interface ITransactionService {

	Transaction add(Transaction transaction) throws InvalidInputTransactionException;

	List<Transaction> list() throws InvalidInputTransactionException;

	List<Transaction> viewTransactionsByDate(LocalDate date) throws NoSuchDateFoundException;

	List<Transaction> viewTransactionByType(String type) throws InvalidInputTransactionException;
}
