package com.ua.lviv.iot.exception;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(Integer id) {
        super("Could not find 'instructor' with id=" + id);
    }
}
