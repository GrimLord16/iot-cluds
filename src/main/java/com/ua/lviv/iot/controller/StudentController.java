package com.ua.lviv.iot.controller;


import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.domain.Student;
import com.ua.lviv.iot.dto.EquipmentDTO;
import com.ua.lviv.iot.dto.StudentDTO;
import com.ua.lviv.iot.dto.assembler.EquipmentDTOAssembler;
import com.ua.lviv.iot.dto.assembler.StudentDTOAssembler;
import com.ua.lviv.iot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDTOAssembler studentDTOAssembler;

    @Autowired
    private EquipmentDTOAssembler equipmentDTOAssembler;

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer studentId) {
        Student student = studentService.findById(studentId);
        StudentDTO studentDTO = studentDTOAssembler.toModel(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.findAll();
        CollectionModel<StudentDTO> studentDTOS = studentDTOAssembler.toCollectionModel(students);
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}/equipments")
    public ResponseEntity<CollectionModel<EquipmentDTO>> getAllEquipmentsForStudent(@PathVariable Integer studentId) {
        List<Equipment> equipmentList = studentService.findEquipmentsById(studentId);
        Link selfLink = linkTo(methodOn(StudentController.class).getAllEquipmentsForStudent(studentId)).withSelfRel();
        CollectionModel<EquipmentDTO> equipmentDTOS = equipmentDTOAssembler.toCollectionModel(equipmentList, selfLink);
        return new ResponseEntity<>(equipmentDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/{studentCardId}")
    public ResponseEntity<StudentDTO> addStudentWithStudentCard(@RequestBody Student student, @PathVariable Integer studentCardId) {
        Student newStudent = studentService.create(student, studentCardId);
        StudentDTO studentDTO = studentDTOAssembler.toModel(newStudent);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{studentId}")
    public ResponseEntity<?> updateStudent(@RequestBody Student uStudent, @PathVariable Integer studentId) {
        studentService.update(studentId, uStudent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{studentId}/student_cards/{studentCardId}")
    public ResponseEntity<?> updateStudentWithStudentCard(@RequestBody Student uStudent, @PathVariable Integer studentId, @PathVariable Integer studentCardId) {
        studentService.update(studentId, uStudent, studentCardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "{studentId}/equipment/{equipmentId}")
    public ResponseEntity<StudentDTO> addEquipmentForStudent(@PathVariable Integer studentId, @PathVariable Integer equipmentId) {
        Student student = studentService.addEquipmentForStudent(studentId, equipmentId);
        StudentDTO studentDTO = studentDTOAssembler.toModel(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "{studentId}/equipment/{equipmentId}")
    public ResponseEntity<StudentDTO> removeEquipmentForStudent(@PathVariable Integer studentId, @PathVariable Integer equipmentId) {
        Student student = studentService.removeEquipmentForStudent(studentId, equipmentId);
        StudentDTO studentDTO = studentDTOAssembler.toModel(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }


}
