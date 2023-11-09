package com.ua.lviv.iot.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Integer id) {
        super("Could not find 'transaction' with id=" + id);
    }
}

