package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.EquipmentController;
import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.dto.EquipmentDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentDTOAssembler implements RepresentationModelAssembler<Equipment, EquipmentDTO> {
    @Override
    public EquipmentDTO toModel(Equipment entity) {
        EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .typeOfEquipment(entity.getTypeOfEquipment())
                .repairCost(entity.getRepairCost())
                .accessible(entity.getAccessible())
                .build();
        Link selfLink = linkTo(methodOn(EquipmentController.class).getEquipment(equipmentDTO.getId())).withSelfRel();
        equipmentDTO.add(selfLink);
        return equipmentDTO;
    }

    @Override
    public CollectionModel<EquipmentDTO> toCollectionModel(Iterable<? extends Equipment> entities) {
        CollectionModel<EquipmentDTO> equipmentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EquipmentController.class).getAllEquipments()).withSelfRel();
        equipmentDTOS.add(selfLink);
        return equipmentDTOS;
    }

    public CollectionModel<EquipmentDTO> toCollectionModel(Iterable<? extends Equipment> entities, Link link) {
        CollectionModel<EquipmentDTO> equipmentDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        equipmentDTOS.add(link);
        return equipmentDTOS;
    }
}
