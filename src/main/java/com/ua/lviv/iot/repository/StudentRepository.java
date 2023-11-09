package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentByStudentCardId(Integer studentCardId);
}
