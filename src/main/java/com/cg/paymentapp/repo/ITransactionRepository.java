package com.cg.paymentapp.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.paymentapp.beans.Transaction;


public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
	
	@Query("FROM Transaction  WHERE transactionDate=:transdate")
	List<Transaction> viewByDate(@Param("transdate") LocalDate transactiondate);
	
	@Query("FROM Transaction  WHERE transactionType=:transtype")
	List<Transaction> viewByType(@Param("transtype") String transactiontype);

}
