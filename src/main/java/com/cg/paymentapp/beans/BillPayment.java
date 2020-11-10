package com.cg.paymentapp.beans;

import java.time.LocalDate;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Bill")
public class BillPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billId")
	private int billId;

	@ManyToOne

	@JoinColumn(name = "walletId")
	@JsonIgnore
	
	private Wallet wallet;
	@Column(length = 20)
	private BillType billtype;
	@Column(length = 20)
	private double amount;
	@Column
	private LocalDate paymentDate;

	public BillPayment() {

	}

	
	public BillPayment(int billId, Wallet wallet,BillType string, double amount, LocalDate string2) {
		super();
		this.billId = billId;
		this.wallet = wallet;
		this.billtype = string;
		this.amount = amount;
		this.paymentDate = string2;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;

	}

	public BillType getBilltype() {
		return billtype;
	}

	public void setBilltype(BillType billtype) {
		this.billtype = billtype;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "BillPayment [billId=" + billId + ", wallet=" + wallet + ", billtype=" + billtype + ", amount=" + amount
				+ ", paymentDate=" + paymentDate + "]";
	}

}
