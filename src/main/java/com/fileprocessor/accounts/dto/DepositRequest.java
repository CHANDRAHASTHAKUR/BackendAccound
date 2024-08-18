package com.fileprocessor.accounts.dto;

import lombok.Data;

@Data
public class DepositRequest {

    private String accountNumber;
    private String depositAmount;
}
