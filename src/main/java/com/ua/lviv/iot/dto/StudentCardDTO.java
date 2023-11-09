package com.ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "student_card", collectionRelation = "student_cards")

public class StudentCardDTO extends  RepresentationModel<StudentCardDTO>{

    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final Date birthDate;
    private final String phoneNumber;
    private final String address;
    private final String password;

}
