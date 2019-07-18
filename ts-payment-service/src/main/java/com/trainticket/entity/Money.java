package com.trainticket.entity;

import com.trainticket.tars.payment.MoneyTars;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="addMoney")
public class Money {
    private String userId;
    private String money;


    public Money(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public MoneyTars toTars(){
        MoneyTars moneyTars = new MoneyTars();
        moneyTars.setMoney(this.money);
        moneyTars.setUserId(this.userId);
        return moneyTars;
    }

}
