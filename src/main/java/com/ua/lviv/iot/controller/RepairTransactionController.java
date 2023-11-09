package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.RepairTransaction;
import com.ua.lviv.iot.dto.RepairTransactionDTO;
import com.ua.lviv.iot.dto.assembler.RepairTransactionDTOAssembler;
import com.ua.lviv.iot.service.RepairTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/repair_transactions")
public class RepairTransactionController {

    @Autowired
    private RepairTransactionService repairTransactionService;
    @Autowired
    private RepairTransactionDTOAssembler repairTransactionDTOAssembler;

    @GetMapping(value = "/{repairTransactionId}")
    public ResponseEntity<RepairTransactionDTO> getRepairTransaction(@PathVariable Integer repairTransactionId) {
        RepairTransaction repairTransaction = repairTransactionService.findById(repairTransactionId);
        RepairTransactionDTO repairTransactionDTO = repairTransactionDTOAssembler.toModel(repairTransaction);
        return new ResponseEntity<>(repairTransactionDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RepairTransactionDTO>> getAllRepairTransactions() {
        List<RepairTransaction> repairTransactions = repairTransactionService.findAll();
        CollectionModel<RepairTransactionDTO> repairDTOS = repairTransactionDTOAssembler.toCollectionModel(repairTransactions);
        return new ResponseEntity<>(repairDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RepairTransactionDTO> addRepairTransaction(@RequestBody RepairTransaction repairTransaction) {
        RepairTransaction newRepairTransaction = repairTransactionService.create(repairTransaction);
        RepairTransactionDTO repairTransactionDTO = repairTransactionDTOAssembler.toModel(newRepairTransaction);
        return new ResponseEntity<>(repairTransactionDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{repairTransactionId}")
    public ResponseEntity<?> updateRepairTransaction(@RequestBody RepairTransaction uRepairTransaction, @PathVariable Integer repairTransactionId) {
        repairTransactionService.update(repairTransactionId, uRepairTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{repairTransactionId}")
    public ResponseEntity<?> deleteRepairTransaction(@PathVariable Integer repairTransactionId) {
        repairTransactionService.delete(repairTransactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
