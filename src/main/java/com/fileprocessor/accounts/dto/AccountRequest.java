package com.fileprocessor.accounts.dto;

import lombok.Data;

@Data
public class AccountRequest {

    private String name;
    private String amount;
    private String type;
}
