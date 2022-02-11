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

	private AuditLog log = new AuditLog();
	private SalesReport salesReport = new SalesReport();
	private VendingMachine vendingMachine = new VendingMachine();

	public void setTestPaths(){
		log.setAUDIT_LOG_FILE_PATH("src/test/resources/LogTest.txt");
		log.resetLog();
		salesReport.setSALES_REPORT_FILE_PATH("src/test/resources/SalesReportTest.txt");
		vendingMachine.setDATA_INPUT_FILE_PATH("src/test/resources/VendingMachineTest.txt");
	}

	public void run() {
		displayWelcomeMessage();
		// ToDo - Add Code here to show menu, etc.
		log.writeToFile("Test");
		vendingMachine.readInData();
		salesReport.readInData(vendingMachine.getInventory());
		//vendingMachine.printDisplay();
		//salesReport.printReport();
		Scanner userInput = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			String[] expectedInputs = new String[]{"1", "2", "3", "4"};
			String message = "\nMain Menu \n1. Display items \n2. Purchase items \n3. Exit\n";
			String value = validateInput(expectedInputs, userInput, message, "Please enter 1, 2, or 3.");
			switch (value) {
				case ("1"):
					vendingMachine.printDisplay();
					break;
				case ("2"):
					purchaseItemMenu(userInput, vendingMachine);
					break;
				case ("3"):
					System.out.println("Thanks for shopping");
					exit = true;
					break;
				case ("4"):
					salesReport.printReport();
					break;
			}
		}
	}
	
	private void purchaseItemMenu(Scanner userInput, VendingMachine vendingMachine) {
		Customer customer = new Customer();
		boolean exit = false;
		while(!exit) {
			String logValue;
			String[] expectedInputs = new String[]{"1", "2", "3"};
			String message = "\nPurchase items \n1. Feed Money \n2. Select Product \n3. Finish Transaction\n";
			String value = validateInput(expectedInputs, userInput, message, "Please enter 1, 2, or 3.");
			switch (value) {
				case ("1"):
					logValue = "";
					System.out.printf("Current balance is: $%.2f\n", customer.getTotalMoney().doubleValue());
					while (true) {
						System.out.print("Please enter whole dollar amount: ");
						String inputAmount = userInput.nextLine();
						try {
							int dollarAmount = Integer.parseInt(inputAmount);
							customer.feedMoney(BigDecimal.valueOf(dollarAmount));
							System.out.printf("Current balance is: $%.2f\n", customer.getTotalMoney().doubleValue());
							logValue += "FEED MONEY: $"+BigDecimal.valueOf(dollarAmount) + " $" + customer.getTotalMoney();
							log.writeToFile(logValue);
							break;
						} catch (NumberFormatException e) {
							System.out.println("Invalid entry.");
						}
					}
					break;
				case ("2"):
					vendingMachine.printDisplay();
					System.out.printf("\nCurrent balance is: $%.2f\n", customer.getTotalMoney().doubleValue());
					String[] expectedInput = new String[vendingMachine.getInventory().size()];
					for (int i = 0; i < vendingMachine.getInventory().size(); i++){
						expectedInput[i] = vendingMachine.getInventory().get(i).getKeyCode();
					}
					String keyCodeValue = validateInput(expectedInput, userInput, "Please enter a valid keycode: ", "Invalid keycode.");
					System.out.println(purchaseItemMenu(vendingMachine, keyCodeValue, customer));
					System.out.printf("\nCurrent balance is: $%.2f\n", customer.getTotalMoney().doubleValue());
					break;
				case ("3"):
					System.out.println("Thank you for shopping your change is:");
					log.writeToFile("GIVE CHANGE: $"+customer.getTotalMoney() + " $0.00");
					System.out.println(customer.returnChange());
					exit = true;
					break;
			}
		}
	}

	private String purchaseItemMenu(VendingMachine vendingMachine, String keyCodeValue, Customer customer) {
		BigDecimal balance = customer.getTotalMoney();
		if (balance.equals(BigDecimal.ZERO)){
			return "Must deposit money first";
		}
		for (Product item: vendingMachine.getInventory()){
			if (item.getKeyCode().equalsIgnoreCase(keyCodeValue)){
				if (item.getStock() == 0) {
					return "Product is sold out";
				} else if (item.getPrice().compareTo(balance) > 0){
					return "Product cost more than balance";
				} else {
					customer.spendMoney(item.getPrice());
					log.writeToFile(item.getName()+" "+item.getKeyCode()+" $"+item.getPrice()+" $"+customer.getTotalMoney());
					item.setStock(item.getStock()-1);
					salesReport.addSale(item.getName(), item.getPrice());
					System.out.println(item.getSound());
					return "Vending " + item.getName() + "...";
				}
			}
		}
		return "";
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
