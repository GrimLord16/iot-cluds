package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.RepairTransactionController;
import com.ua.lviv.iot.domain.RepairTransaction;
import com.ua.lviv.iot.dto.RepairTransactionDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepairTransactionDTOAssembler implements RepresentationModelAssembler<RepairTransaction, RepairTransactionDTO> {
    @Override
    public RepairTransactionDTO toModel(RepairTransaction entity) {
        RepairTransactionDTO repairTransactionDTO = RepairTransactionDTO.builder()
                .id(entity.getId())
                .repairTotalUSD(entity.getRepairTotalUsd())
                .build();
        Link selfLink = linkTo(methodOn(RepairTransactionController.class).getRepairTransaction(repairTransactionDTO.getId())).withSelfRel();
        repairTransactionDTO.add(selfLink);
        return repairTransactionDTO;
    }

    @Override
    public CollectionModel<RepairTransactionDTO> toCollectionModel(Iterable<? extends RepairTransaction> entities) {
        CollectionModel<RepairTransactionDTO> repairTransactionDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RepairTransactionController.class).getAllRepairTransactions()).withSelfRel();
        repairTransactionDTOS.add(selfLink);
        return repairTransactionDTOS;
    }

    public CollectionModel<RepairTransactionDTO> toCollectionModel(Iterable<? extends RepairTransaction> entities, Link link) {
        CollectionModel<RepairTransactionDTO> repairTransactionDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        repairTransactionDTOS.add(link);
        return repairTransactionDTOS;
    }
}
