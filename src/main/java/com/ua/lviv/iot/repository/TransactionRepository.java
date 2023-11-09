package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.Rent;
import com.ua.lviv.iot.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


}
