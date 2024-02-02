package com.ensaa.constructionapp.controller;

import com.ensaa.constructionapp.dto.EmployeeDto;
import com.ensaa.constructionapp.model.Response;
import com.ensaa.constructionapp.service.ConstructionSiteService;
import com.ensaa.constructionapp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static com.ensaa.constructionapp.controller.CustomResponse.response;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ConstructionSiteService constructionSiteService;

    @GetMapping(value = "/page/{pageNum}")
    public ResponseEntity<List<EmployeeDto>> displayAllEmployees(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<EmployeeDto> accountPage = employeeService.showAllEmployees(pageNum, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(accountPage.getContent());
    }

    @GetMapping(value = "/page")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployee(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.find(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws ParseException {
        employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    @PutMapping
    public ResponseEntity<Response> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws ParseException {
        employeeService.update(employeeDto);
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
    @GetMapping("/searchEmplyees")
    public  ResponseEntity<List<EmployeeDto>> FindEmployees(@RequestParam("name") String name,@RequestParam("date") String date){
        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployees(1,20,name,date));
    }
}
