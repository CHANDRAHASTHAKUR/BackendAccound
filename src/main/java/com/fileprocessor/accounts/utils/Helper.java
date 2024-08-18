package com.fileprocessor.accounts.utils;

import java.util.Random;
import java.util.UUID;

public class Helper {

    public String getAccountNumber(){
        Random random = new Random();
        int accNum = 10000000 + random.nextInt(90000000);
        String accountNumber = String.valueOf(accNum);
        return accountNumber;

    }

    public String generateCustomerId(){

        return UUID.randomUUID().toString();
    }
}
