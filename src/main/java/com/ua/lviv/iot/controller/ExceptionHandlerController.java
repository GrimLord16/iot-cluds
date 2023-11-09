package com.ua.lviv.iot.controller;

import com.ua.lviv.iot.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(EquipmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String equipmentNotFoundHandler(EquipmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EquipmentSetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String equipmentSetNotFoundHandler(EquipmentSetNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String rentNotFoundHandler(RentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RepairNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String repairNotFoundHandler(RepairNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentNotFoundHandler(StudentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StudentCardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentCardNotFoundHandler(StudentCardNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RepairTransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String repairTransactionNotFoundHandler(RepairTransactionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String transactionNotFoundHandler(TransactionNotFoundException ex) {
        return ex.getMessage();
    }

}
