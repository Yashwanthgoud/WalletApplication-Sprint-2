package com.cg.paymentapp.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.beans.Wallet;

public interface IAccountRepository extends JpaRepository<BankAccount, Integer> {

	@Query("from BankAccount where walletid=:wall")
	List<BankAccount> viewAllAccounts(@Param("wall") int walletid);

}
