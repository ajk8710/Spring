package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidatorDeposit;

import jakarta.validation.Valid;

@Controller
public class BankTransactionControllerDeposit {
    
    @Autowired BankTransactionService bankTransactionService;
    @Autowired AccountService accountService;
    @Autowired BankTransactionValidatorDeposit bankTransactionValidatorDeposit;
    
    @RequestMapping("depositForm")
    public String bankTransactionFormDeposit(BankTransaction bankTransaction, Model model) {
        modelData(model);
        return "bankTransactionFormDeposit";
    }
    
    @RequestMapping("saveDeposit")
    public String saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        bankTransactionValidatorDeposit.validate(bankTransaction, br);
        
        if (!br.hasErrors()) {  // update account for deposit and save transaction for record.
            Account toAccount = accountService.getAccountById(bankTransaction.getBankTransactionToAccount());
            double depositAmount = bankTransaction.getTransactionAmount();
            toAccount.setAccountBalance(toAccount.getAccountBalance() + depositAmount);
            accountService.saveAccount(toAccount);
            bankTransactionService.saveBankTransaction(bankTransaction);
            
            // do not need to call modelData(model) because it's in bankTransactionFormDeposit method
            return "redirect:depositForm";  // redirect to url depositForm which calls bankTransactionFormDeposit method
        }
        
        modelData(model);
        return "bankTransactionFormDeposit";  // do not redirect, keep the info entered and show error messages
    }
    
    /*
    @RequestMapping("depositForm/update")
    public String updateBankTransaction(BankTransaction bankTransaction, Model model) {  // BankTransaction object created with only id is passed in by request param
        BankTransaction retrievedBankTransaction = bankTransactionService.getBankTransactionById(bankTransaction.getBankTransactionId());
        model.addAttribute("retrievedBankTransaction", retrievedBankTransaction);
        
        // using retrievedBankTransaction on the form to get the field works fine.
        // TransactionType selectedTransactionType = retrievedBankTransaction.getTransactionType();
        // model.addAttribute("selectedTransactionType", selectedTransactionType);
        
        modelData(model);
        return "bankTransactionFormDeposit";
    }
    
    @RequestMapping("depositForm/delete")
    public String deleteBankTransaction(BankTransaction bankTransaction, Model model) {
        bankTransactionService.deleteBankTransactionById(bankTransaction.getBankTransactionId());
        
        modelData(model);
        return "redirect:bankTransactionFormDeposit";
    }
    */
    
    private void modelData(Model model) {
        model.addAttribute("transactions", bankTransactionService.getAllBankTransactions());
        model.addAttribute("ListofAllAccounts", accountService.getAllAccounts());        
        // model.addAttribute("transactionTypes", TransactionType.values());
        model.addAttribute("selectedTransactionType", TransactionType.DEPOSIT);
    }
}
