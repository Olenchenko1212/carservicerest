package ua.foxminded.carservicerest.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "ua.foxminded.carservicerest")
public class HandlerException extends ResponseEntityExceptionHandler {

}
