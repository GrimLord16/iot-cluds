package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.StudentCardController;
import com.ua.lviv.iot.controller.StudentController;
import com.ua.lviv.iot.domain.Student;
import com.ua.lviv.iot.domain.StudentCard;
import com.ua.lviv.iot.dto.StudentCardDTO;
import com.ua.lviv.iot.dto.StudentDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentDTOAssembler implements RepresentationModelAssembler<Student, StudentDTO> {

    @Override
    public StudentDTO toModel(Student entity) {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
        Link selfLink = linkTo(methodOn(StudentController.class).getStudent(studentDTO.getId())).withSelfRel();
        studentDTO.add(selfLink);
        Link equipmentLink = linkTo(methodOn(StudentController.class).getAllEquipmentsForStudent(entity.getId())).withRel("equipments");
        studentDTO.add(equipmentLink);
        return studentDTO;
    }

    @Override
    public CollectionModel<StudentDTO> toCollectionModel(Iterable<? extends Student> entities) {
        CollectionModel<StudentDTO> studentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();
        studentDTOS.add(selfLink);
        return studentDTOS;
    }

    public CollectionModel<StudentDTO> toCollectionModel(Iterable<? extends Student> entities, Link link) {
        CollectionModel<StudentDTO> studentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        studentDTOS.add(link);
        return studentDTOS;
    }
}
