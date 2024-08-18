package com.fileprocessor.accounts.dto;

import lombok.Data;

@Data
public class WidthdrawResponse {
    private String bankName;
    private String accountNumber;
    private String type;
    private String amount;
    private String name;
}
