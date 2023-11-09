package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.EquipmentSet;
import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Student;
import com.ua.lviv.iot.domain.Transaction;
import com.ua.lviv.iot.exception.*;
import com.ua.lviv.iot.repository.RentRepository;
import com.ua.lviv.iot.repository.StudentRepository;
import com.ua.lviv.iot.repository.TransactionRepository;
import com.ua.lviv.iot.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    RentRepository rentRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent findById(Integer id) {
        return rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(id));
    }

    @Transactional
    public Rent create(Rent entity) {
        rentRepository.save(entity);
        return entity;
    }

    @Transactional
    public Rent create(Rent entity, Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        entity.setStudent(student);
        rentRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, Rent entity) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rent.setBookingTime(entity.getBookingTime());
        rent.setReturnTime(entity.getReturnTime());
        rentRepository.save(entity);

    }

    @Transactional
    public void paymentDone(Integer id, Integer transactionId) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        rent.setTransaction(transaction);
        rentRepository.save(rent);

    }

    @Transactional
    public void update(Integer id, Rent entity, Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        rent.setBookingTime(entity.getBookingTime());
        rent.setReturnTime(entity.getReturnTime());
        rent.setStudent(student);
        rentRepository.save(entity);

    }

    @Transactional
    public void delete(Integer id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rentRepository.delete(rent);
    }

    @Override
    public List<Rent> findRentByStudentId(Integer id) {
        return rentRepository.findRentByStudentId(id);
    }

    @Override
    public Rent findRentByTransactionId(Integer transactionId) {
        return rentRepository.findRentByTransactionId(transactionId);
    }
}
