package com.ua.lviv.iot.service;

import com.ua.lviv.iot.domain.Instructor;

public interface InstructorService extends GeneralService<Instructor, Integer>{


    Integer insertion(Instructor instructor);

    Integer instructorEquipmentSet(Integer instructorId, Integer equipmentSetId);

    void addNonames();
}
