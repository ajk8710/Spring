package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;

import jakarta.validation.Valid;

@Controller
public class AccountController {
    
    @Autowired AccountService accountService;
    @Autowired CustomerService customerService;
    @Autowired BranchService branchService;
    
    @RequestMapping("accountForm")
    public String accountForm(Account account, Model model) {
        modelData(model);
        return "accountForm";
    }
    
    @RequestMapping("saveAccount")
    public String saveAccount(@Valid @ModelAttribute Account account, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        
        if (!br.hasErrors()) {
            accountService.saveAccount(account);
            // do not need to call modelData(model) because it's in accountForm method
            return "redirect:accountForm";  // redirect to url accountForm which calls accountForm method
        }
        
        modelData(model);
        return "accountForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateAccount")
    public String updateAccount(Account account, Model model) {  // Account object created with only id is passed in by request param
        Account retrievedAccount = accountService.getAccountById(account.getAccountId());
        model.addAttribute("retrievedAccount", retrievedAccount);
        
        AccountType selectedAccountType = retrievedAccount.getAccountType();
        model.addAttribute("selectedAccountType", selectedAccountType);
        System.out.println(selectedAccountType);

        Branch selectedBranch = retrievedAccount.getAccountBranch();
        model.addAttribute("selectedBranch", selectedBranch);
        
        Customer selectedCustomer = retrievedAccount.getAccountCustomer();
        model.addAttribute("selectedCustomer", selectedCustomer);
        
        modelData(model);
        return "accountForm";
    }
    
    @RequestMapping("deleteAccount")
    public String deleteAccount(Account account, Model model) {
        accountService.deleteAccountById(account.getAccountId());
        
        modelData(model);
        return "redirect:accountForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("ListofAllBranches", branchService.getAllBranches());
        model.addAttribute("ListofAllCustomers", customerService.getAllCustomers());
        model.addAttribute("accountTypes", AccountType.values());
        
        // for (AccountType at : AccountType.values()) {System.out.println(at);}
    }
}
