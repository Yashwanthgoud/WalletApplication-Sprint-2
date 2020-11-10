package com.cg.paymentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.beans.Wallet;

public interface ICustomerRepo extends JpaRepository<Customer, String> {
}
