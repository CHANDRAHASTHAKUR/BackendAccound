package com.fileprocessor.accounts.dto;

import lombok.Data;

@Data
public class AccountResponse {

    private String bankName;
    private String IFSC;
    private String accountNumber;
    private String type;
    private String amount;
    private String name;
}
