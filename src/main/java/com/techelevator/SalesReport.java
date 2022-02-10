package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
    private BigDecimal totalGrossSales = BigDecimal.ZERO;
    private Map<String, Integer> itemSaleCount = new HashMap<>();
    private final String SALES_REPORT_FILE_PATH = "src/main/resources/SalesReport.txt";

    public SalesReport(List<Product> productsList) {
        for(Product item: productsList){
            itemSaleCount.put(item.getName(), 0);
        }
    }

    public void addSale(String name, BigDecimal price){
        totalGrossSales = totalGrossSales.add(price);
        itemSaleCount.put(name, itemSaleCount.get(name)+1);
    }

    public void printReport(){
        File filePath = new File(SALES_REPORT_FILE_PATH);
        try(PrintWriter salesReport = new PrintWriter(filePath)){
            for(Map.Entry<String, Integer> item: itemSaleCount.entrySet()){
                salesReport.println(item.getKey() + "|" + item.getValue());
            }
            salesReport.println();
            salesReport.printf("**TOTAL SALES** $%.2f", totalGrossSales.doubleValue());
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
