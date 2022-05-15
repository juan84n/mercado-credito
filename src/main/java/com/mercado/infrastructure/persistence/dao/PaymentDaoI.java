package com.mercado.infrastructure.persistence.dao;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercado.infrastructure.persistence.entitites.BalanceEntity;
import com.mercado.infrastructure.persistence.entitites.PaymentEntity;

public interface PaymentDaoI extends JpaRepository<PaymentEntity, Long> {

	List<PaymentEntity> getListPaymentsByLoanId(long loan_id);
	
	@Query("SELECT new com.mercado.infrastructure.persistence.entitites.BalanceEntity(SUM(f.amount), f.loan)"
			+ " FROM PaymentEntity f WHERE f.loan.id = :loan_id AND f.date <= :endDate GROUP BY f.loan.amount")
	List<BalanceEntity> getPaymentsByLoanId(@Param("loan_id") long loan_id, @Param("endDate") Timestamp endDate);
	
	@Query("SELECT new com.mercado.infrastructure.persistence.entitites.BalanceEntity(SUM(f.amount), f.loan)"
			+ " FROM PaymentEntity f RIGHT JOIN LoanEntity l ON(f.loan.id = l.id ) WHERE f.date <= :endDate GROUP BY f.loan.amount, l.id")
	List<BalanceEntity> getPaymentsByDate(@Param("endDate") Timestamp enddate);
	
	@Query("SELECT new com.mercado.infrastructure.persistence.entitites.BalanceEntity(SUM(f.amount), f.loan)"
			+ " FROM PaymentEntity f, LoanEntity l WHERE f.loan.id = l.id AND l.target = :target GROUP BY f.loan.amount, l.id")
	List<BalanceEntity> getPaymentsByTarget(@Param("target") String target);
	
	@Query("SELECT new com.mercado.infrastructure.persistence.entitites.BalanceEntity(SUM(f.amount), f.loan)"
			+ " FROM PaymentEntity f, LoanEntity l WHERE f.loan.id = l.id AND l.target = :target  AND f.date <= :endDate GROUP BY f.loan.amount, l.id")
	List<BalanceEntity> getPaymentsByTargetAndDate(@Param("target") String target, @Param("endDate") Timestamp endDate);
}
