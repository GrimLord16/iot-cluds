package com.ua.lviv.iot.exception;

public class RepairTransactionNotFoundException extends RuntimeException {
    public RepairTransactionNotFoundException(Integer id) {
        super("Could not find 'repair_transaction' with id=" + id);
    }
}

