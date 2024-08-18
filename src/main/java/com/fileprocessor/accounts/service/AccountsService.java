package com.fileprocessor.accounts.service;

import com.fileprocessor.accounts.dto.AccountRequest;
import com.fileprocessor.accounts.dto.AccountResponse;
import com.fileprocessor.accounts.dto.DepositRequest;
import com.fileprocessor.accounts.dto.WidthdrawRequest;
import com.fileprocessor.accounts.entity.Accounts;
import com.fileprocessor.accounts.repository.AccountsRepo;
import com.fileprocessor.accounts.utils.AccountsConstant;
import com.fileprocessor.accounts.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountsService {


    private Helper helper = new Helper();

    @Autowired
    private AccountsRepo accountRepo;

    public AccountResponse createAccount(AccountRequest accountRequest) {
        Accounts accounts = new Accounts();

        accounts.setAccountNumber(helper.getAccountNumber());
        accounts.setBankName(AccountsConstant.bankName);
        accounts.setCustomerId(helper.generateCustomerId());
        accounts.setIfsc(AccountsConstant.ifsc);
        accounts.setName(accountRequest.getName());
        accounts.setType(accountRequest.getType());
        accounts.setAmount(accountRequest.getAmount());
        accounts.setCreatedAt(new Date());
        accounts.setUpdatedAt(new Date());
        Accounts afterSave = accountRepo.save(accounts);
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(afterSave.getAccountNumber());
        response.setType(afterSave.getType());
        response.setBankName(afterSave.getBankName());
        response.setName(accounts.getName());
        response.setIFSC(afterSave.getIfsc());
        response.setAmount(afterSave.getAmount());
        return response;

    }

    public List<Accounts> getAllAccounts() {
        return accountRepo.findAll();
    }

    public Accounts getAccountById(Long id) {
        return accountRepo.findById(id).orElse(null);

    }

    public Accounts updateAccountById(Long id, AccountRequest accountRequest) {
        Accounts accounts = accountRepo.findById(id).orElse(null);
        if(accounts != null){
            accounts.setName(accountRequest.getName());
            accounts.setAmount(accountRequest.getAmount());
            accounts = accountRepo.save(accounts);
        }
        return accounts;
    }

    public String deleteAccountById(Long id) {
        accountRepo.deleteById(id);
        return "Account Deleted Successfully";
    }

    public String depositAmount(Long id, DepositRequest depositRequest) {
        Accounts accounts = accountRepo.findById(id).orElse(null);
        int newBalance = 0;
        if(accounts != null){
            String currentbalance = accounts.getAmount();
            if(accounts.getAccountNumber().equals(depositRequest.getAccountNumber())){
                newBalance = Integer.parseInt(currentbalance) + Integer.parseInt(depositRequest.getDepositAmount());
                accounts.setAmount(String.valueOf(newBalance));
                accountRepo.save(accounts);
            }else {
                return "Account number is wrong";
            }
        }else{
            return "No Account found";
        }
        return "Deposit Success";
    }

    public String widthdrawAmount(Long id, WidthdrawRequest widthdrawRequest) {
        Accounts accounts = accountRepo.findById(id).orElse(null);
        int newBalance = 0;
        if(accounts != null){
            String currentbalance = accounts.getAmount();
            if (Integer.parseInt(currentbalance) < Integer.parseInt(widthdrawRequest.getWidthdrawAmount())){
                return "Insufficient Balance";
            }
            if(accounts.getAccountNumber().equals(widthdrawRequest.getAccountNumber())){
                newBalance = Integer.parseInt(currentbalance) - Integer.parseInt(widthdrawRequest.getWidthdrawAmount());
                accounts.setAmount(String.valueOf(newBalance));
                accountRepo.save(accounts);
            }else {
                return "Account number is wrong";
            }
        }else{
            return "No Account found";
        }
        return "Widthdraw Success";
    }

    public String checkBalance(Long id){
        Accounts accounts = accountRepo.findById(id).orElse(null);
        if (accounts != null){
            String balance = accounts.getAmount();
            return "Your Current Balance is " + balance;
        }else{
            return "No Account found";
        }

    }
}
