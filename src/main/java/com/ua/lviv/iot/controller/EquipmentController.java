package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.dto.EquipmentDTO;
import com.ua.lviv.iot.dto.assembler.EquipmentDTOAssembler;
import com.ua.lviv.iot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentDTOAssembler equipmentDTOAssembler;

    @GetMapping(value = "/{equipmentId}")
    public ResponseEntity<EquipmentDTO> getEquipment(@PathVariable Integer equipmentId) {
        Equipment equipment = equipmentService.findById(equipmentId);
        EquipmentDTO equipmentDTO = equipmentDTOAssembler.toModel(equipment);
        return new ResponseEntity<>(equipmentDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EquipmentDTO>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.findAll();
        CollectionModel<EquipmentDTO> equipmentDTOS = equipmentDTOAssembler.toCollectionModel(equipments);
        return new ResponseEntity<>(equipmentDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EquipmentDTO> addEquipment(@RequestBody Equipment equipment) {
        Equipment newEquipment = equipmentService.create(equipment);
        EquipmentDTO equipmentDTO = equipmentDTOAssembler.toModel(newEquipment);
        return new ResponseEntity<>(equipmentDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{equipmentSetId}")
    public ResponseEntity<EquipmentDTO> addEquipmentWithEquipmentSet(@RequestBody Equipment equipment, @PathVariable Integer equipmentSetId) {
        Equipment newEquipment = equipmentService.create(equipment, equipmentSetId);
        EquipmentDTO equipmentDTO = equipmentDTOAssembler.toModel(newEquipment);
        return new ResponseEntity<>(equipmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{equipmentId}")
    public ResponseEntity<?> updateEquipment(@RequestBody Equipment uEquipment, @PathVariable Integer equipmentId) {
        equipmentService.update(equipmentId, uEquipment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(@PathVariable Integer equipmentId) {
        equipmentService.delete(equipmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/equipment_cursor/")
    public ResponseEntity<?> carCursor() {
        equipmentService.equipmentCursorProcedure();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
