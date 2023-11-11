package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "repair_transaction", schema = "dbo")
public class RepairTransaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "repair_total_usd")
    private Integer repairTotalUsd;
    @OneToOne(mappedBy = "repairTransaction")
    private Repair repair;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepairTotalUsd() {
        return repairTotalUsd;
    }

    public void setRepairTotalUsd(Integer repairTotalUsd) {
        this.repairTotalUsd = repairTotalUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairTransaction that = (RepairTransaction) o;
        return Objects.equals(id, that.id) && Objects.equals(repairTotalUsd, that.repairTotalUsd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repairTotalUsd);
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
