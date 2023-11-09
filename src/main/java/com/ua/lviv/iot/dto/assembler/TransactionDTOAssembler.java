package com.ua.lviv.iot.dto.assembler;

import com.ua.lviv.iot.controller.TransactionController;
import com.ua.lviv.iot.domain.Transaction;
import com.ua.lviv.iot.dto.TransactionDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionDTOAssembler implements RepresentationModelAssembler<Transaction, TransactionDTO> {
    @Override
    public TransactionDTO toModel(Transaction entity) {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .id(entity.getId())
                .totalUSD(entity.getTotalUsd())
                .build();
        Link selfLink = linkTo(methodOn(TransactionController.class).getTransaction(transactionDTO.getId())).withSelfRel();
        transactionDTO.add(selfLink);
        return transactionDTO;
    }

    @Override
    public CollectionModel<TransactionDTO> toCollectionModel(Iterable<? extends Transaction> entities) {
        CollectionModel<TransactionDTO> transactionDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TransactionController.class).getAllTransactions()).withSelfRel();
        transactionDTOS.add(selfLink);
        return transactionDTOS;
    }

    public CollectionModel<TransactionDTO> toCollectionModel(Iterable<? extends Transaction> entities, Link link) {
        CollectionModel<TransactionDTO> transactionDTOS = RepresentationModelAssembler.super.toCollectionModel(entities);
        transactionDTOS.add(link);
        return transactionDTOS;
    }
}
