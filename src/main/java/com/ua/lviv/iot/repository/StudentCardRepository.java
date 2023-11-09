package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Integer> {
    List<StudentCard> findStudentCardByPhoneNumber(String phoneNumber);
    List<StudentCard> findStudentCardBySurname(String surname);

}
