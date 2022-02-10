package com.techelevator;

import java.math.BigDecimal;

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
        
    }
}
