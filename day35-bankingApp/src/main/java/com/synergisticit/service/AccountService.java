package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;

public interface AccountService {
    Account saveAccount(Account account);
    Account getAccountById(Long accountId);
    List<Account> getAllAccounts();
    void deleteAccountById(Long accountId);
    boolean existById(Long accountId);
}
