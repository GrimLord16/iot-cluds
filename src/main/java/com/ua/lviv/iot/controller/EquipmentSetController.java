package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.EquipmentSet;
import com.ua.lviv.iot.dto.EquipmentSetDTO;
import com.ua.lviv.iot.dto.assembler.EquipmentSetDTOAssembler;
import com.ua.lviv.iot.service.EquipmentSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/equipment_sets")
public class EquipmentSetController {

    @Autowired
    private EquipmentSetService equipmentSetService;
    @Autowired
    private EquipmentSetDTOAssembler equipmentSetDTOAssembler;

    @GetMapping(value = "/{equipmentSetId}")
    public ResponseEntity<EquipmentSetDTO> getEquipmentSet(@PathVariable Integer equipmentSetId) {
        EquipmentSet equipmentSet = equipmentSetService.findById(equipmentSetId);
        EquipmentSetDTO equipmentSetDTO = equipmentSetDTOAssembler.toModel(equipmentSet);
        return new ResponseEntity<>(equipmentSetDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EquipmentSetDTO>> getAllEquipmentSets() {
        List<EquipmentSet> equipmentSets = equipmentSetService.findAll();
        CollectionModel<EquipmentSetDTO> equipmentSetDTOS = equipmentSetDTOAssembler.toCollectionModel(equipmentSets);
        return new ResponseEntity<>(equipmentSetDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/instructors/{instructorId}")
    public ResponseEntity<CollectionModel<EquipmentSetDTO>> getEquipmentSetsByInstructorId(@PathVariable Integer instructorId) {
        List<EquipmentSet> equipmentSets = equipmentSetService.findEquipmentSetsByInstructorId(instructorId);
        CollectionModel<EquipmentSetDTO> equipmentSetDTOS = equipmentSetDTOAssembler.toCollectionModel(equipmentSets);
        return new ResponseEntity<>(equipmentSetDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EquipmentSetDTO> addEquipmentSet(@RequestBody EquipmentSet equipmentSet) {
        EquipmentSet newEquipment = equipmentSetService.create(equipmentSet);
        EquipmentSetDTO equipmentSetDTO = equipmentSetDTOAssembler.toModel(newEquipment);
        return new ResponseEntity<>(equipmentSetDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{equipmentSetId}")
    public ResponseEntity<?> updateEquipmentSet(@RequestBody EquipmentSet uEquipmentSet, @PathVariable Integer equipmentSetId) {
        equipmentSetService.update(equipmentSetId, uEquipmentSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(@PathVariable Integer equipmentId) {
        equipmentSetService.delete(equipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
