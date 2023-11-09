package com.ua.lviv.iot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TransactionLog {

    @Id
    @Column(name = "transaction_id")
    private int id;

    @Column(name = "total_usd", length = 45)
    private Double totalUSD;

    @Column(name = "action", length = 45, nullable = false)
    private String action;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public Double getTotalUSD() {
        return totalUSD;
    }
    public void setTotalUSD(Double totalUSD) {
        this.totalUSD = totalUSD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionLog transactionLog = (TransactionLog) o;
        return id == transactionLog.id && Objects.equals(totalUSD, transactionLog.totalUSD) && Objects.equals(action, transactionLog.action) && Objects.equals(timestamp, transactionLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalUSD, action, timestamp);
    }

}
