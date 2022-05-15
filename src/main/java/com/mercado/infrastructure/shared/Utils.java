package com.mercado.infrastructure.shared;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;

public class Utils {
	
	public static Timestamp stringToTimestamp(String date) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    date += " 23:59:59";
	    Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(date);
		} catch (ParseException e) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "El formato de la fecha es incorrecto");
		}
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    
	    return timestamp;
	}
}
