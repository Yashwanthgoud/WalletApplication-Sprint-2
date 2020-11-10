package com.cg.paymentapp.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="wallet")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="walletid")
	private int walletId;
	private BigDecimal balance;
	/*
	 * @OneToOne(mappedBy = "wallet", cascade = {CascadeType.ALL}, fetch =
	 * FetchType.EAGER) private Customer c;
	 */
	@OneToMany(mappedBy = "wallet", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<BankAccount> bacc=new HashSet<BankAccount>();
	@OneToMany(mappedBy = "wallet", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<BillPayment> payment=new ArrayList<BillPayment>();
	public List<BillPayment> getPayment() {
		return payment;
	}
	
	@OneToMany(mappedBy = "wallet" ,cascade =  CascadeType.ALL , fetch = FetchType.LAZY)
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public void setPayment(List<BillPayment> payment) {
		this.payment = payment;
	}
	
	public Set<BankAccount> getBacc() {
		return bacc;
	}

	public void setBacc(Set<BankAccount> bacc) {
		this.bacc = bacc;
	}

	public Wallet() {
		super();
	}
	
	public Wallet(int walletId, BigDecimal balance, Set<BankAccount> bacc) {
		super();
		this.walletId = walletId;
		this.balance = balance;
		this.bacc = bacc;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public Wallet(BigDecimal amount) {
		this.balance = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return " balance= " + balance;
	}

}
