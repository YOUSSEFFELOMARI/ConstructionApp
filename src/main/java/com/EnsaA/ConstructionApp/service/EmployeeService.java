package com.EnsaA.ConstructionApp.service;


import com.EnsaA.ConstructionApp.dto.ConstructionSiteDto;
import com.EnsaA.ConstructionApp.dto.EmployeeDto;
import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.mapper.ConstructionSiteMapper;
import com.EnsaA.ConstructionApp.mapper.EmployeeMapper;
import com.EnsaA.ConstructionApp.mapper.MonthMapper;
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
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor

public class EmployeeService {

    private final MonthService monthService;
    private final ConstructionSiteService constructionSiteService;
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;
    private final MonthMapper monthMapper;
    private final ConstructionSiteMapper constructionSiteMapper;

    public Page<EmployeeDto> showAllEmployees(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<EmployeeDto> employeeDtoPage= employeeRepository.findAll(pageable).map(employeeMapper::toDto);
        employeeDtoPage.forEach(
                elem -> {elem.setMonths(monthService.getAllMonthsDtoforPage(elem.getEmployerId()));
                    if (elem.getConstructionSiteDto() != null) {
                        ConstructionSite constructionSite= constructionSiteService.find(elem.getConstructionSiteDto().getConstructionSiteId());
                        elem.setConstructionSiteDto(constructionSiteMapper.toDto(constructionSite));
                    }
                });
        return employeeDtoPage;
    }

    public EmployeeDto create(EmployeeDto employeedto) throws ParseException {
        Employee employee=employeeMapper.toEntity(employeedto);
        if (employeeRepository.getEmployeeByNameAndLastName(employee.getName(),employee.getLastName()) != null)
            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
        if (employeedto.getConstructionSiteDto() != null) {
            ConstructionSite constructionSite = constructionSiteService.create(employeedto.getConstructionSiteDto());
            employee.setConstructionSite(constructionSite);
        }
        createEmployee(employee);
        return employeedto;
    }

    public void createEmployee(Employee employee) throws ParseException {
        if (employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
//        constructionSiteService.create(employee.getConstructionSite());
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        List<Month> months=monthService.getAllMonths(id);
        months.forEach(mnth -> monthService.delete(mnth.getMonthId()));
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employeeRepository.deleteById(id);
    }

    public void update(EmployeeDto employeedto) throws ParseException {
        Employee employee=employeeMapper.toEntity(employeedto);
        if (!employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityNotFoundException("Employer not found - ID : "+employee.getEmployeeId()) {};
        employeedto.setMonths(new HashSet<>(monthService.getAllMonthsDto(employeedto.getEmployerId())));
        ConstructionSite constructionSite=constructionSiteMapper.toEntity(employeedto.getConstructionSiteDto());
        if (employeedto.getConstructionSiteDto().getConstructionSiteId() == 0) {
                constructionSite = constructionSiteService.create(employeedto.getConstructionSiteDto());
        }
        employee.setConstructionSite(constructionSite);
        employeeRepository.save(employee);
    }



    public EmployeeDto find(int id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
        EmployeeDto employeeDto1=employeeMapper.toDto(employee);
        employeeDto1.setMonths(new HashSet<>(monthService.getAllMonthsDto(id)));
        if (employee.getConstructionSite() != null) {
            ConstructionSite constructionSite= constructionSiteService.find(employee.getConstructionSite().getConstructionSiteId());
            employeeDto1.setConstructionSiteDto(constructionSiteMapper.toDto(constructionSite));
        }
        return employeeDto1;
    }

    public Employee findEmployer(int id){
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employer not found - ID : "+id) {});
        return employee;
    }



    public long count() {
        return employeeRepository.count();
    }
}
