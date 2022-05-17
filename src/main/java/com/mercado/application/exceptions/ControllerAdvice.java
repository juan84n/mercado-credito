package com.mercado.application.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mercado.domain.exceptions.BusinessException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<BusinessError> businessHandler(HttpServletRequest request, BusinessException rexc) {
		
		HttpStatus status = HttpStatus.valueOf(rexc.getCode());
        BusinessError errorInfo = new BusinessError(rexc.getCode(), rexc.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, status);
		
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<BusinessError> runtimeErrorHandler(HttpServletRequest request, RuntimeException rexc) {

        BusinessError errorInfo = new BusinessError(HttpStatus.BAD_REQUEST.value(), rexc.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<BusinessError> entityNotFoundHandler(HttpServletRequest request, EntityNotFoundException rexc) {

        BusinessError errorInfo = new BusinessError(HttpStatus.NOT_FOUND.value(), "No se encontraron resultados", request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
		
	}
}
