package com.ua.lviv.iot.exception;


public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(Integer id) {
        super("Could not find 'equipment' with id=" + id);
    }
}

