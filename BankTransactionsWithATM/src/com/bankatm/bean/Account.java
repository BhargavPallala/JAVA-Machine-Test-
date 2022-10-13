package com.bankatm.bean;

public class Account {

	// Instance Variables
	private String accountNo;
	private String customerName;
	private boolean isSaving;
	private double balanceAmount;
	private String mobileNo;
	private String emailAddress;
	private int atmPin;
	private boolean isActive;
	private String panCard;

	// Static Variable(s)
	private static double minBalance = 1000.0; // Hard Coded values

	// Default Constructor
	public Account() {
		super();
	}

	

	// Parameterized Constructor
	public Account(String accountNo, String customerName, boolean isSaving,
			double balanceAmount, String mobileNo, String emailAddress,
			int atmPin, boolean isActive) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.isSaving = isSaving;
		this.balanceAmount = balanceAmount;
		this.mobileNo = mobileNo;
		this.emailAddress = emailAddress;
		this.atmPin = atmPin;
		this.isActive = isActive;
	}

	// Getters & Setters
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean isSaving() {
		return isSaving;
	}
	
	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public void setSaving(boolean isSaving) {
		this.isSaving = isSaving;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(int atmPin) {
		this.atmPin = atmPin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static double getMinBalance() {
		return minBalance;
	}

	public static void setMinBalance(double minBalance) {
		Account.minBalance = minBalance;
	}

	// Overridden toString()
	@Override
	public String toString() {

		// Check for Savings or Current
		String isSavingToString = (isSaving) ? "Savings" : "Current";

		// Check for Active or Deleted
		String isActiveToString = (isActive) ? "Active" : "Inactive";

		String retString = String
				.format("\n\t|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|",
						accountNo, customerName, isSavingToString,
						balanceAmount, mobileNo, emailAddress, "****",
						isActiveToString);
		return retString;
	}

}
