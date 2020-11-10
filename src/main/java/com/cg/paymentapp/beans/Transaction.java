package com.cg.paymentapp.beans;
import java.time.LocalDate;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue
	private int transactionId;
	
	@Column
	private String transactionType;
	
	@Column
	private LocalDate transactionDate;
	
	@Column
	private double amount;
	
	@Column
	private String description;
	
	@Transient
	private int walletId;
	
	
	@ManyToOne
	@JoinColumn(name= "walletid")
	@JsonIgnore
    private Wallet wallet;
	
	public Transaction() {
		
	}
	
	public Transaction(int transactionId, String transactionType, LocalDate transactionDate, double amount,
			String description, Wallet wallet) {
		
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.description = description;
		this.wallet = wallet;
	}

	

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
    }
	
	
	
	

	
	
	
	
	
	
	


