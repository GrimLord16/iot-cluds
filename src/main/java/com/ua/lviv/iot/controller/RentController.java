package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.dto.RentDTO;
import com.ua.lviv.iot.dto.assembler.RentDTOAssembler;
import com.ua.lviv.iot.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rents")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentDTOAssembler rentDTOAssembler;

    @GetMapping(value = "/{rentId}")
    public ResponseEntity<RentDTO> getRent(@PathVariable Integer rentId) {
        Rent rent = rentService.findById(rentId);
        RentDTO rentDTO = rentDTOAssembler.toModel(rent);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RentDTO>> getAllRents() {
        List<Rent> rents = rentService.findAll();
        CollectionModel<RentDTO> rentDTOS = rentDTOAssembler.toCollectionModel(rents);
        return new ResponseEntity<>(rentDTOS, HttpStatus.OK);
    }



    @PostMapping(value = "/{studentId}")
    public ResponseEntity<RentDTO> addRentWithStudent(@RequestBody Rent rent, @PathVariable Integer studentId) {
        Rent newRent = rentService.create(rent, studentId);
        RentDTO rentDTO = rentDTOAssembler.toModel(newRent);
        return new ResponseEntity<>(rentDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{rentId}")
    public ResponseEntity<?> updateRent(@RequestBody Rent uRent, @PathVariable Integer rentId) {
        rentService.update(rentId, uRent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{rentId}/transactions/{transactionId}")
    public ResponseEntity<?> paymentDone(@PathVariable Integer rentId, @PathVariable Integer transactionId) {
        rentService.paymentDone(rentId, transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{rentId}/students/{studentId}")
    public ResponseEntity<?> updateRentWithStudent(@RequestBody Rent uRent, @PathVariable Integer rentId,
                                                  @PathVariable Integer studentId) {
        rentService.update(rentId, uRent, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rentId}")
    public ResponseEntity<?> deleteRent(@PathVariable Integer rentId) {
        rentService.delete(rentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
