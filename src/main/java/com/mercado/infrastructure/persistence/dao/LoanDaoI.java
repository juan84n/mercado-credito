package com.mercado.infrastructure.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercado.infrastructure.persistence.entitites.LoanEntity;

@Repository
public interface LoanDaoI extends JpaRepository<LoanEntity, Long>{
	
	@Query("SELECT f FROM LoanEntity f WHERE LOWER(f.user_id) = LOWER(:user_id) and date > :startDate")
	List<LoanEntity> findByUserId(@Param("user_id") long user_id, @Param("startDate") Date startDate);
	
	@Query("SELECT f FROM LoanEntity f WHERE f.date >= :startDate AND f.date <= :endDate")
	List<LoanEntity> listLoanByDates(@Param("startDate") Date startDate, @Param("endDate") Date enddate);
}
