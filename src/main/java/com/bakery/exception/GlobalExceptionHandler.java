package com.bakery.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bakery.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		headers.add("desc", "HTTP method not supported");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		headers.add("desc", "Media Type not supported");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		headers.add("desc", "Path Variable is Missing");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		headers.add("desc", "Invalid Data Type");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleHotelNotFound(OrderNotFoundException ex){
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Order Not Found");
		String message = ex.getMessage();
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleHotelNotFound(ProductNotFoundException ex){
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Product Not Found");
		String message = ex.getMessage();
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		headers.add("desc", "Internal Server Error");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, status);
		return ResponseEntity.status(status).headers(headers).body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "exception occured...");
		String message = ex.getMessage();
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), message, HttpStatus.EXPECTATION_FAILED);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).headers(headers).body(errors);
	}
	
}
