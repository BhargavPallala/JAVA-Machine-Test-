package com.bankatm.bean;

import java.util.Scanner;

//Class for Custom Utility
public class CustomUtility {

	// Method to display welcome note
	public static void displayHeader(String headNote) {
		drawDashedLine(149);
		System.out.println("\t------   " + headNote + "   ------");
		drawDashedLine(149);
	}

	// Method which draws a horizontal line on console
	public static void drawDashedLine(int howMuch) {

		// Draws Dashed Line
		for (int i = 0; i < howMuch; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	// Method for asking User for choice to continue
	public static boolean wantToContinue() {

		// Input Scanner Object
		Scanner input = new Scanner(System.in);
		System.out.println("\n");
		drawDashedLine(149);

		// Prompt for choice
		System.out.print("\tWould you like to continue? (y/n)\n\t");

		try {

			// Choice Variable
			char choice = input.nextLine().charAt(0);
			while ((choice != 'y') && (choice != 'Y') && (choice != 'n')
					&& (choice != 'N')) {

				// Invalid Choice Handle
				drawDashedLine(149);
				System.out.print("\tError : INVALID CHOICE\n\tPlease "
						+ "input only 'y' or 'n'\n\t");
				choice = input.nextLine().charAt(0);
			}

			if (choice == 'y' || choice == 'Y') {
				drawDashedLine(149);
				return true;
			} else {

				return false;
			}
		} catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
			return false;
		} catch (Exception e) {
			return false;
		}

	}
	
	// Method for asking User for choice to Update
		public static boolean wantToUpdate(String what) {

			// Input Scanner Object
			Scanner input = new Scanner(System.in);
			drawDashedLine(149);

			// Prompt for choice
			System.out.print("\tWant to update "+what+"? (y/n)\n\t");

			try {

				// Choice Variable
				char choice = input.nextLine().charAt(0);
				while ((choice != 'y') && (choice != 'Y') && (choice != 'n')
						&& (choice != 'N')) {

					// Invalid Choice Handle
					drawDashedLine(149);
					System.out.print("\tError : INVALID CHOICE\n\tPlease "
							+ "input only 'y' or 'n'\n\t");
					choice = input.nextLine().charAt(0);
				}

				if (choice == 'y' || choice == 'Y') {
					drawDashedLine(149);
					return true;
				} else {

					return false;
				}
			} catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
				return false;
			} catch (Exception e) {
				return false;
		}

	}
		
	//Method to confirm delete
		public static boolean confirmDelete()
		{
			// Input Scanner Object
						Scanner input = new Scanner(System.in);
						drawDashedLine(149);

						// Prompt for choice
						System.out.print("\tConfirm delete? (y/n)\n\t");

						try {

							// Choice Variable
							char choice = input.nextLine().charAt(0);
							while ((choice != 'y') && (choice != 'Y') && (choice != 'n')
									&& (choice != 'N')) {

								// Invalid Choice Handle
								drawDashedLine(149);
								System.out.print("\tError : INVALID CHOICE\n\tPlease "
										+ "input only 'y' or 'n'\n\t");
								choice = input.nextLine().charAt(0);
							}

							if (choice == 'y' || choice == 'Y') {
								drawDashedLine(149);
								return true;
							} else {

								return false;
							}
						} catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
							return false;
						} catch (Exception e) {
							return false;
					}
		}

	// Method for displaying exit note
	public static void exitNote() {

		drawDashedLine(149);
		System.out.println("\n\t------   Thank You very much for using "
				+ "the App   ------\n");
		drawDashedLine(149);
	}

	// Method take valid double value as input
	public static double inputDouble() {
		Scanner input = new Scanner(System.in);
		Double doubleValue = 0.00;
		boolean continueInput = true;
		do {
			try {
				doubleValue = Double.parseDouble(input.nextLine());

				// Success input
				break;
			} catch (Exception e) {
				continueInput = true;
				// CustomUtility.drawDashedLine(149);
				System.out
						.print("\tInvalid input please provide Numeric Value (Float accepted)\n\t");
			}
		} while (continueInput);

		return doubleValue;
	}

	// Method take valid int value as input
	public static int inputInt() {
		Scanner input = new Scanner(System.in);
		int intInput = 0;
		boolean continueInput = true;
		do {
			try {
				intInput = Integer.parseInt(input.nextLine());

				// Success input
				break;
			} catch (Exception e) {
				continueInput = true;
				// CustomUtility.drawDashedLine(149);
				System.out
						.print("\tInvalid input please provide Numeric Value (Integer accepted)\n\t");
			}
		} while (continueInput);

		return intInput;
	}

	// Method take valid String value as input
	public static String inputString() {
		Scanner input = new Scanner(System.in);
		String stringInput = null;
		boolean continueInput = true;
		do {
			try {
				stringInput = input.nextLine();

				// Success input
				break;
			} catch (Exception e) {
				continueInput = true;
				System.out.print("\tInvalid input please provide a value\n\t");

			}
		} while (continueInput);

		return stringInput;
	}

}
