package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.InstructorController;
import com.ua.lviv.iot.domain.Instructor;
import com.ua.lviv.iot.dto.InstructorDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InstructorDTOAssembler implements RepresentationModelAssembler<Instructor, InstructorDTO> {
    @Override
    public InstructorDTO toModel(Instructor entity) {
        InstructorDTO instructorDTO = InstructorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .middleName(entity.getMiddleName())
                .build();
        Link selfLink = linkTo(methodOn(InstructorController.class).getInstructor(instructorDTO.getId())).withSelfRel();
        instructorDTO.add(selfLink);
        return instructorDTO;
    }

    @Override
    public CollectionModel<InstructorDTO> toCollectionModel(Iterable<? extends Instructor> entities) {
        CollectionModel<InstructorDTO> instructorDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(InstructorController.class).getAllInstructors()).withSelfRel();
        instructorDTOS.add(selfLink);
        return instructorDTOS;
    }

    public CollectionModel<InstructorDTO> toCollectionModel(Iterable<? extends Instructor> entities, Link link) {
        CollectionModel<InstructorDTO> instructorDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        instructorDTOS.add(link);
        return instructorDTOS;
    }
}