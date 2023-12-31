package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.dto.EmployeeDto;
import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Response;
import com.EnsaA.ConstructionApp.service.EmployeeService;
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
    @RequestMapping("/api/v1/Employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/page/{pageNum}")
    public List<EmployeeDto> displayAllEmployees(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<EmployeeDto> accountPage = employeeService.showAllEmployees(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployee(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.find(id));
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee admin) {
        employeeService.create(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PutMapping
    public ResponseEntity<Response> updateEmployee(@Valid @RequestBody Employee admin) {
        employeeService.update(admin);
        return response("Employee successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEmployees(@PathVariable int id) {
        employeeService.delete(id);
        return response("Employee successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEmployers(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.count());
    }
}
