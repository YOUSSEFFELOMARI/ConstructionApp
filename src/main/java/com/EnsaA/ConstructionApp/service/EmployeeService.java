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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
//        employeeDtoPage.forEach(
//                elem -> {elem.setMonths(monthService.getAllMonthsDtoforPage(elem.getEmployerId()));
//                    if (elem.getConstructionSiteDto() != null) {
//                        ConstructionSite constructionSite= constructionSiteService.find(elem.getConstructionSiteDto().getConstructionSiteId());
//                        elem.setConstructionSiteDto(constructionSiteMapper.toDto(constructionSite));
//                    }
//                });
        return employeeDtoPage;
    }

    public EmployeeDto create(EmployeeDto employeedto) throws ParseException {
        System.out.println("hello in service to add employeee");
        Employee employee=employeeMapper.toEntity(employeedto);
        System.out.println("the employee recevied is: "+employee);
        if (employeeRepository.getEmployeeByNameAndLastName(employee.getName(),employee.getLastName()) != null)
            throw new EntityExistsException("Employer already stored in database : "+employee.getName()+" "+employee.getLastName()) {};
//        if (employeedto.getMonths() != null) {
//            employeedto.getMonths().forEach(
//                    mdto -> {
//                        try {
//                            Month month = monthService.create(mdto);
//                            employee.setMonths(Set.of(month));
//                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//            );
//        }
//        if (employeedto.getConstructionSiteDto() != null) {
//            ConstructionSite constructionSite = constructionSiteService.create(employeedto.getConstructionSiteDto());
//            employee.setConstructionSite(constructionSite);
//        }
//        createEmployee(employee);
        System.out.println("---------tryin to save employeeeee------");
        employeeRepository.save(employee);

        return employeedto;
    }

//    public void createEmployee(Employee employee) throws ParseException {
////        if (employeeRepository.existsById(employee.getEmployeeId()))
////            throw new EntityExistsException("Employer already stored in database - ID : "+employee.getEmployeeId()) {};
//    }

    public void delete(int id) {
        List<Month> months=monthService.getAllMonths(id);
        months.forEach(mnth -> monthService.delete(mnth.getMonthId()));
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException("Employer not found - ID : "+id) {};
        employeeRepository.deleteById(id);
    }

    public void update(EmployeeDto employeedto) throws ParseException {
        Set<Month> months= new HashSet<>(monthService.getAllMonths(employeedto.getEmployerId()));
        Employee employee=employeeMapper.toEntity(employeedto);

        if (!employeeRepository.existsById(employee.getEmployeeId()))
            throw new EntityNotFoundException("Employer not found - ID : "+employee.getEmployeeId()) {};
            employee.setMonths(months);

        if (employeedto.getMonths() != null) {
            employeedto.getMonths().forEach(
                    mdto -> {
                        try {
                            months.add(monthMapper.toEntity(mdto));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        employee.setMonths(months);

//        employeedto.setMonths(new HashSet<>(monthService.getAllMonthsDto(employeedto.getEmployerId())));
//        ConstructionSite constructionSite=constructionSiteMapper.toEntity(employeedto.getConstructionSiteDto());
//        if (employeedto.getConstructionSiteDto().getConstructionSiteId() == 0) {
//                constructionSite = constructionSiteService.create(employeedto.getConstructionSiteDto());
//        }
//        employee.setConstructionSite(constructionSite);
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

    public  List<EmployeeDto> findEmployees(int pageNum, int pageSize, String constructionName , String date){
        System.out.println("find employeees name is "+constructionName+" date is :"+date);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<EmployeeDto> employeeDtoPage= employeeRepository.findAll(pageable).map(employeeMapper::toDto);
        List<EmployeeDto> employees =employeeDtoPage.getContent();
        List<EmployeeDto> filteredEmployees = employees.stream()
                .filter(employee -> employee.getConstructionSiteDto().getName().equals(constructionName))
                .filter(employee -> employee.getMonths().stream().anyMatch(month -> month.getDate().equals(date)))
                .peek(employee-> {
                    Set<MonthDto> filteredMonths=employee.getMonths().stream().filter(month->month.getDate().equals(date))
                            .collect(Collectors.toSet());

                    employee.setMonths(filteredMonths);
                })
                .collect(Collectors.toList());

        return filteredEmployees;

    }

}
