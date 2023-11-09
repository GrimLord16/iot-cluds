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
@Relation(itemRelation = "instructor", collectionRelation = "instructors")

public class InstructorDTO extends  RepresentationModel<InstructorDTO>{

    private final Integer id;
    private final String email;
    private final String middleName;
    private final String name;
    private final String phoneNumber;
    private final String surname;

}
