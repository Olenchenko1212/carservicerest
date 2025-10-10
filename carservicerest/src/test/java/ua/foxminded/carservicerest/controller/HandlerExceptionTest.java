package ua.foxminded.carservicerest.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

class HandlerExceptionTest {

    @Test
    void handleBindExeption_returnsProblemDetailWithErrors() {
        HandlerException handler = new HandlerException();

        BindException bindException = new BindException(new Object(), "target");
        bindException.addError(new ObjectError("target", "first error"));
        bindException.addError(new ObjectError("target", "second error"));

        ResponseEntity<ProblemDetail> response = handler.handleBindExeption(bindException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ProblemDetail body = response.getBody();
        assertNotNull(body);
        Object errorsObj = body.getProperties().get("errors");
        assertTrue(errorsObj instanceof List<?>);
        List<?> errors = (List<?>) errorsObj;
        assertEquals(2, errors.size());
        assertTrue(errors.contains("first error"));
        assertTrue(errors.contains("second error"));
    }

    @Test
    void handleNoSuchElementExeption_returnsNotFound() {
        HandlerException handler = new HandlerException();

        ResponseEntity<ProblemDetail> response = handler.handleNoSuchElementExeption(new NoSuchElementException("not found"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
