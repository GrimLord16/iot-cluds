package com.ua.lviv.iot.service.impl;

import com.ua.lviv.iot.domain.Student;
import com.ua.lviv.iot.domain.StudentCard;
import com.ua.lviv.iot.exception.EquipmentNotFoundException;
import com.ua.lviv.iot.exception.StudentCardNotFoundException;
import com.ua.lviv.iot.repository.StudentCardRepository;
import com.ua.lviv.iot.service.StudentCardService;
import com.ua.lviv.iot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCardServiceImpl implements StudentCardService {

    @Autowired
    StudentCardRepository studentCardRepository;

    @Autowired
    StudentService studentService;

    @Override
    public List<StudentCard> findAll() {
        return studentCardRepository.findAll();
    }

    @Override
    public StudentCard findById(Integer id) {
        return studentCardRepository.findById(id).orElseThrow(()-> new StudentCardNotFoundException(id));
    }

    @Transactional
    public StudentCard create(StudentCard entity) {
        studentCardRepository.save(entity);
        return entity;
    }

    @Transactional
    public void update(Integer id, StudentCard entity) {
        StudentCard studentCard = studentCardRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
        studentCard.setName(entity.getName());
        studentCard.setSurname(entity.getSurname());
        studentCard.setEmail(entity.getEmail());
        studentCard.setAddress(entity.getAddress());
        studentCard.setPhoneNumber(entity.getPhoneNumber());
        studentCard.setBirthDate(entity.getBirthDate());
        studentCard.setPassword(entity.getPassword());


        studentCardRepository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        StudentCard studentCard = studentCardRepository.findById(id)
                .orElseThrow(() -> new StudentCardNotFoundException(id));
        for (Student student : studentService.findStudentByStudentCardId(id)) {
            studentService.delete(student.getId());
        }
        studentCardRepository.delete(studentCard);
    }
}
