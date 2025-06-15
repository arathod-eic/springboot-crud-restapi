package com.userapp.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userapp.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice // Combination of @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest req) {
		Map<String, String> fieldsMap = ex.getBindingResult().getAllErrors().stream().map(error -> (FieldError) error)
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

		ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), req.getRequestURI(),
				"Validation Failed", fieldsMap);
		return ResponseEntity.badRequest().body(errorResponse);

	}
	@ExceptionHandler(exception = UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest req) {
		ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), req.getRequestURI(),
				ex.getMessage(), new HashMap<>());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(exception = ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest req) {
		ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), req.getRequestURI(),
				ex.getMessage(), new HashMap<>());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(exception = IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handlesIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest req) {
		ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), req.getRequestURI(),
				ex.getMessage(), new HashMap<>());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
