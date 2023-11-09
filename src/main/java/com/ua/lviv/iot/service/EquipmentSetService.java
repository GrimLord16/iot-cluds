package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.EquipmentSet;

import java.util.List;

public interface EquipmentSetService extends GeneralService<EquipmentSet, Integer>{

    List<EquipmentSet> findByName(String name);
    List<EquipmentSet> findByAvailable(Byte available);

    List<EquipmentSet> findEquipmentSetsByInstructorId(Integer instructorId);
}
