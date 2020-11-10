package com.cg.paymentapp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	private String name;
	@Id
	private String mobileNo;
	private String password;
	
	@OneToOne
	@JoinColumn(name="walletid")
	private Wallet wallet;
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer(String name, String mobileNo) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
	}
	public Customer() {
		super();
	}
	public Customer(String name, String mobileNo, String password) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", password=" + password + "]";
	}
	

}
