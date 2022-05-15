package com.mercado.domain.repository;

import java.sql.Timestamp;
import java.util.List;

import com.mercado.domain.models.Balance;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;

public interface PaymentRepositoryI {
	public List<Payment> getListPayments(long loan_id);
	public ResponsePayment doPayment(Payment payment, double debt);
	public Balance getPaymentsByLoanId(long loan_id, Timestamp date);
	public List<Balance> getBalanceByTargetOrDate(String target, Timestamp date);
}
