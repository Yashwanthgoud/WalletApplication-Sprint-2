package com.cg.paymentapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.paymentapp.beans.BenificiaryDetails;

public interface IBenificiaryRepository extends JpaRepository<BenificiaryDetails, Integer> {


}
