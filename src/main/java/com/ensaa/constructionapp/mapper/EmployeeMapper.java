package com.ensaa.constructionapp.mapper;

import com.ensaa.constructionapp.dto.EmployeeDto;
import com.ensaa.constructionapp.dto.MonthDto;
import com.ensaa.constructionapp.model.ConstructionSite;
import com.ensaa.constructionapp.model.Employee;
import com.ensaa.constructionapp.model.Month;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class EmployeeMapper {

    @Autowired
    ConstructionSiteMapper constructionSiteMapper;
    @Autowired
    MonthMapper monthMapper;


    public EmployeeDto toDto(Employee employee){
        Set<MonthDto> monthDtos=employee.getMonths().stream().map(monthMapper::toDto).collect(Collectors.toSet());

        return EmployeeDto.builder()
                .employerId(employee.getEmployeeId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .homeAddress(employee.getHomeAddress())
                .salary(String.valueOf(employee.getSalary()))
                .phone(employee.getPhone())
                .constructionSiteDto(employee.getConstructionSite() == null ? null : constructionSiteMapper.toDto(employee.getConstructionSite()))
                .months(monthDtos)
                .build();
    }

    public Employee toEntity(EmployeeDto employeeDto) throws ParseException {

        Set<Month> monthSet=employeeDto.getMonths().stream()
                .map(monthDtoo -> {
                    try {
                        return monthMapper.toEntity(monthDtoo);
                    } catch (ParseException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .collect(Collectors.toSet());
        ConstructionSite Csite;
        if(employeeDto.getConstructionSiteDto() == null) Csite=null;
        else{
            Csite= constructionSiteMapper.toEntity(employeeDto.getConstructionSiteDto());

        }

        return Employee.builder()
                .employeeId(employeeDto.getEmployerId())
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .homeAddress(employeeDto.getHomeAddress())
                .salary(Double.parseDouble(employeeDto.getSalary()))
                .phone(employeeDto.getPhone())
                .constructionSite(Csite)
                .months(monthSet)
                .build();
    }
}
