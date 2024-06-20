package ua.foxminded.carservicerest.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ProblemDetail> handleBindExeption(BindException exception) {

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "errors.400");
		problemDetail.setProperty("errors",
				exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
		return ResponseEntity.badRequest().body(problemDetail);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ProblemDetail> handleNoSuchElementExeption(NoSuchElementException exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage()));
	}
}
