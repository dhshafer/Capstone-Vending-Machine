package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	public VendingMachineCLI() {

	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {
		displayWelcomeMessage();
		// ToDo - Add Code here to show menu, etc.
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.readInData();
		vendingMachine.printDisplay();
		Scanner userInput = new Scanner(System.in);

		String[] expectedInputs = new String[] {"1", "2", "3", "4"};
		String message = "\nMain Menu \n1. Display items \n2. Purchase items \n3. Exit\n";
		String value = validateInput(expectedInputs, userInput, message, "Please enter 1, 2, or 3.");
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

	public String validateInput(String[] expected, Scanner input, String message, String invalidMessage){
		while (true) {
			System.out.printf(message + "\n");
			String value = input.nextLine().trim();
			for (String item : expected){
				if (item.equals(value)){
					return value;
				}
			}
			System.out.println(invalidMessage);
		}
	}

}
