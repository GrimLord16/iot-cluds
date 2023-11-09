package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.Instructor;
import com.ua.lviv.iot.dto.InstructorDTO;
import com.ua.lviv.iot.dto.assembler.InstructorDTOAssembler;
import com.ua.lviv.iot.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/instructors")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @Autowired
    InstructorDTOAssembler instructorDTOAssembler;

    @GetMapping(value = "/{instructorId}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable Integer instructorId) {
        Instructor instructor = instructorService.findById(instructorId);
        InstructorDTO instructorDTO = instructorDTOAssembler.toModel(instructor);
        return new ResponseEntity<>(instructorDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<InstructorDTO>> getAllInstructors() {
        List<Instructor> instructors = instructorService.findAll();
        CollectionModel<InstructorDTO> instructorDTOS = instructorDTOAssembler.toCollectionModel(instructors);
        return new ResponseEntity<>(instructorDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Integer> insertInstructor(@RequestBody Instructor instructor) {
        Integer id = instructorService.insertion(instructor);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{instructorId}")
    public ResponseEntity<?> updateInstructor(@RequestBody Instructor uInstructor, @PathVariable Integer instructorId) {
        instructorService.update(instructorId, uInstructor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{instructorId}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Integer instructorId) {
        instructorService.delete(instructorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{instructorId}/cities/{equipmentSetId}")
    public ResponseEntity<Integer> instructorEquipmentSet(@PathVariable Integer instructorId, @PathVariable Integer equipmentSetId) {
        Integer id = instructorService.instructorEquipmentSet(instructorId, equipmentSetId);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PostMapping("/nonames")
    public ResponseEntity<?> addNonames() {
        instructorService.addNonames();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
