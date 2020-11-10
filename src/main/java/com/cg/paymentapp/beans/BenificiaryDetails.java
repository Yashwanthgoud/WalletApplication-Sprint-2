package com.cg.paymentapp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//mark class as an Entity   
@Entity
@Table(name="Benificiary")

public class BenificiaryDetails {
	//Defining benificiary id as primary key 
	@Id
	@GeneratedValue()
	private int benificiaryId;
	
	@Column(length=45)
	private String name;
	
	@Column(length=45)
	private String mobileNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
		
		// TODO Auto-generated constructor stub
		public int getBenificiaryId() {
		return benificiaryId;
	}
	public void setBenificiaryId(int benificiaryId) {
		this.benificiaryId = benificiaryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "BenificiaryDetails [benificiaryId=" + benificiaryId + ", name=" + name + ", mobileNumber="
				+ mobileNumber + ", customer=" + customer + "]";
	}
	public BenificiaryDetails(int benificiaryId, String name, String mobileNumber) {
		super();
		this.benificiaryId = benificiaryId;
		this.name = name;
		this.mobileNumber = mobileNumber;
	}
	public BenificiaryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

	

	
	

	
	


