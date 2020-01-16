package com.atl.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
@Component
public class GenericExceptionHandler {
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleAllNotFoundExceptions(NotFoundException ex) {
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", ex.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

}
