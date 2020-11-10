package com.cg.paymentapp.rest;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.paymentapp.beans.BenificiaryDetails;
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.BenificiaryNotFoundException;

import com.cg.paymentapp.repo.IBenificiaryRepository;
import com.cg.paymentapp.service.BenificiaryService;

@RestController
//@RequestMapping("/user")
public class BenificiaryDetailsController {
	@Autowired
	private BenificiaryService benificiarydetailsservice;
	

	

	// creating get mapping to get the list of benificiariary
	@GetMapping("/get") // http://localhost:8080/get
	private List<BenificiaryDetails> viewAllBenificiary() {
		return benificiarydetailsservice.viewAllBenificiaryDetails();
	}

	
	
	// creating post mapping that post the benificiary details in the database
	// http://localhost:8080/details
	@PostMapping("/details")
	public BenificiaryDetails addBenificiary(@RequestBody BenificiaryDetails bd) throws BenificiaryNotFoundException  {
		return benificiarydetailsservice.addBenificiary(bd);
	}
	

	// creating a delete mapping that deletes a specified benificiary
	// http://localhost:8080/delete/id
	@DeleteMapping("/delete/{benificiaryId}")
	private void deleteBenificiary(@PathVariable("benificiaryId") int benificiaryid) throws BenificiaryNotFoundException {
		
		benificiarydetailsservice.deleteBenificiary(benificiaryid);

	}

	
	// creating a get mapping that retrieves the detail of a specific customer
	// http://localhost:8080/detail/id
	@GetMapping("/get/{id}")
	private BenificiaryDetails getviewBenificiary(@PathVariable("id") int benificiaryid) throws BenificiaryNotFoundException {
	return benificiarydetailsservice.viewBenificiary(benificiaryid);
	
	}
	@PutMapping("/update")
	public BenificiaryDetails updateBenificiarydetails(@RequestBody BenificiaryDetails bd) throws BenificiaryNotFoundException
	{
		return benificiarydetailsservice.updateBenificiary(bd);
	}



	
}
