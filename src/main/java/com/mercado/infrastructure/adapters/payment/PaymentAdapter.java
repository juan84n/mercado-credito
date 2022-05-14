package com.mercado.infrastructure.adapters.payment;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.repository.PaymentRepositoryI;
import com.mercado.infrastructure.persistence.dao.PaymentDaoI;
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
	public double getTotalPaymentByLoanId(long loan_id, Timestamp date) {
		return this.paymentDao.getBalanceByLoanId(loan_id, date)
				.orElse((double)0);
	}


	@Override
	public double getBalanceByTarget(String target, Timestamp date) {
		// TODO Auto-generated method stub
		return 0;
	}


}
