package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.Instructor;
import com.ua.lviv.iot.exception.InstructorNotFoundException;
import com.ua.lviv.iot.repository.InstructorRepository;
import com.ua.lviv.iot.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(Integer id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
    }

    @Transactional
    public Instructor create(Instructor entity) {
        instructorRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, Instructor entity) {

        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        instructor.setName(entity.getName());
        instructor.setSurname(entity.getSurname());
        instructor.setMiddleName(entity.getMiddleName());
        instructor.setEmail(entity.getEmail());
        instructor.setPhoneNumber(entity.getPhoneNumber());


        instructorRepository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {

        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        instructorRepository.delete(instructor);
    }

    @Override
    public Integer insertion(Instructor instructor) {

        String name = instructor.getName();
        String surname = instructor.getSurname();
        String middleName = instructor.getMiddleName();
        String email = instructor.getEmail();
        String phoneNumber = instructor.getPhoneNumber();
        return instructorRepository.insertion( email, middleName, name, phoneNumber, surname);
    }

    @Override
    public Integer instructorEquipmentSet(Integer instructorId, Integer equipmentSetId) {
        return instructorRepository.instructorEquipmentSet(instructorId, equipmentSetId);
    }

    @Override
    public void addNonames() {
        instructorRepository.addNonames();
    }


}
