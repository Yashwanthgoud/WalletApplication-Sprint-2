package com.cg.paymentapp.service;

import com.cg.paymentapp.beans.BenificiaryDetails;
import com.cg.paymentapp.beans.Customer;
import com.cg.paymentapp.exception.BenificiaryNotFoundException;

public interface IBenificiaryService {
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException;

	public BenificiaryDetails updateBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException;

	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException;

	public BenificiaryDetails viewBenificiary(BenificiaryDetails bd) throws BenificiaryNotFoundException;

	public BenificiaryDetails viewAllBenificiary();

}
