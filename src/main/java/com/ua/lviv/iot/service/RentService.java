package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.Rent;

import java.util.List;

public interface RentService extends GeneralService<Rent, Integer>{

    List<Rent> findRentByStudentId(Integer studentId);
    Rent findRentByTransactionId(Integer transactionId);

    Rent create(Rent entity, Integer studentId);

    void paymentDone(Integer id, Integer transactionId);

    void update(Integer id, Rent entity, Integer studentId);
}
