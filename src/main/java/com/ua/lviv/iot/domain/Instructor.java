package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Instructor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Basic
    @Column(name = "surname", length = 45, nullable = false)
    private String surname;

    @Basic
    @Column(name = "middlename", length = 45)
    private String middleName;

    @Basic
    @Column(name = "email", length = 50)
    private String email;

    @Basic
    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor instructor = (Instructor) o;
        return Objects.equals(id, instructor.id) && Objects.equals(name, instructor.name) && Objects.equals(surname, instructor.surname) && Objects.equals(middleName, instructor.middleName) && Objects.equals(email, instructor.email) && Objects.equals(phoneNumber, instructor.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, middleName, email, phoneNumber);
    }
}
