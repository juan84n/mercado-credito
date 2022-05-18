package com.mercado.application.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mercado.domain.exceptions.BusinessException;

/**
 * @author juanfelipenarvaez
 * 
 *  Controlador que intercepta las excepciones
 *
 */
@RestControllerAdvice
public class ControllerAdvice {

	/**
	 * 
	 * Interceptor de BusinessException
	 * @param request
	 * @param rexc
	 * @return
	 */
	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<BusinessError> businessHandler(HttpServletRequest request, BusinessException rexc) {
		
		HttpStatus status = HttpStatus.valueOf(rexc.getCode());
        BusinessError errorInfo = new BusinessError(rexc.getCode(), rexc.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, status);
		
	}
	
	/**
	 * 
	 * Interceptor de RuntimeException
	 * 
	 * @param request
	 * @param rexc
	 * @return
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<BusinessError> runtimeErrorHandler(HttpServletRequest request, RuntimeException rexc) {

        BusinessError errorInfo = new BusinessError(HttpStatus.BAD_REQUEST.value(), rexc.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
		
	}
	
	/**
	 * 
	 * Interceptor de EntityNotFoundException
	 * 
	 * @param request
	 * @param rexc
	 * @return
	 */
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<BusinessError> entityNotFoundHandler(HttpServletRequest request, EntityNotFoundException rexc) {

        BusinessError errorInfo = new BusinessError(HttpStatus.NOT_FOUND.value(), "No se encontraron resultados", request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
		
	}
}
