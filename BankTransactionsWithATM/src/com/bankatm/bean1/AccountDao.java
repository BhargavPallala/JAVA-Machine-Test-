package com.bankatm.bean1;

import java.util.ArrayList;
import java.util.Scanner;

import com.bankatm.bean.Account;
import com.bankatm.bean.CustomUtility;

//Data Storage 
public class AccountDao {

	// Storing Account Details in ArrayList
	private static ArrayList<Account> arrAccountList = new ArrayList<Account>();

	// 1. Adding a New Customer
	public static void addCourse() {

		// Account Object Creation
		Account account = new Account();

		// Autogenerated Account Number
		account.setAccountNo(generateAccountNo());

		// Input Prompt for various details
		System.out.print("\n\tEnter Customer Name : ");
		String tempName = CustomUtility.inputString();

		// Check for null Name
		while (tempName.equals("") || tempName.equals("\r")
				|| (tempName.length() > 30)) {
			System.out
					.println("\tError : Name can't be empty. Only 30 Characters allowed");
			System.out.print("\n\tEnter Customer Name : ");
			tempName = CustomUtility.inputString();
		}

		account.setCustomerName(tempName);

		boolean wantToContinue = true;

		// Check for valid input
		do {
			System.out.print("\tAccount Type (Savings/Current) : ");

			String accountType = CustomUtility.inputString().toLowerCase();
			while (!(accountType.equals("savings") || accountType
					.equals("current"))) {
				// Showing error & guiding what to do
				System.out.println("\n\tError : Invalid Input - Please "
						+ "input only 'Savings' or 'Current' to proceed");

				// Prompt Again for correct input
				System.out.print("\tAccount Type (Savings/Current) : ");
				accountType = CustomUtility.inputString().toLowerCase();
			}

			// Check for Savings or Current
			if (accountType.equals("savings")) {
				account.setSaving(true);
			} else if (accountType.equals("current")) {
				account.setSaving(false);
			} else {
				// Might Handle any loopholes if occur
			}
			wantToContinue = false;
		} while (wantToContinue);

		System.out.print("\tEnter Balance : ");
		Double balanceAmount = CustomUtility.inputDouble();

		// Check with Minimum Balance
		while (balanceAmount < Account.getMinBalance()) {
			// Minimum Balance Error
			System.out.println("\tSorry! You Have to Maintain a minimum "
					+ "balance of ---   ' " + Account.getMinBalance()
					+ "'   ---");

			// Prompt Again
			System.out.println("\tPlease put balance above the already "
					+ "set min balance");
			balanceAmount = CustomUtility.inputDouble();
		}
		account.setBalanceAmount(balanceAmount);

		String mobileString;
		do {
			System.out.print("\tEnter Mobile No. : ");
			mobileString = CustomUtility.inputString();

		} while (!validMobile(mobileString));

		account.setMobileNo(mobileString);

		System.out.print("\tEnter Email : ");
		account.setEmailAddress(CustomUtility.inputString());

		// Autogenerated PIN
		account.setAtmPin(generatePin());

		// Setting the account status as Active
		account.setActive(true);

		arrAccountList.add(account);
	}

	// 2. Updating Customer Details
	public static void updateCustomer() {

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();

		Account findAccount = isCustomer(accountNo);

		// Check for account with customer no
		if (findAccount != null) {

			boolean wantToUpdate = false;

			// Display previous values
			CustomUtility.drawDashedLine(149);

			System.out.println("\tMobile No. : " + findAccount.getMobileNo());
			wantToUpdate = CustomUtility.wantToUpdate("Mobile No. ");

			if (wantToUpdate) {
				System.out.print("\tNew Mobile No. : ");
				findAccount.setMobileNo(CustomUtility.inputString());
			}

			System.out.println("\tEmail : " + findAccount.getEmailAddress());
			wantToUpdate = CustomUtility.wantToUpdate("Email Address");

			if (wantToUpdate) {
				System.out.print("\tNew Email  : ");

				findAccount.setEmailAddress(CustomUtility.inputString());
			}

			// Display Success Notes
			CustomUtility.drawDashedLine(149);
			System.out.println("\n\tAccount Details Updated Succesfully");

		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer in the Bank "
					+ "with the Customer ID '" + accountNo + "'");
		}

	}

	// 3. Deleting a Customer
	public static void deleteCustomer() {

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();

		Account findAccount = isCustomer(accountNo);

		// Check for account with customer no
		if (findAccount != null) {

			boolean confirmDelete = false;

			confirmDelete = CustomUtility.confirmDelete();

			if (confirmDelete) {
				// Confirm for delete
				findAccount.setActive(false);
				System.out.println("\tAccount Deleted");
			}

		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer in the Bank"
					+ "with the Customer ID '" + accountNo + "'");
		}

	}

	// 4. Depositing Money into Customer Account
	public static void depositMoney() {

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();
		Account findAccount = isCustomer(accountNo);

		// Check for account with customer no
		if (findAccount != null) {

			System.out.print("\tEnter Amount (To Deposit) : ");
			double amountToDeposit = CustomUtility.inputDouble();

			while (amountToDeposit < 0) {
				System.out.println("\t---   Error : Please input +ve"
						+ " amount   ---");
				System.out.print("\tEnter Amount (To Deposit) : ");
				amountToDeposit = CustomUtility.inputDouble();
			}

			// For above 50K deposit you need to enter PAN Card details
			if (amountToDeposit > 50000.0) {
				System.out.print("\tEnter PAN Card : ");
				String panCard = CustomUtility.inputString();

				findAccount.setPanCard(panCard);
			}

			findAccount.setBalanceAmount(findAccount.getBalanceAmount()
					+ amountToDeposit);

			CustomUtility.drawDashedLine(149);
			System.out.println("\tSuccessfully credited " + amountToDeposit
					+ " in your Account");

		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer in the Bank"
					+ "with the Customer ID '" + accountNo + "'");
		}

	}

	// 5. Withdraw Money from Customer Account
	public static void withdrawMoney() {

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();
		Account findAccount = isCustomer(accountNo);

		// Check for account with customer no
		if (findAccount != null) {

			System.out.print("\tEnter Amount (To Withdraw) : ");
			double amountToWithdraw = Double.parseDouble(CustomUtility
					.inputString());

			while (amountToWithdraw < 0) {

				System.out.println("\n\t---   Error : Please input +ve "
						+ "amount   ---");
				System.out.print("\tEnter Amount (To Withdraw) : ");
				amountToWithdraw = Double.parseDouble(CustomUtility
						.inputString());
			}

			// For above 50K withdraw you need to enter PAN Card details
			if (amountToWithdraw > 50000.0) {
				System.out.print("\tEnter PAN Card : ");
				String panCard = CustomUtility.inputString();

				findAccount.setPanCard(panCard);
			}

			double checkBal = findAccount.getBalanceAmount() - amountToWithdraw;

			if (checkBal > Account.getMinBalance()) {
				findAccount.setBalanceAmount(checkBal);

				CustomUtility.drawDashedLine(149);
				System.out.println("\tSuccessfully debited " + amountToWithdraw
						+ " in your Account");
				CustomUtility.drawDashedLine(149);

			} else {
				CustomUtility.drawDashedLine(149);
				System.out.println("\tSorry! Insufficient Balance in "
						+ "your Account");
			}

		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer in the"
					+ " Bank with the Customer ID '" + accountNo + "'");
		}

	}

	// 6. Show Balance of a Customer
	public static void showBalance() {

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();

		Account findAccount = isCustomer(accountNo);

		if (findAccount != null) {
			System.out.print("\tEnter Your PIN : ");
			int accountPin = Integer.parseInt(CustomUtility.inputString());

			byte attemptAllowed = 3;
			while ((attemptAllowed > 0) && validatePin(findAccount, accountPin)) {
				// Wrong PIN
				System.out.println("\tYou entered a wrong PIN, Try Again. "
						+ "You have " + (attemptAllowed - 1) + ""
						+ " attempt(s) left");
				System.out.print("\tEnter Your PIN : ");
			}

			if (attemptAllowed > 0) {
				// Displaying Balance
				System.out
						.println("Account No : " + findAccount.getAccountNo());
				System.out.println("Ledger  Balance : "
						+ findAccount.getBalanceAmount());
				System.out.println("Available Balance : "
						+ (findAccount.getBalanceAmount() - Account
								.getMinBalance()));
				System.out.println();

				// Get out of this method
				return;
			} else {
				System.out
						.println("\t Sorry you exceeded the limit. Try again later");
			}
		} else {
			// Empty Message
			System.out
					.println("\n\tSorry! There are no customer in the Bank with the Customer ID '"
							+ accountNo + "'");

		}
	}

	// 7. Display the list of all Customers in the Bank
	public static void displayAllCustomer() {
		// Check for empty
		if (arrAccountList.size() > 0) {
			// Displaying the column name for the display table
			System.out
					.println(String
							.format("\n\t|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|",
									"Ac No.", "Name", "Type", "Balance",
									"Mobile", "Email", "PIN", "Status"));

			CustomUtility.drawDashedLine(149);

			// Displaying each record
			for (Account account : arrAccountList) {
				System.out.println(account);
			}
		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer "
					+ "in the Bank.\n\tPlease Add Customers to view "
					+ "their detail(s)\n");
		}

	}

	// 8. Display Customer Details of a specific Customer
	public static void displayCustomer() {

		Scanner input = new Scanner(System.in);
		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No : ");

		String accountNo = CustomUtility.inputString();

		Account findAccount = isCustomer(accountNo);

		if (findAccount != null) {
			// Displaying the column name for the display table
			System.out.println(String.format("\n\t|\t%-10s|\t%-10s|\t%-10s|"
					+ "\t%-10s|\t%-10s|\t%-10s|\t%-10s|\t%-10s|", "Ac No.",
					"Name", "Type", "Balance", "Mobile", "Email", "PIN",
					"Status"));

			CustomUtility.drawDashedLine(149);

			// Displaying Required record
			System.out.println(findAccount);
			System.out.println();

			// Get out of this method
			return;
		} else {
			// Empty Message
			System.out.println("\n\tSorry! There are no customer "
					+ "in the Bank with the Customer ID '" + accountNo + "'");

		}

	}

	// 9. Transferring Money from One Account to Another account
	public static void transferMoney() {

		// from
		Scanner input = new Scanner(System.in);

		// Prompt for the Customer ID
		System.out.print("\tEnter Customer No (From Customer): ");

		String fromAccountNo = CustomUtility.inputString();

		Account fromFindAccount = isCustomer(fromAccountNo);

		if (fromFindAccount != null) {
			// Prompt for the Customer ID
			System.out.print("\tEnter Customer No (To Customer): ");

			String toAccoutNo = CustomUtility.inputString();

			Account toFindAccount = isCustomer(toAccoutNo);

			if (toFindAccount != null) {
				System.out.print("\tEnter Amount (To Transfer) : ");
				double transferAmount = CustomUtility.inputDouble();

				while (transferAmount < 0) {

					System.out.println("\n\t---   Error : Please input +ve "
							+ "amount   ---");
					System.out.print("\tEnter Amount (To Transfer) : ");
					transferAmount = CustomUtility.inputDouble();
				}

				double checkBal = (fromFindAccount.getBalanceAmount() - transferAmount);

				if (checkBal > Account.getMinBalance()) {

					// Debited
					fromFindAccount.setBalanceAmount(checkBal);

					// Credited
					toFindAccount.setBalanceAmount(toFindAccount
							.getBalanceAmount() + transferAmount);

					// Success Note
					CustomUtility.drawDashedLine(149);
					System.out.println("\tSuccessfully transfered "
							+ transferAmount + " to Account No. : "
							+ toAccoutNo);

				} else {
					CustomUtility.drawDashedLine(149);
					System.out
							.println("\tSorry! Insufficient Balance in your Account. Can't process the transaction");

				}

			} else {
				// Empty Message
				System.out
						.println("\n\tSorry! There are no customer in the Bank with the Customer ID '"
								+ fromFindAccount + "'");
			}

		} else {
			// Empty Message
			System.out
					.println("\n\tSorry! There are no customer in the Bank with the Customer ID '"
							+ fromFindAccount + "'");

		}

	}

	// Custom method to Generate Account No.
	private static String generateAccountNo() {

		// 9 Digit Account No. only Digits allowed

		StringBuilder randomAccountNo = new StringBuilder("");

		// Keeping 1st digit from 1-9
		int digit = (int) (Math.random() * 9 + 1);
		randomAccountNo.append(digit);

		// Keeping 2nd digit onwards as 0-9
		for (int i = 0; i < 8; i++) {
			digit = (int) (Math.random() * 9);
			randomAccountNo.append(digit);
		}

		// Validation already existing Account No.
		Account checkAccount = isCustomer(randomAccountNo.toString());

		if (checkAccount != null) {
			// Returning New Random No. as it is matched
			return generateAccountNo();
		}

		// New Non matching Account No. of 9 Digits
		return randomAccountNo.toString();

	}

	// Custom Method to Generate PIN
	private static int generatePin() {

		// Keeping 1st digit from 1-9
		int randomPin = (int) (Math.random() * 9 + 1);

		// Keeping 2nd digit onwards as 0-9
		for (int i = 0; i < 3; i++) {
			randomPin = randomPin * 10 + (int) (Math.random() * 9);
		}

		return randomPin;
	}

	// Method to validate PIN
	private static boolean validatePin(Account account, int accountPin) {
		if (account.getAtmPin() == accountPin) {
			return true;
		}

		return false;
	}

	// Method to check whether a customer exist or not
	private static Account isCustomer(String CustomerId) {

		Scanner input = new Scanner(System.in);
		for (Account account : arrAccountList) {
			if (account.getAccountNo().equals(CustomerId)) {
				// Found with Customer ID
				if (account.isActive()) {
					return account;
				}
				return null;
			}
		}

		// Not Found with Customer ID --> null mean
		return null;

	}

	// Method to validate Mobile String
	private static boolean validMobile(String mobileString) {
		// Check for Mobile length
		if (mobileString.length() != 10) {
			System.out.println("\tInvalid Mobile Number."
					+ " Input only 10 digit Mobile Number.");
			return false;
		} else {
			// Check whether all characters are digit or not
			for (int i = 0; i < 10; i++) {
				char ch = mobileString.charAt(i);
				if (ch < '0' || ch > '9') {
					System.out
							.println("\tInvalid Mobile Number .Only digits allowed.");
					return false;
				}
			}
			return true;
		}
	}
}