package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.service.MonthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.EnsaA.ConstructionApp.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Months")
public class MonthController {
    private final MonthService monthService;

    @GetMapping(value = "/page/{pageNum}")
    public List<MonthDto> displayAllMonths(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<MonthDto> accountPage = monthService.showAllMonths(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/page/monthsByEmployeeid/{id}")
    public List<MonthDto> getAll(@PathVariable(name = "id") int id){
        return monthService.getAllMonthsDto(id).stream().toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthDto> displayMonth(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(monthService.find(id));
    }

    @PostMapping
    public ResponseEntity<MonthDto> saveMonth(@Valid @RequestBody MonthDto monthdto) throws ParseException {
        monthService.create(monthdto);
        return ResponseEntity.status(HttpStatus.OK).body(monthdto);
    }

    @PutMapping
    public ResponseEntity<Response> updateMonth(@Valid @RequestBody MonthDto monthdto) throws ParseException {
        monthService.update(monthdto);
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
