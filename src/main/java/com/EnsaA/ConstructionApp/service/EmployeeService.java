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
import java.util.List;
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

    public EmployeeDto create(EmployeeDto employeedto) throws ParseException {
        Employee employee=employeeDto.toEntity(employeedto);
        if (employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
        createEmployee(employee);
        return employeedto;
    }

    public Employee createEmployee(Employee employee){
        if (employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
        return employeeRepository.save(employee);
    }

    public void delete(int id) {
        List<Month> months=monthService.getAllMonths(id);
        months.forEach(mnth -> monthService.delete(mnth.getMonthId()));
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employeeRepository.deleteById(id);
    }

    public void update(EmployeeDto employeedto) {
        Employee employee=employeeDto.toEntity(employeedto);
        if (!employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityNotFoundException("Employer not found - ID : "+employee.getEmployeeId()) {};
        employeeRepository.save(employee);
    }



    public EmployeeDto find(int id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
        EmployeeDto employeeDto1=employeeDto.toDto(employee);
        employeeDto1.setMonths(new HashSet<>(monthService.getAllMonthsDto(id)));
        return employeeDto1;
    }

    public long count() {
        return employeeRepository.count();
    }
}
