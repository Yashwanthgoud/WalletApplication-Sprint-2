package com.cg.paymentapp.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.paymentapp.beans.Customer;

public interface IUserRepo extends JpaRepository<Customer, String>{	

}
