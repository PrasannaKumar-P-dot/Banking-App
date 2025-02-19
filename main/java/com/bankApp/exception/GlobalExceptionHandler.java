package com.bankApp.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeException(ResourceNotFoundException exception){
		Map<String, Object> map = new HashMap<>();
		
		map.put("timestamp", LocalDate.now());
		map.put("message", exception.getMessage());
		map.put("status", HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		
	}
	

}
