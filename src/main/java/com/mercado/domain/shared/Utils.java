package com.mercado.domain.shared;

import com.mercado.domain.models.Loan;

public class Utils {

	/**
	 * @param loan
	 * @return
	 */
	public static double getLoanByFormule(Loan loan) {
		double rate = loan.getRate() / 12;
		double value = (rate + rate) / Math.pow((1 + rate), (loan.getTerm() - 1)) * loan.getAmount();
		return Math.round(value);
	}
}
