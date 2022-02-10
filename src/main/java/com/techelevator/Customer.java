package com.techelevator;

import java.math.BigDecimal;
import java.math.MathContext;

public class Customer {

    private BigDecimal totalMoney = BigDecimal.ZERO;

    public Customer() {

    }

    public void feedMoney(BigDecimal money){
        this.totalMoney = totalMoney.add(money);
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void spendMoney(BigDecimal money){
        this.totalMoney = totalMoney.subtract(money);
    }

    public String returnChange(){
        MathContext mc = new MathContext(0);
        BigDecimal extra = totalMoney.remainder(new BigDecimal(".25"));
        String quarters = totalMoney.subtract(extra).divide(new BigDecimal(".25")).toString();
        this.totalMoney = extra;
        extra = totalMoney.remainder(new BigDecimal(".10"));
        String dimes = totalMoney.subtract(extra).divide(new BigDecimal(".10")).toString();
        this.totalMoney = extra;
        extra = totalMoney.remainder(new BigDecimal(".05"));
        String nickels = totalMoney.subtract(extra).divide(new BigDecimal(".05")).toString();
        this.totalMoney = BigDecimal.ZERO;
        String output = "";
        if(quarters.equals("1")){
            output += quarters + " Quarter ";
        } else if(!quarters.equals("0")){
            output += quarters + " Quarters ";
        }
        if(dimes.equals("1")){
            output += dimes + " Dime ";
        } else if(!dimes.equals("0")){
            output += dimes + " Dimes ";
        }
        if(nickels.equals("1")){
            output += nickels + " Nickel ";
        } else if(!nickels.equals("0")){
            output += nickels + " Nickels ";
        }


        return output;

    }
}
