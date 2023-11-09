package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.domain.EquipmentSet;
import com.ua.lviv.iot.domain.Instructor;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.EquipmentSetNotFoundException;
import com.ua.lviv.iot.exception.InstructorNotFoundException;
import com.ua.lviv.iot.repository.EquipmentRepository;
import com.ua.lviv.iot.repository.EquipmentSetRepository;
import com.ua.lviv.iot.repository.InstructorRepository;
import com.ua.lviv.iot.service.EquipmentService;
import com.ua.lviv.iot.service.EquipmentSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentSetServiceImpl implements EquipmentSetService {

    @Autowired
    EquipmentSetRepository equipmentSetRepository;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<EquipmentSet> findByName(String name) {
        return equipmentSetRepository.findEquipmentSetsByName(name);
    }

    @Override
    public List<EquipmentSet> findByAvailable(Byte available) {
        return equipmentSetRepository.findEquipmentSetsByAvailable(available);
    }

    @Override
    public List<EquipmentSet> findAll() {
        return equipmentSetRepository.findAll();
    }

    @Override
    public EquipmentSet findById(Integer id) {
        return equipmentSetRepository.findById(id).orElseThrow(() -> new EquipmentSetNotFoundException(id));
    }

    @Transactional
    public EquipmentSet create(EquipmentSet entity) {
        equipmentSetRepository.save(entity);
        return entity;
    }


    @Transactional
    public void update(Integer id, EquipmentSet entity) {
        EquipmentSet equipmentSet = equipmentSetRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        equipmentSet.setName(entity.getName());
        equipmentSet.setIsFull(entity.getIsFull());
        equipmentSet.setAvailable(entity.getAvailable());
        equipmentSetRepository.save(entity);

    }

    @Transactional
    public void delete(Integer id) {
        EquipmentSet equipmentSet = equipmentSetRepository.findById(id)
                .orElseThrow(() -> new EquipmentSetNotFoundException(id));
        for (Equipment equipment : equipmentRepository.findEquipmentsByEquipmentSetId(id)) {
            equipment.setEquipmentSet(null);
        }
        equipmentSetRepository.delete(equipmentSet);
    }

    @Override
    public List<EquipmentSet> findEquipmentSetsByInstructorId(Integer instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(instructorId));
        return equipmentSetRepository.findByInstructorId(instructorId);
    }
}
