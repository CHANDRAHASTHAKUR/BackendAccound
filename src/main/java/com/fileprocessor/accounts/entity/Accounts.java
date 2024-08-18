package com.fileprocessor.accounts.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Accounts {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String customerId;
        private String accountNumber;
        private String ifsc;
        private String info;
        private String bankName;
        private String amount;
        private String type;
        private Date createdAt;
        private Date updatedAt;

}
