package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
	}

	public void displayWelcomeMessage(){
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}



}
