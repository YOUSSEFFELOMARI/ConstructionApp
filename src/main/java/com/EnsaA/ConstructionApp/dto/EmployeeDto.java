package com.EnsaA.ConstructionApp.dto;


import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.ConstructionSiteRepository;
import com.EnsaA.ConstructionApp.repository.MonthRepository;
import com.EnsaA.ConstructionApp.service.ConstructionSiteService;
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
import java.util.Optional;
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
    private ConstructionSiteDto constructionSiteDto;

    public int getEmployerId() {
        return employerId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getSalary() {
        return salary;
    }

    public String getPhone() {
        return phone;
    }

    public Set<MonthDto> getMonths() {
        return months;
    }

    public ConstructionSiteDto getConstructionSiteDto() {
        return constructionSiteDto;
    }
}
