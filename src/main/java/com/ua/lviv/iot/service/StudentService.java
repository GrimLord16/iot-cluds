package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.Equipment;
import com.ua.lviv.iot.domain.Student;

import java.util.List;

public interface StudentService extends GeneralService<Student, Integer>{

    List<Student> findStudentByStudentCardId(Integer studentCardId);
    Student removeEquipmentForStudent(Integer studentId, Integer equipmentId);

    Student addEquipmentForStudent(Integer studentId, Integer equipmentId);
    List<Equipment> findEquipmentsById(Integer id);

    Student create(Student entity, Integer studentCardId);
    void update(Integer id, Student entity, Integer studentCardId);


}
