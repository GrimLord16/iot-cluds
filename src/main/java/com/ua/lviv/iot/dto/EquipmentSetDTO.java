package com.ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "equipment_set", collectionRelation = "equipment_sets")

public class EquipmentSetDTO extends  RepresentationModel<EquipmentSetDTO>{

    private final Integer id;
    private final String name;
    private final Byte isFull;
    private final Byte available;

}