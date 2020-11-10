package com.cg.paymentapp.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.paymentapp.beans.BankAccount;
import com.cg.paymentapp.beans.Customer;
//import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Integer>{
	
	public Customer save(Customer customer);

	@Query("from Customer where mobileNo=:mobileno")
	public Customer findByCustomer(@Param("mobileno") String mobileNo);
}
