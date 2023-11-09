package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.RentController;
import com.ua.lviv.iot.controller.RepairController;
import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Repair;
import com.ua.lviv.iot.dto.RentDTO;
import com.ua.lviv.iot.dto.RepairDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepairDTOAssembler implements RepresentationModelAssembler<Repair, RepairDTO> {
    @Override
    public RepairDTO toModel(Repair entity) {
        RepairDTO repairDTO = RepairDTO.builder()
                .id(entity.getId())
                .repairCost(entity.getRepairCost())
                .repairDoneDate(entity.getRepairDoneDate())
                .build();
        Link selfLink = linkTo(methodOn(RepairController.class).getRepair(repairDTO.getId())).withSelfRel();
        repairDTO.add(selfLink);
        return repairDTO;
    }

    @Override
    public CollectionModel<RepairDTO> toCollectionModel(Iterable<? extends Repair> entities) {
        CollectionModel<RepairDTO> repairDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RepairController.class).getAllRepairs()).withSelfRel();
        repairDTOS.add(selfLink);
        return repairDTOS;
    }

    public CollectionModel<RepairDTO> toCollectionModel(Iterable<? extends Repair> entities, Link link) {
        CollectionModel<RepairDTO> repairDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        repairDTOS.add(link);
        return repairDTOS;
    }
}
