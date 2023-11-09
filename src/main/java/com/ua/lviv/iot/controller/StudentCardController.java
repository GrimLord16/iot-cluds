package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.domain.StudentCard;
import com.ua.lviv.iot.dto.StudentCardDTO;
import com.ua.lviv.iot.dto.assembler.StudentCardDTOAssembler;
import com.ua.lviv.iot.service.StudentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/student_cards")
public class StudentCardController {

    @Autowired
    private StudentCardService studentCardService;
    @Autowired
    private StudentCardDTOAssembler studentCardDTOAssembler;

    @GetMapping(value = "/{studentCardId}")
    public ResponseEntity<StudentCardDTO> getStudentCard(@PathVariable Integer studentCardId) {
        StudentCard studentCard = studentCardService.findById(studentCardId);
        StudentCardDTO studentCardDTO = studentCardDTOAssembler.toModel(studentCard);
        return new ResponseEntity<>(studentCardDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<StudentCardDTO>> getAllStudentCards() {
        List<StudentCard> studentCards = studentCardService.findAll();
        CollectionModel<StudentCardDTO> studentCardDTOS = studentCardDTOAssembler.toCollectionModel(studentCards);
        return new ResponseEntity<>(studentCardDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<StudentCardDTO> addStudentCard(@RequestBody StudentCard studentCard) {
        StudentCard newStudentCard = studentCardService.create(studentCard);
        StudentCardDTO studentCardDTO = studentCardDTOAssembler.toModel(newStudentCard);
        return new ResponseEntity<>(studentCardDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{studentCardId}")
    public ResponseEntity<?> updateStudentCard(@RequestBody StudentCard uStudentCard, @PathVariable Integer studentCardId) {
        studentCardService.update(studentCardId, uStudentCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentCardId}")
    public ResponseEntity<?> deleteStudentCard(@PathVariable Integer studentCardId) {
        studentCardService.delete(studentCardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
