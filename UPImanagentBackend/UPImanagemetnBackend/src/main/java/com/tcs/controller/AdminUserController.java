package com.tcs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.model.Account;
import com.tcs.model.Transaction;
import com.tcs.model.User;
import com.tcs.repository.TransactionRepository;
import com.tcs.service.AccountService;
import com.tcs.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionRepository txnRepo;

    public AdminUserController(UserService u, AccountService a, TransactionRepository t) {
        this.userService = u;
        this.accountService = a;
        this.txnRepo = t;
    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}/accounts")
    public List<Account> accounts(@PathVariable Long id) {
        return accountService.getAccountsByUser(id);
    }

    @GetMapping("/user/{id}/transactions")
    public List<Transaction> txns(@PathVariable Long id) {
        return txnRepo.findByAccountUserId(id);
    }
}

