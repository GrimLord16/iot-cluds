package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Transaction;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.StudentNotFoundException;
import com.ua.lviv.iot.exception.TransactionNotFoundException;
import com.ua.lviv.iot.repository.TransactionRepository;
import com.ua.lviv.iot.service.RentService;
import com.ua.lviv.iot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RentService rentService;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(()-> new TransactionNotFoundException(id));
    }

    @Transactional
    public Transaction create(Transaction entity) {
        transactionRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, Transaction entity) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        transaction.setTotalUsd(entity.getTotalUsd());


        transactionRepository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        Rent rent = rentService.findRentByTransactionId(id);
        if(rent!=null) {
            rent.setTransaction(null);
            rentService.update(rent.getId(), rent);
        }

        transactionRepository.delete(transaction);
    }
}
