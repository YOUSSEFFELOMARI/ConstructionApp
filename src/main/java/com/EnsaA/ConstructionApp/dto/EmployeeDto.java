package com.EnsaA.ConstructionApp.dto;


import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.ConstructionSiteRepository;
import com.EnsaA.ConstructionApp.repository.MonthRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Component
public class EmployeeDto {
    private int  employerId;
    private String name;
    private String lastName;
    private String homeAddress;
    private String salary;
    private String phone;
    private Set<MonthDto> months=new HashSet<>();
    private String constructionSite;

    @JsonIgnore
    ConstructionSiteRepository constructionSiteRepository;


    public EmployeeDto toDto(Employee employee){
        MonthDto monthDto=new MonthDto();
        Set<MonthDto> monthDtos=employee.getMonths().stream().map(monthDto::toDto).collect(Collectors.toSet());
        return EmployeeDto.builder()
                .employerId(employee.getEmployeeId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .homeAddress(employee.getHomeAddress())
                .salary(String.valueOf(employee.getSalary()))
                .phone(employee.getPhone())
                .constructionSite(employee.getConstructionSite() == null ? "" : employee.getConstructionSite().getName() )
                .months(monthDtos)
                .build();
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        MonthDto monthDto=new MonthDto();
        Set<Month> monthSet=employeeDto.getMonths().stream()
                                        .map(monthDtoo -> {
                                            try {
                                                return monthDto.toEntity(monthDtoo);
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .collect(Collectors.toSet());
        return Employee.builder()
                .employeeId(employeeDto.getEmployerId())
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .homeAddress(employeeDto.getHomeAddress())
                .salary(Double.parseDouble(employeeDto.getSalary()))
                .phone(employeeDto.getPhone())
                .constructionSite(constructionSiteRepository.
                        getConstructionSiteByName(employeeDto.getConstructionSite()))
                .months(monthSet)
                .build();
    }

}
