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
import com.synergisticit.validation.BankTransactionValidatorWithdrawal;

import jakarta.validation.Valid;

@Controller
public class BankTransactionControllerWithdrawal {
    
    @Autowired BankTransactionService bankTransactionService;
    @Autowired AccountService accountService;
    @Autowired BankTransactionValidatorWithdrawal bankTransactionValidatorWithdrawal;
    
    @RequestMapping("withdrawalForm")
    public String bankTransactionFormWithdrawal(BankTransaction bankTransaction, Model model) {
        modelData(model);
        return "bankTransactionFormWithdrawal";
    }
    
    @RequestMapping("saveWithdrawal")
    public String saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        bankTransactionValidatorWithdrawal.validate(bankTransaction, br);
        
        if (!br.hasErrors()) {  // update account for withdrawal and save transaction for record.
            Account fromAccount = accountService.getAccountById(bankTransaction.getBankTransactionFromAccount());
            double withdrawalAmount = bankTransaction.getTransactionAmount();
            fromAccount.setAccountBalance(fromAccount.getAccountBalance() - withdrawalAmount);  // validator checks for balance > amount
            accountService.saveAccount(fromAccount);
            bankTransactionService.saveBankTransaction(bankTransaction);
            
            // do not need to call modelData(model) because it's in bankTransactionFormWithdrawal method
            return "redirect:withdrawalForm";  // redirect to url withdrawalForm which calls bankTransactionFormWithdrawal method
        }
        
        modelData(model);
        return "bankTransactionFormWithdrawal";  // do not redirect, keep the info entered and show error messages
    }
    
    private void modelData(Model model) {
        model.addAttribute("transactions", bankTransactionService.getAllBankTransactions());
        model.addAttribute("ListofAllAccounts", accountService.getAllAccounts());        
        // model.addAttribute("transactionTypes", TransactionType.values());
        model.addAttribute("selectedTransactionType", TransactionType.WITHDRAWAL);
    }
}
