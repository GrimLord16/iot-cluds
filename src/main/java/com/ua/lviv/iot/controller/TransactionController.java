package com.ua.lviv.iot.controller;


import com.ua.lviv.iot.domain.Transaction;
import com.ua.lviv.iot.dto.TransactionDTO;
import com.ua.lviv.iot.dto.assembler.TransactionDTOAssembler;
import com.ua.lviv.iot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionDTOAssembler transactionDTOAssembler;

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Integer transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        TransactionDTO transactionDTO = transactionDTOAssembler.toModel(transaction);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        CollectionModel<TransactionDTO> transactionDTOS = transactionDTOAssembler.toCollectionModel(transactions);
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction equipment) {
        Transaction newTransaction = transactionService.create(equipment);
        TransactionDTO transactionDTO = transactionDTOAssembler.toModel(newTransaction);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{transactionId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction uTransaction, @PathVariable Integer transactionId) {
        transactionService.update(transactionId, uTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
