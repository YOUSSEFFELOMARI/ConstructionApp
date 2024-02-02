package com.ensaa.constructionapp.controller;

import com.ensaa.constructionapp.model.Response;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@NoArgsConstructor
public class CustomResponse {
    public static ResponseEntity<Response> response(String message, HttpStatus status){
        Response response = new Response();
        response.setStatusCode(String.valueOf(status.value()));
        response.setStatusMsg(message);
        return ResponseEntity
                .status(status)
                .body(response);
    }
}
