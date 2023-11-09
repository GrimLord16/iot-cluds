package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Repair;
import com.ua.lviv.iot.domain.RepairTransaction;
import com.ua.lviv.iot.domain.Transaction;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.RepairTransactionNotFoundException;
import com.ua.lviv.iot.exception.StudentNotFoundException;
import com.ua.lviv.iot.repository.RepairTransactionRepository;
import com.ua.lviv.iot.service.RepairService;
import com.ua.lviv.iot.service.RepairTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepairTransactionServiceImpl implements RepairTransactionService {

    @Autowired
    RepairTransactionRepository repairTransactionRepository;
    @Autowired
    RepairService repairService;

    @Override
    public List<RepairTransaction> findAll() {
        return repairTransactionRepository.findAll();
    }

    @Override
    public RepairTransaction findById(Integer id) {
        return repairTransactionRepository.findById(id).orElseThrow(() -> new RepairTransactionNotFoundException(id));
    }

    @Transactional
    public RepairTransaction create(RepairTransaction entity) {
        repairTransactionRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, RepairTransaction entity) {
        RepairTransaction repairTransaction = repairTransactionRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        repairTransaction.setRepairTotalUsd(entity.getRepairTotalUsd());

        repairTransactionRepository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        RepairTransaction repairTransaction = repairTransactionRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        Repair repair = repairService.findRepairByRepairTransactionId(id);
        if(repair!=null) {
            repair.setRepairTransaction(null);
            repairService.update(repair.getId(), repair);
        }

        repairTransactionRepository.delete(repairTransaction);
    }
}
