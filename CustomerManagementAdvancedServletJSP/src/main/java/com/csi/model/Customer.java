package com.csi.model;

import java.util.Date;

public class Customer {

	private int custId;

	private String custName;

	private String custAddress;

	private int custAge;

	private long custContactNumber;

	private double custAccountBalance;

	private Date custDOB;

	private String custEmailId;

	private String custPassword;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int custId, String custName, String custAddress, int custAge, long custContactNumber,
			double custAccountBalance, Date custDOB, String custEmailId, String custPassword) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custAddress = custAddress;
		this.custAge = custAge;
		this.custContactNumber = custContactNumber;
		this.custAccountBalance = custAccountBalance;
		this.custDOB = custDOB;
		this.custEmailId = custEmailId;
		this.custPassword = custPassword;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public int getCustAge() {
		return custAge;
	}

	public void setCustAge(int custAge) {
		this.custAge = custAge;
	}

	public long getCustContactNumber() {
		return custContactNumber;
	}

	public void setCustContactNumber(long custContactNumber) {
		this.custContactNumber = custContactNumber;
	}

	public double getCustAccountBalance() {
		return custAccountBalance;
	}

	public void setCustAccountBalance(double custAccountBalance) {
		this.custAccountBalance = custAccountBalance;
	}

	public Date getCustDOB() {
		return custDOB;
	}

	public void setCustDOB(Date custDOB) {
		this.custDOB = custDOB;
	}

	public String getCustEmailId() {
		return custEmailId;
	}

	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custAddress=" + custAddress + ", custAge="
				+ custAge + ", custContactNumber=" + custContactNumber + ", custAccountBalance=" + custAccountBalance
				+ ", custDOB=" + custDOB + ", custEmailId=" + custEmailId + ", custPassword=" + custPassword + "]";
	}

}
