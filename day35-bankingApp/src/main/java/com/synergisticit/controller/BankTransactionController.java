package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;


import jakarta.validation.Valid;

@Controller
public class BankTransactionController {
    
    @Autowired BankTransactionService bankTransactionService;
    @Autowired AccountService accountService;
    
    @RequestMapping("bankTransactionForm")
    public String bankTransactionForm(BankTransaction bankTransaction, Model model) {
        modelData(model);
        return "bankTransactionForm";
    }
    
    @RequestMapping("saveBankTransaction")
    public String saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        
        if (!br.hasErrors()) {
            bankTransactionService.saveBankTransaction(bankTransaction);
            // do not need to call modelData(model) because it's in bankTransactionForm method
            return "redirect:bankTransactionForm";  // redirect to url bankTransactionForm which calls bankTransactionForm method
        }
        
        modelData(model);
        return "bankTransactionForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateBankTransaction")
    public String updateBankTransaction(BankTransaction bankTransaction, Model model) {  // BankTransaction object created with only id is passed in by request param
        BankTransaction retrievedBankTransaction = bankTransactionService.getBankTransactionById(bankTransaction.getBankTransactionId());
        model.addAttribute("retrievedBankTransaction", retrievedBankTransaction);
        
        // TransactionType selectedTransactionType = retrievedBankTransaction.getTransactionType();
        // model.addAttribute("selectedTransactionType", selectedTransactionType);
        
        modelData(model);
        return "bankTransactionForm";
    }
    
    @RequestMapping("deleteBankTransaction")
    public String deleteBankTransaction(BankTransaction bankTransaction, Model model) {
        bankTransactionService.deleteBankTransactionById(bankTransaction.getBankTransactionId());
        
        modelData(model);
        return "redirect:bankTransactionForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("transactions", bankTransactionService.getAllBankTransactions());
        model.addAttribute("ListofAllAccounts", accountService.getAllAccounts());
        model.addAttribute("transactionTypes", TransactionType.values());
    }
}
