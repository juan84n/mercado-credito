package com.mercado.infrastructure.persistence.dao;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercado.infrastructure.persistence.entitites.PaymentEntity;

public interface PaymentDaoI extends JpaRepository<PaymentEntity, Long> {

	List<PaymentEntity> getListPaymentsByLoanId(long loan_id);
	
	@Query("SELECT SUM(f.amount) FROM PaymentEntity f WHERE f.loan.id = :loan_id AND f.date <= :endDate")
	Optional<Double> getBalanceByLoanId(@Param("loan_id") long loan_id, @Param("endDate") Timestamp enddate);
}
