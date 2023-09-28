package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.service.MonthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
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
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Months")
public class MonthEmployer {
    private final MonthService monthService;

    @GetMapping(value = "/page/{pageNum}")
    public List<Month> displayAllMonths(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<Month> accountPage = monthService.showAllMonths(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Month> displayMonth(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(monthService.find(id));
    }

    @PostMapping
    public ResponseEntity<Month> saveMonth(@Valid @RequestBody Month admin) {
        monthService.create(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PutMapping
    public ResponseEntity<Response> updateMonth(@Valid @RequestBody Month admin) {
        monthService.update(admin);
        return response("Month successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteMonth(@PathVariable int id) {
        monthService.delete(id);
        return response("Month successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countMonth(){
        return ResponseEntity.status(HttpStatus.OK).body(monthService.count());
    }
}
