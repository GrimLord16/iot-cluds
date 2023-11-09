package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.RentController;
import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.dto.RentDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RentDTOAssembler implements RepresentationModelAssembler<Rent, RentDTO> {
    @Override
    public RentDTO toModel(Rent entity) {
        RentDTO rentDTO = RentDTO.builder()
                .id(entity.getId())
                .bookingTime(entity.getBookingTime())
                .returnTime(entity.getReturnTime())
                .build();
        Link selfLink = linkTo(methodOn(RentController.class).getRent(rentDTO.getId())).withSelfRel();
        rentDTO.add(selfLink);
        return rentDTO;
    }

    @Override
    public CollectionModel<RentDTO> toCollectionModel(Iterable<? extends Rent> entities) {
        CollectionModel<RentDTO> rentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RentController.class).getAllRents()).withSelfRel();
        rentDTOS.add(selfLink);
        return rentDTOS;
    }

    public CollectionModel<RentDTO> toCollectionModel(Iterable<? extends Rent> entities, Link link) {
        CollectionModel<RentDTO> rentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        rentDTOS.add(link);
        return rentDTOS;
    }
}
