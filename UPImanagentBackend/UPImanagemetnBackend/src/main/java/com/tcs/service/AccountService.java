package com.tcs.service;

import com.tcs.model.Account;
import com.tcs.model.User;
import com.tcs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ✅ get all accounts of user
    public List<Account> getAccountsByUser(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    // ✅ get account by id (REQUIRED for add-money & transfer)
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // ✅ save/update account
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account getAccountByUserAndType(Long userId, String type) {
        return accountRepository
                .findByUserIdAndType(userId, type)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    public Optional<Account> getAccountByAccountNumber(String accNo) {
        return accountRepository.findByAccountNumber(accNo);
    }

    public Account getOrCreateSavingsAccount(User user) {

        return accountRepository
            .findByUserIdAndType(user.getId(), "SAVINGS")
            .orElseGet(() -> {
                Account acc = new Account();
                acc.setType("SAVINGS");
                acc.setBalance(0);
                acc.setUser(user);
                return accountRepository.save(acc);
            });
    }
    public Account createDefaultAccount(User user) {

        Account acc = new Account();
        acc.setUser(user);
        acc.setType("SAVINGS");
        acc.setBalance(0);
        acc.setAccountNumber(generateAccountNumber());

        return accountRepository.save(acc);
    }
    private String generateAccountNumber() {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 7; i++) {   // 🔥 6–7 length (7 here)
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }

        return sb.toString();
    }


}
