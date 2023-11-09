package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.Equipment;

import java.util.List;

public interface EquipmentService extends GeneralService<Equipment, Integer>{
    List<Equipment> findByName(String name);

    List<Equipment> findByAccessibility(Byte accessible);

    Equipment create(Equipment equipment, Integer equipmentSetId);

    void equipmentCursorProcedure();

}
