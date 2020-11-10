package com.cg.paymentapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.cg.paymentapp.beans.BenificiaryDetails;
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.BenificiaryNotFoundException;

import com.cg.paymentapp.repo.IBenificiaryRepository;

@Service

public class BenificiaryService {

	@Autowired
	IBenificiaryRepository benificiaryrepository;

	// getting all benificiary by using the method findaAll() of CrudRepository
	public List<BenificiaryDetails> viewAllBenificiaryDetails() {
		// List<BenificiaryDetails> bd = new ArrayList<BenificiaryDetails>();
		// benificiaryrepository.findAll().forEach(bd1 -> bd.add(bd1));

		return benificiaryrepository.findAll();
	}

	// saving a specific record by using the method save() of CrudRepository
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException {
		if (bd.getBenificiaryId() != 0) {
			return benificiaryrepository.save(bd);
		} else {
			throw new BenificiaryNotFoundException();
		}

	}

	// deleting a specific record by using the method deleteById() of CrudRepository
	public BenificiaryDetails deleteBenificiary(int id) throws BenificiaryNotFoundException {
		BenificiaryDetails benificiaryDetails = benificiaryrepository.findById(id)
				.orElseThrow(() -> new BenificiaryNotFoundException("benificiary Id doesn't exist: " + id));

		benificiaryrepository.deleteById(id);
		// return "deleted";
		return benificiaryDetails;
	}

	// updating a record
	public BenificiaryDetails updateBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException {

		// return benificiaryDetails;
		if (benificiaryrepository.findById(bd.getBenificiaryId()) != null) {
			return benificiaryrepository.save(bd);
		}

		else {
			return null;
		}

	}

	// get specific details of benificiary
	public BenificiaryDetails viewBenificiary(int benificiaryid) throws BenificiaryNotFoundException

	{
		BenificiaryDetails benificiaryDetails = benificiaryrepository.findById(benificiaryid)
				.orElseThrow(() -> new BenificiaryNotFoundException("Benificiary Id doesn't exist: " + benificiaryid));
		// BenificiaryDetails benificiaryDetails =
		// benificiaryrepository.findById(id).orElseThrow(exceptionSupplier)(() -> new
		// BenificiaryNotFoundException("benificiary Id doesnt exist: " + id));
		return benificiaryrepository.findById(benificiaryid).get();

	}

}
