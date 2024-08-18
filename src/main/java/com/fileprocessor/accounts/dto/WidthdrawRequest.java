package com.fileprocessor.accounts.dto;

import lombok.Data;

@Data
public class WidthdrawRequest {

    private String accountNumber;
    private String widthdrawAmount;
}
