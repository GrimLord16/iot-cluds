package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.domain.EquipmentSet;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentSetRepository extends JpaRepository<EquipmentSet, Integer> {

    List<EquipmentSet> findEquipmentSetsByAvailable(Byte available);
    List<EquipmentSet> findEquipmentSetsByName(String name);
    List<EquipmentSet> findEquipmentSetsByIsFull(Byte isFull);

    List<EquipmentSet> findByInstructorId(Integer instructorId);



}
