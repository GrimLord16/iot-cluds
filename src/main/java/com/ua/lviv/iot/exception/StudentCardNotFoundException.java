package com.ua.lviv.iot.exception;

public class StudentCardNotFoundException extends RuntimeException {
    public StudentCardNotFoundException(Integer id) {
        super("Could not find 'student_card' with id=" + id);
    }
}
