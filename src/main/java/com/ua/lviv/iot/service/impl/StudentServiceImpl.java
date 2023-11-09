package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.*;
import com.ua.lviv.iot.exception.*;
import com.ua.lviv.iot.repository.EquipmentRepository;
import com.ua.lviv.iot.repository.StudentCardRepository;
import com.ua.lviv.iot.repository.StudentRepository;
import com.ua.lviv.iot.service.RentService;
import com.ua.lviv.iot.service.RepairService;
import com.ua.lviv.iot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RentService rentService;

    @Autowired
    RepairService repairService;


    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    StudentCardRepository studentCardRepository;



    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
    }

    @Transactional
    public Student create(Student entity) {
        studentRepository.save(entity);
        return entity;
    }

    @Transactional
    public Student create(Student entity, Integer studentCardId) {
        StudentCard studentCard = studentCardRepository.findById(studentCardId)
                .orElseThrow(() -> new StudentCardNotFoundException(studentCardId));
        entity.setStudentCard(studentCard);
        studentRepository.save(entity);
        return entity;
    }
    @Transactional
    public void update(Integer id, Student entity) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());


        studentRepository.save(entity);
    }

    @Transactional
    public void update(Integer id, Student entity, Integer studentCardId) {
        StudentCard studentCard = studentCardRepository.findById(id)
                .orElseThrow(() -> new StudentCardNotFoundException(id));
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setStudentCard(studentCard);


        studentRepository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        for (Rent rent : rentService.findRentByStudentId(id)) {
            rentService.delete(rent.getId());
        }
        for (Repair repair : repairService.findRepairByStudentId(id)) {
            repairService.delete(repair.getId());
        }
        studentRepository.delete(student);
    }

    @Override
    public List<Student> findStudentByStudentCardId(Integer studentCardId) {
        return studentRepository.findStudentByStudentCardId(studentCardId);
    }

    @Transactional
    public Student removeEquipmentForStudent(Integer studentId, Integer equipmentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException(equipmentId));
        if (!student.getEquipments().contains(equipment)) throw new StudentHasNoEquipmentException(equipmentId, studentId);
        student.getEquipments().remove(equipment);
        studentRepository.save(student);
        return student;
    }

    @Transactional
    public Student addEquipmentForStudent(Integer studentId, Integer equipmentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException(equipmentId));
        if (student.getEquipments().contains(equipment)) throw new StudentHasEquipmentException(equipmentId, studentId);
        student.getEquipments().add(equipment);
        studentRepository.save(student);
        return student;
    }
    @Override
    public List<Equipment> findEquipmentsById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return student.getEquipments().stream().toList();
    }

//    @Override
//    public List<Student> findAllByStudentCardId(Integer id) {
//        return studentRepository.findAllByStudentCardId(id);
//    }

}
