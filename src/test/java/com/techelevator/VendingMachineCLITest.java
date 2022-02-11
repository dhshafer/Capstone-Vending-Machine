package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.NoSuchElementException;

public class VendingMachineCLITest {
    VendingMachineCLI vendingMachineCLI;
    PrintStream old;
    PrintStream ps;
    ByteArrayOutputStream baos;
    @Before
    public void setUp(){
        vendingMachineCLI = new VendingMachineCLI();
        vendingMachineCLI.setTestPaths();
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);
    }

    @Test
    public void test(){
        System.out.println("Test");
        String userInput = "A\n1\n2\n1\nA\n10\n2\nAA\nD1\n2\nC1\n2\nB1\n2\nA1\nA\n3\n4\n3";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        vendingMachineCLI.run();
    }
    @Test
    public void testNoStock(){
        String userInput = "2\n1\n10\n2\nA2\n2\nA2\n2\nA2\n2\nA2\n2\nA2\n2\nA2";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        try{
            vendingMachineCLI.run();
        } catch (NoSuchElementException e){
            Assert.assertTrue(baos.toString().contains("Product is sold out"));
        }
    }

    @Test
    public void testMustDepositMoneyFirst(){
        String userInput = "2\n2\nA1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        try{
            vendingMachineCLI.run();
        } catch (NoSuchElementException e){
            Assert.assertTrue(baos.toString().contains("Must deposit money first"));
        }
    }

    @Test
    public void testProductCostsMoreThanBalance(){
        String userInput = "2\n1\n1\n2\nC3";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        try{
            vendingMachineCLI.run();
        } catch (NoSuchElementException e){
            Assert.assertTrue(baos.toString().contains("Product cost more than balance"));
        }
    }

    @Test
    public void testInvalidKeycode(){
        String userInput = "2\n2\naa";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        try{
            vendingMachineCLI.run();
        } catch (NoSuchElementException e){
            Assert.assertTrue(baos.toString().contains("Invalid keycode."));
        }
    }

    @Test
    public void testVending(){
        String userInput = "2\n1\n5\n2\nA1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        try{
            vendingMachineCLI.run();
        } catch (NoSuchElementException e){
            Assert.assertTrue(baos.toString().contains("Vending Potato Crisps..."));
        }
    }

    @Test
    public void testAuditLog() throws IOException {
        String userInput = "A\n1\n2\n1\nA\n10\n2\nAA\nD1\n2\nC1\n2\nB1\n2\nA1\nA\n3\n4\n3";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        vendingMachineCLI.run();
        File logPath = new File("src/test/resources/LogTest.txt");
        String content = Files.readString(logPath.toPath());
        File validatedLogFile = new File("src/test/resources/CorrectLogOutput");
        String correctLogFile = Files.readString(validatedLogFile.toPath());
        String[] split = correctLogFile.split("\\n");
        for(String item: split){
            Assert.assertTrue(content.trim().contains(item));
        }
    }

    @Test
    public void testSalesReport() throws IOException {
        String userInput = "A\n1\n2\n1\nA\n10\n2\nAA\nD1\n2\nC1\n2\nB1\n2\nA1\nA\n3\n4\n3";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        vendingMachineCLI.run();
        File logPath = new File("src/test/resources/SalesReportTest.txt");
        String content = Files.readString(logPath.toPath());
        File validatedLogFile = new File("src/test/resources/CorrectSalesReport");
        String correctLogFile = Files.readString(validatedLogFile.toPath());
        Assert.assertEquals(content.trim(), correctLogFile.trim());

    }


    @After
    public void tearDown(){
        System.out.flush();
        System.setOut(old);
        //System.out.println(baos.toString());
    }
}
