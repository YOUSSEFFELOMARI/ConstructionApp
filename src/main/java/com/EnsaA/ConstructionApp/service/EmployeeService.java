package com.EnsaA.ConstructionApp.service;


import com.EnsaA.ConstructionApp.dto.EmployeeDto;
import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeService {

    private final MonthService monthService;
    private final ConstructionSiteService constructionSiteService;
    private final EmployeeRepository employeeRepository;

    private final EmployeeDto employeeDto;
    private final MonthDto monthDto;

    public Page<EmployeeDto> showAllEmployees(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return employeeRepository.findAll(pageable).map(employeeDto::toDto);
    }

    public void create(Employee employee) {
        if (employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
        Month month= employee.getMonths().stream().toList().get(0);
        System.out.println(month.getEmployee().getName());
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employeeRepository.deleteById(id);
    }

    public void update(Employee employee) {
        if (!employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityNotFoundException("Employer not found - ID : "+employee.getEmployeeId()) {};
        employeeRepository.save(employee);
    }



    public EmployeeDto find(int id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
//        employee.setMonths(monthService.getAllMonths(id).stream().map(a-> {
//            try {
//                return monthDto.toEntity(a);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }).collect(Collectors.toSet()));
        EmployeeDto employeeDto1=employeeDto.toDto(employee);
        employeeDto1.setMonths(new HashSet<>(monthService.getAllMonths(id)));
        return employeeDto1;
    }

//    public Employee getEmployeeByNameAndLasNameFun(String name,String lastName){
//        return employeeRepository.getEmployeeByNameAndLastName(name,lastName);
//    }
    public long count() {
        return employeeRepository.count();
    }
}
