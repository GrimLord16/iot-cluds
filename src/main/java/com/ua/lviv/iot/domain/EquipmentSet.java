package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "equipment_set", schema = "mydb", catalog = "")
public class EquipmentSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "is_full")
    private Byte isFull;
    @Basic
    @Column(name = "instructor_id")
    private Integer instructorId;

    @Basic
    @Column(name = "available")
    private Byte available;
    @OneToMany(mappedBy = "equipmentSet")
    private Set<Equipment> equipments;

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

    public Byte getIsFull() {
        return isFull;
    }

    public void setIsFull(Byte isFull) {
        this.isFull = isFull;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentSet that = (EquipmentSet) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(isFull, that.isFull) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isFull, available);
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }
}
