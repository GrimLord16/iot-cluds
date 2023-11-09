package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.StudentCardController;
import com.ua.lviv.iot.domain.StudentCard;
import com.ua.lviv.iot.dto.StudentCardDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentCardDTOAssembler implements RepresentationModelAssembler<StudentCard, StudentCardDTO>{

    @Override
    public StudentCardDTO toModel(StudentCard entity) {
        StudentCardDTO studentCardDTO = StudentCardDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .password(entity.getPassword())
                .build();
        Link selfLink = linkTo(methodOn(StudentCardController.class).getStudentCard(studentCardDTO.getId())).withSelfRel();
        studentCardDTO.add(selfLink);
        return studentCardDTO;
    }

    @Override
    public CollectionModel<StudentCardDTO> toCollectionModel(Iterable<? extends StudentCard> entities) {
        CollectionModel<StudentCardDTO> studentCardDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(StudentCardController.class).getAllStudentCards()).withSelfRel();
        studentCardDTOS.add(selfLink);
        return studentCardDTOS;
    }

    public CollectionModel<StudentCardDTO> toCollectionModel(Iterable<? extends StudentCard> entities, Link link) {
        CollectionModel<StudentCardDTO> studentCardDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        studentCardDTOS.add(link);
        return studentCardDTOS;
    }
}
