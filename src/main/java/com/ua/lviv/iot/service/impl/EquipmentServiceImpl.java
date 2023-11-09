package com.ua.lviv.iot.service.impl;


import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.domain.EquipmentSet;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.EquipmentSetNotFoundException;
import com.ua.lviv.iot.repository.EquipmentRepository;
import com.ua.lviv.iot.repository.EquipmentSetRepository;
import com.ua.lviv.iot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    EquipmentSetRepository equipmentSetRepository;



    @Override
    public List<Equipment> findByName(String name) {
        return equipmentRepository.findEquipmentsByName(name);
    }

    @Override
    public List<Equipment> findByAccessibility(Byte accessible) {
        return equipmentRepository.findEquipmentsByIsAccessible(accessible);
    }


    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(Integer id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }

    @Transactional
    public Equipment create(Equipment entity) {
        equipmentRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, Equipment entity) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        equipment.setName(entity.getName());
        equipment.setRepairCost(entity.getRepairCost());
        equipment.setTypeOfEquipment(entity.getTypeOfEquipment());
        equipment.setAccessible(entity.getAccessible());
        equipmentRepository.save(entity);

    }

    @Transactional
    public void delete(Integer id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
//        for (Student student : studentService.findStudentByEquipmentId(id)) {
//            studentService.delete(student.getId());
//        }
        equipmentRepository.delete(equipment);
    }

    @Transactional
    public Equipment create(Equipment equipment, Integer equipmentSetId) {
        EquipmentSet equipmentSet = equipmentSetRepository.findById(equipmentSetId)
                .orElseThrow(() -> new EquipmentSetNotFoundException(equipmentSetId));
        equipment.setEquipmentSet(equipmentSet);
        equipmentRepository.save(equipment);
        return equipment;
    }

    @Override
    public void equipmentCursorProcedure() {
        equipmentRepository.equipmentCursorProcedure();
    }

}
