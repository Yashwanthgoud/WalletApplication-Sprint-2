package com.cg.paymentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.paymentapp.beans.BillPayment;

public interface IBillPaymentRepository extends JpaRepository<BillPayment, Integer> {

}
