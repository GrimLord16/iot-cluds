package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.Repair;

import java.util.List;

public interface RepairService extends GeneralService<Repair, Integer>{

    List<Repair> findRepairByStudentId(Integer studentId);
    Repair findRepairByRepairTransactionId(Integer repairTransactionId);

    void paymentDone(Integer id, Integer transactionId);
    void update(Integer id, Repair entity, Integer studentId);
    Repair create(Repair entity, Integer studentId);
}
