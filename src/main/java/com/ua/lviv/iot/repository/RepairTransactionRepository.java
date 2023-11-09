package com.ua.lviv.iot.repository;

import com.ua.lviv.iot.domain.RepairTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepairTransactionRepository extends JpaRepository<RepairTransaction, Integer> {

}
