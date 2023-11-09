package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    List<Equipment> findEquipmentByTypeOfEquipment(String typeOfEquipment);

    List<Equipment> findEquipmentsByName(String name);

    List<Equipment> findEquipmentsByIsAccessible(Byte accessible);

    List<Equipment> findEquipmentsByEquipmentSetId(Integer equipmentSetId);

    @Procedure("select_max_equipment_repair_cost")
    Double getMaxEquipmentRepairCost();

    @Procedure("equipment_cursor_procedure")
    void equipmentCursorProcedure();

}
