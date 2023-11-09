package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.*;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.RepairNotFoundException;
import com.ua.lviv.iot.exception.StudentNotFoundException;
import com.ua.lviv.iot.exception.TransactionNotFoundException;
import com.ua.lviv.iot.repository.RepairRepository;
import com.ua.lviv.iot.repository.RepairTransactionRepository;
import com.ua.lviv.iot.repository.StudentRepository;
import com.ua.lviv.iot.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    RepairRepository repairRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RepairTransactionRepository repairTransactionRepository;

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public Repair findById(Integer id) {
        return repairRepository.findById(id).orElseThrow(() -> new RepairNotFoundException(id));
    }

    @Transactional
    public Repair create(Repair entity) {
        repairRepository.save(entity);
        return entity;
    }

    @Transactional
    public Repair create(Repair entity, Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        entity.setStudent(student);
        repairRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, Repair entity) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RepairNotFoundException(id));
        repair.setRepairCost(entity.getRepairCost());
        repair.setRepairDoneDate(entity.getRepairDoneDate());
        repair.setDamageType(entity.getDamageType());
        repair.setReturnState(entity.getReturnState());

        repairRepository.save(entity);


    }

    @Transactional
    public void paymentDone(Integer id, Integer transactionId) {
        RepairTransaction repairTransaction = repairTransactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        repair.setRepairTransaction(repairTransaction);

        repairRepository.save(repair);

    }

    @Transactional
    public void update(Integer id, Repair entity, Integer studentId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RepairNotFoundException(id));
        repair.setRepairCost(entity.getRepairCost());
        repair.setRepairDoneDate(entity.getRepairDoneDate());
        repair.setDamageType(entity.getDamageType());
        repair.setReturnState(entity.getReturnState());
        repair.setStudent(student);

        repairRepository.save(entity);


    }

    @Transactional
    public void delete(Integer id) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new RepairNotFoundException(id));
        repairRepository.delete(repair);
    }

    @Override
    public List<Repair> findRepairByStudentId(Integer studentId) {
        return repairRepository.findRepairByStudentId(studentId);
    }

    @Override
    public Repair findRepairByRepairTransactionId(Integer repairTransactionId) {
        return repairRepository.findRepairByRepairTransactionId(repairTransactionId);
    }

}
