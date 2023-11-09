package com.ua.lviv.iot.controller;


import com.ua.lviv.iot.domain.Repair;
import com.ua.lviv.iot.dto.RepairDTO;
import com.ua.lviv.iot.dto.assembler.RepairDTOAssembler;
import com.ua.lviv.iot.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private RepairDTOAssembler repairDTOAssembler;

    @GetMapping(value = "/{repairId}")
    public ResponseEntity<RepairDTO> getRepair(@PathVariable Integer repairId) {
        Repair repair = repairService.findById(repairId);
        RepairDTO repairDTO = repairDTOAssembler.toModel(repair);
        return new ResponseEntity<>(repairDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RepairDTO>> getAllRepairs() {
        List<Repair> repairs = repairService.findAll();
        CollectionModel<RepairDTO> repairDTOS = repairDTOAssembler.toCollectionModel(repairs);
        return new ResponseEntity<>(repairDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{studentId}")
    public ResponseEntity<RepairDTO> addRepairWithStudent(@RequestBody Repair repair, @PathVariable Integer studentId) {
        Repair newRepair = repairService.create(repair, studentId);
        RepairDTO repairDTO = repairDTOAssembler.toModel(newRepair);
        return new ResponseEntity<>(repairDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{repairId}")
    public ResponseEntity<?> updateRepair(@RequestBody Repair uRepair, @PathVariable Integer repairId) {
        repairService.update(repairId, uRepair);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{repairId}/students/{studentId}")
    public ResponseEntity<?> updateRepair(@RequestBody Repair uRepair, @PathVariable Integer repairId, @PathVariable Integer studentId) {
        repairService.update(repairId, uRepair, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{repairId}/repair_transactions/{repairTransactionId}")
    public ResponseEntity<?> paymentDone(@PathVariable Integer repairId, @PathVariable Integer repairTransactionId) {
        repairService.paymentDone(repairId, repairTransactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{repairId}")
    public ResponseEntity<?> deleteRepair(@PathVariable Integer repairId) {
        repairService.delete(repairId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
