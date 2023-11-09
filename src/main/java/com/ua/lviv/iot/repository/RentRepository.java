package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    List<Rent> findAllByBookingTimeIsBetweenAndReturnTimeIsBetween(Timestamp from, Timestamp to, Timestamp endFrom, Timestamp endTo);
    List<Rent> findRentByBookingTime(Timestamp bookingTime);
    List<Rent> findRentByReturnTime(Timestamp returnTime);

    List<Rent> findRentByStudentId(Integer studentId);
    Rent findRentByTransactionId(Integer transactionId);
}
