package com.ua.lviv.iot.exception;

public class RepairNotFoundException extends RuntimeException {
    public RepairNotFoundException(Integer id) {
        super("Could not find 'repair' with id=" + id);
    }
}

