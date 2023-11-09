package com.ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "repair", collectionRelation = "repairs")

public class RepairDTO extends  RepresentationModel<RepairDTO>{

    private final Integer id;
    private final Integer repairCost;
    private final Timestamp repairDoneDate;
    private final Byte returnState;
    private final String damageType;

}
