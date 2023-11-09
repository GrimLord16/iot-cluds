package com.ua.lviv.iot.exception;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(Integer id) {
        super("Could not find 'rent' with id=" + id);
    }
}
