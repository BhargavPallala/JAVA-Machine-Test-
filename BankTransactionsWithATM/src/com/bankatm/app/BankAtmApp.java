package com.bankatm.app;

import java.util.Scanner;

import com.bankatm.bean.CustomUtility;
import com.bankatm.bean1.AccountDao;

//Main App
public class BankAtmApp {

	// Entry Point for the Bank ATM App
	public static void main(String[] args) {

		// Welcome Message
		CustomUtility.displayHeader("Welcome to Bank Transaction App");

		// Redirected to Main Menu
		mainMenu();
	}

	public static void mainMenu() {

		// Choice variable
		boolean wantToContinue = true;

		// Menu Driven Loop
		do {

			// Prompt for choice from options
			System.out.println("\n\t1. Add New Customer"
					+ "\n\t2. Update Customer Detail(s)"
					+ "\n\t3. Delete Customer" + "\n\t4. Deposit Money"
					+ "\n\t5. Withdraw Money" + "\n\t6. Show Balance"
					+ "\n\t7. Display All Customer"
					+ "\n\t8. Display Customer (By Customer ID)"
					+ "\n\t9. Transfer Money" + "\n\t0. Exit");

			System.out.print("\n\tEnter your Choice : ");

			char choice = CustomUtility.inputString().charAt(0);
			System.out.println();
			switch (choice) {

			case '1':

				// Add New Customer
				CustomUtility.displayHeader("Add New Customer");
				AccountDao.addCourse();

				break;

			case '2':

				// Update Customer Detail(s)
				CustomUtility.displayHeader("Update Customer Details");
				AccountDao.updateCustomer();

				break;

			case '3':

				// Delete Customer
				CustomUtility.displayHeader("Delete Customer");
				AccountDao.deleteCustomer();

				break;

			case '4':

				// Deposit Money
				CustomUtility.displayHeader("Deposit Money");
				AccountDao.depositMoney();

				break;

			case '5':

				// Withdraw Money
				CustomUtility.displayHeader("Withdraw Money");
				AccountDao.withdrawMoney();

				break;

			case '6':

				// Show Balance
				CustomUtility.displayHeader("Show Balance");
				AccountDao.showBalance();

				break;

			case '7':

				// Show All Customer Details
				CustomUtility.displayHeader("All Customer Details");
				AccountDao.displayAllCustomer();

				break;

			case '8':

				// Display Customer Details By Customer ID
				CustomUtility.displayHeader("Display Customer Details "
						+ "(By Cutomer ID)");
				AccountDao.displayCustomer();

				break;

			case '9':

				// Transfer Money
				CustomUtility.displayHeader("Transfer Money");
				AccountDao.transferMoney();

				break;

			case '0':

				// Exit
				CustomUtility.exitNote();
				System.exit(0);

				break;

			default:

				// Error Handling
				CustomUtility.drawDashedLine(149);
				System.out.println("\tError : INVALID CHOICE "
						+ "\n\tChoose from the option only");
			}

			// Again asking for choice
			wantToContinue = CustomUtility.wantToContinue();

		} while (wantToContinue);

		// Displaying Exit Note
		CustomUtility.exitNote();
	}

}
