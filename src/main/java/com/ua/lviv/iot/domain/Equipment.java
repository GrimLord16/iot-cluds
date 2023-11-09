package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Equipment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "repair_cost")
    private Integer repairCost;
    @Basic
    @Column(name = "type_of_equipment")
    private String typeOfEquipment;
    @Basic
    @Column(name = "is_accessible")
    private Byte isAccessible;
    @ManyToOne
    @JoinColumn(name = "equipment_set_id", referencedColumnName = "id", nullable = true)
    private EquipmentSet equipmentSet;
    @ManyToMany(mappedBy = "equipments")
    private Set<Student> students;

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

    public Integer getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Integer repairCost) {
        this.repairCost = repairCost;
    }

    public String getTypeOfEquipment() {
        return typeOfEquipment;
    }

    public void setTypeOfEquipment(String typeOfEquipment) {
        this.typeOfEquipment = typeOfEquipment;
    }

    public Byte getAccessible() {
        return isAccessible;
    }

    public void setAccessible(Byte accessible) {
        this.isAccessible = accessible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) && Objects.equals(name, equipment.name) && Objects.equals(repairCost, equipment.repairCost) && Objects.equals(typeOfEquipment, equipment.typeOfEquipment) &&  Objects.equals(isAccessible, equipment.isAccessible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, repairCost, typeOfEquipment, isAccessible);
    }

    public EquipmentSet getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(EquipmentSet equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
