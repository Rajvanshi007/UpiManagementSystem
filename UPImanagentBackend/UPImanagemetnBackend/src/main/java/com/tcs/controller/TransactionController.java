
package com.tcs.controller;

import com.tcs.model.Transaction;
import com.tcs.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/account/{id}")
    public List<Transaction> history(@PathVariable Long id) {
        return service.getTransactions(id);
    }
}
