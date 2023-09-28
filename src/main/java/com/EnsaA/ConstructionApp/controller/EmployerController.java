package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.model.Employer;
import com.EnsaA.ConstructionApp.model.Response;
import com.EnsaA.ConstructionApp.service.EmployerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Employers")
public class EmployerController {
    private final EmployerService employerService;

    @GetMapping(value = "/page/{pageNum}")
    public List<Employer> displayAllEmployers(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<Employer> accountPage = employerService.showAllEmplyers(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> displayEmployers(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.find(id));
    }

    @PostMapping
    public ResponseEntity<Employer> saveEmployers(@Valid @RequestBody Employer admin) {
        employerService.create(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PutMapping
    public ResponseEntity<Response> updateEmployers(@Valid @RequestBody Employer admin) {
        employerService.update(admin);
        return response("Employer successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEmployers(@PathVariable int id) {
        employerService.delete(id);
        return response("Employer successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEmployers(){
        return ResponseEntity.status(HttpStatus.OK).body(employerService.count());
    }
}
