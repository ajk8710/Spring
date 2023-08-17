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
import com.synergisticit.validation.BankTransactionValidatorTransfer;

import jakarta.validation.Valid;

@Controller
public class BankTransactionControllerTransfer {
    
    @Autowired BankTransactionService bankTransactionService;
    @Autowired AccountService accountService;
    @Autowired BankTransactionValidatorTransfer bankTransactionValidatorTransfer;
    
    @RequestMapping("transferForm")
    public String bankTransactionFormTransfer(BankTransaction bankTransaction, Model model) {
        modelData(model);
        return "bankTransactionFormTransfer";
    }
    
    @RequestMapping("saveTransfer")
    public String saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        bankTransactionValidatorTransfer.validate(bankTransaction, br);
        
        if (!br.hasErrors()) {  // update accounts for transfer and save transaction for record.
            Account fromAccount = accountService.getAccountById(bankTransaction.getBankTransactionFromAccount());
            Account toAccount = accountService.getAccountById(bankTransaction.getBankTransactionToAccount());
            double transferAmount = bankTransaction.getTransactionAmount();
            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transferAmount);  // validator checks for balance > amount
            toAccount.setAccountBalance(toAccount.getAccountBalance() + transferAmount);
            accountService.saveAccount(fromAccount);
            accountService.saveAccount(toAccount);
            bankTransactionService.saveBankTransaction(bankTransaction);
            
            // do not need to call modelData(model) because it's in bankTransactionFormTransfer method
            return "redirect:transferForm";  // redirect to url transferForm which calls bankTransactionFormTransfer method
        }
        
        modelData(model);
        return "bankTransactionFormTransfer";  // do not redirect, keep the info entered and show error messages
    }
    
    private void modelData(Model model) {
        model.addAttribute("transactions", bankTransactionService.getAllBankTransactions());
        model.addAttribute("ListofAllAccounts", accountService.getAllAccounts());        
        // model.addAttribute("transactionTypes", TransactionType.values());
        model.addAttribute("selectedTransactionType", TransactionType.TRANSFER);
    }
}
