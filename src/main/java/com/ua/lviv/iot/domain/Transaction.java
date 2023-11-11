package com.ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "dbo")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "total_usd")
    private Integer totalUsd;

    @OneToOne(mappedBy = "transaction")
    private Rent rent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalUsd() {
        return totalUsd;
    }

    public void setTotalUsd(Integer totalUsd) {
        this.totalUsd = totalUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(totalUsd, that.totalUsd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalUsd);
    }

    public Rent getRentById() {
        return rent;
    }

    public void setRentsById(Rent rent) {
        this.rent = rent;
    }
}
