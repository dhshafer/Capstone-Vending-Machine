package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private String DATA_INPUT_FILE_PATH = "src/main/resources/VendingMachine.txt";
    private List<Product> Inventory;

    public VendingMachine(){
    }

    public void setDATA_INPUT_FILE_PATH(String DATA_INPUT_FILE_PATH) {
        this.DATA_INPUT_FILE_PATH = DATA_INPUT_FILE_PATH;
    }

    public void readInData(){
        Inventory = new ArrayList<>();
        File inputData = new File(DATA_INPUT_FILE_PATH);
        try(Scanner vendingMachineData = new Scanner(inputData)){
            while(vendingMachineData.hasNextLine()){
                String input = vendingMachineData.nextLine();
                String[] inputSplit = input.split("\\|");
                String keycode = inputSplit[0];
                String name = inputSplit[1];
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(inputSplit[2]));
                String type = inputSplit[3];
                Inventory.add(new Product(type, price, name, keycode));
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public void printDisplay(){
        System.out.println("*************************************************************");
        for(Product item: Inventory){
            String soldOut = item.getStock() == 0 ? "SOLD OUT": "";
            System.out.printf("%s %s $%.2f %s\n", item.getKeyCode(), item.getName(),item.getPrice().doubleValue(), soldOut);
        }
    }

    public List<Product> getInventory() {
        return Inventory;
    }
}
