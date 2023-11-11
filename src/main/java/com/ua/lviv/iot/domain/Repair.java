package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "repair", schema = "dbo")
public class Repair {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "repair_cost")
    private Integer repairCost;
    @Basic
    @Column(name = "repair_done_date")
    private Timestamp repairDoneDate;
    @Basic
    @Column(name = "return_state")
    private Byte returnState;
    @Basic
    @Column(name = "damage_type")
    private String damageType;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;
    @OneToOne
    @JoinColumn(name = "repair_transaction_id", referencedColumnName = "id", nullable = true)
    private RepairTransaction repairTransaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Integer repairCost) {
        this.repairCost = repairCost;
    }

    public Timestamp getRepairDoneDate() {
        return repairDoneDate;
    }

    public void setRepairDoneDate(Timestamp repairDoneDate) {
        this.repairDoneDate = repairDoneDate;
    }

    public Byte getReturnState() {
        return returnState;
    }

    public void setReturnState(Byte returnState) {
        this.returnState = returnState;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return Objects.equals(id, repair.id) && Objects.equals(repairCost, repair.repairCost) && Objects.equals(repairDoneDate, repair.repairDoneDate) && Objects.equals(returnState, repair.returnState) && Objects.equals(damageType, repair.damageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repairCost, repairDoneDate, returnState, damageType);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RepairTransaction getRepairTransaction() {
        return repairTransaction;
    }

    public void setRepairTransaction(RepairTransaction repairTransaction) {
        this.repairTransaction = repairTransaction;
    }
}
