package com.bravi.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice 
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMessage = "Invalid message";
		String devMessage = ex.getCause().toString();
		List<Error> errorList = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
	}	
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = "Resource not found";
		String devMessage = ex.toString();
		List<Error> errorList = Arrays.asList(new Error(userMessage, devMessage));
		 
		return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
		
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		String userMessage = "Operation not allowed";
		String devMessage = ExceptionUtils.getRootCauseMessage(ex) ;
		List<Error> errorList = Arrays.asList(new Error(userMessage, devMessage));
		
		return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	public static class Error{
		private String userMessage;
		private String devMessage;
				
		public Error(String userMessage, String devMessage) {
			this.userMessage = userMessage;
			this.devMessage = devMessage;
		}
		
		public String getUserMessage() {
			return userMessage;
		}
		public String getDevMessage() {
			return devMessage;
		}
		
	}
}