package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.EquipmentSetController;
import com.ua.lviv.iot.domain.EquipmentSet;
import com.ua.lviv.iot.dto.EquipmentSetDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentSetDTOAssembler implements RepresentationModelAssembler<EquipmentSet, EquipmentSetDTO> {
    @Override
    public EquipmentSetDTO toModel(EquipmentSet entity) {
        EquipmentSetDTO equipmentSetDTO = EquipmentSetDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isFull(entity.getIsFull())
                .available(entity.getAvailable())
                .build();
        Link selfLink = linkTo(methodOn(EquipmentSetController.class).getEquipmentSet(equipmentSetDTO.getId())).withSelfRel();
        equipmentSetDTO.add(selfLink);
        return equipmentSetDTO;
    }

    @Override
    public CollectionModel<EquipmentSetDTO> toCollectionModel(Iterable<? extends EquipmentSet> entities) {
        CollectionModel<EquipmentSetDTO> equipmentSetDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EquipmentSetController.class).getAllEquipmentSets()).withSelfRel();
        equipmentSetDTOS.add(selfLink);
        return equipmentSetDTOS;
    }

    public CollectionModel<EquipmentSetDTO> toCollectionModel(Iterable<? extends EquipmentSet> entities, Link link) {
        CollectionModel<EquipmentSetDTO> equipmentSetDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        equipmentSetDTOS.add(link);
        return equipmentSetDTOS;
    }
}
