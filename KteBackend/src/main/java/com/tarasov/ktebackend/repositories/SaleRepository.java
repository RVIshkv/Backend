package com.tarasov.ktebackend.repositories;

import com.tarasov.ktebackend.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select distinct s from Sale s LEFT JOIN FETCH s.positions where s.clientId = :clientId ")
    List<Sale> findSalesByClientId(@Param("clientId") Long clientId);

    @Query("select distinct s from Sale s LEFT JOIN FETCH s.positions p where p.productId = :productId ")
    List<Sale> findSalesByProductId(@Param("productId") Long productId);

    @Modifying
    @Query(value="ALTER SEQUENCE SEQ_CHECK_NUMBER RESTART WITH 100", nativeQuery = true)
    void reset();
}
