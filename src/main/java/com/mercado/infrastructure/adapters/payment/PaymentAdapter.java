package com.mercado.infrastructure.adapters.payment;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Balance;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.repository.PaymentRepositoryI;
import com.mercado.infrastructure.persistence.dao.PaymentDaoI;
import com.mercado.infrastructure.persistence.entitites.BalanceEntity;
import com.mercado.infrastructure.persistence.entitites.PaymentEntity;

@Service
public class PaymentAdapter implements PaymentRepositoryI {

	@Autowired
	PaymentDaoI paymentDao;
	
	
	@Override
	public ResponsePayment doPayment(Payment payment, double debt) {
		
		
		PaymentEntity paymentEntity = paymentDao.save(PaymentTransformer
				.paymentToPaymentEntity(payment));
		
		ResponsePayment response = new ResponsePayment();
		response.setLoan_id(paymentEntity.getLoan().getId());
		response.setDebt(debt);
		response.setId(paymentEntity.getId());
		
		return response;
	}


	@Override
	public List<Payment> getListPayments(long loan_id) {
		return this.paymentDao.getListPaymentsByLoanId(loan_id)
				.stream()
				.map(PaymentTransformer::paymentEntityToPayment)
				.collect(Collectors.toList());
	}


	@Override
	public Balance getPaymentsByLoanId(long loan_id, Timestamp date) {
		List<BalanceEntity> balanceEntity = paymentDao.getPaymentsByLoanId(loan_id, date);
		if(balanceEntity.size() == 0) {
			throw new BusinessException(Status.NOT_FOUND.getCode(), "No hubo resultados");
		}
		Balance balance  = BalanceTransfomer.balanceEntityToBalance(balanceEntity.get(0));
		return balance;
	}


	@Override
	public List<Balance> getBalanceByTargetOrDate(String target, Timestamp date) {
		
		boolean isDate = date != null;
		boolean isTarget = target != null && !target.equals("");
		
		List<BalanceEntity> listBalance = null;
				
		if(isDate && !isTarget) {
			listBalance = paymentDao.getPaymentsByDate(date);
			if(listBalance.size() == 0) {
				throw new BusinessException(Status.NOT_FOUND.getCode(), "No hubo resultados");
			}
			
		}
		
		if(isTarget && !isDate) {
			listBalance = paymentDao.getPaymentsByTarget(target);
			if(listBalance.size() == 0) {
				throw new BusinessException(Status.NOT_FOUND.getCode(), "No hubo resultados");
			}
			
		}
		
		if(isTarget && isDate) {
			listBalance = paymentDao.getPaymentsByTargetAndDate(target, date);
			if(listBalance.size() == 0) {
				throw new BusinessException(Status.NOT_FOUND.getCode(), "No hubo resultados");
			}
			
			
		}
		
		return listBalance.stream().map(BalanceTransfomer::balanceEntityToBalance)
				.collect(Collectors.toList());
	}
	
}
