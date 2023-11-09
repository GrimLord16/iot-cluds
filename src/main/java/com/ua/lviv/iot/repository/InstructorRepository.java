package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    @Procedure("instructor_insertion")
    Integer insertion( String email, String middlename, String name, String phoneNumber, String surname);

    @Procedure("instructor_equipment_set")
    Integer instructorEquipmentSet(Integer instructorId, Integer equipmentId);

    @Procedure("instructor_add_nonames")
    void addNonames();
}
