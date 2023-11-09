package com.ua.lviv.iot.exception;

public class EquipmentSetNotFoundException extends RuntimeException {
    public EquipmentSetNotFoundException(Integer id) {
        super("Could not find 'equipment_set' with id=" + id);
    }
}
