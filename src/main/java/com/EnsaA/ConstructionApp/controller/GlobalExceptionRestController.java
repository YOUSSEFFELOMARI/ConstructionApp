package com.EnsaA.ConstructionApp.controller;


import com.EnsaA.ConstructionApp.model.Response;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;


@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Component.class})
@Order(1)
public class GlobalExceptionRestController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        body.put("timestamp", formatter.format(new Date()));
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> findExceptionHandler(Exception exception) {
        return response(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Response> saveExceptionHandler(Exception exception) {
        return response(exception.getMessage(), HttpStatus.CONFLICT);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> generalExceptionHandler(Exception exception) {
        return response(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> processRuntimeException(RuntimeException e) {
        return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
