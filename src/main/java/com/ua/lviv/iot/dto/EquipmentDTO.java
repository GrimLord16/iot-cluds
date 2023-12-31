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
@Relation(itemRelation = "equipment", collectionRelation = "equipments")

public class EquipmentDTO extends  RepresentationModel<EquipmentDTO>{

        private final Integer id;
        private final String name;
        private final Integer repairCost;
        private final String typeOfEquipment;
        private final Byte accessible;

}
