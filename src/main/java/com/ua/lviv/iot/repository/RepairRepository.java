package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {

    List<Repair> findRepairByDamageType(String damageType);
    List<Repair> findRepairByRepairCost(Integer repairCost);
    List<Repair> findRepairByReturnState(Byte returnState);
    List<Repair> findRepairByRepairDoneDate(Timestamp repairDoneDate);

    List<Repair> findRepairByStudentId(Integer studentId);
    Repair findRepairByRepairTransactionId(Integer repairTransactionId);
}
