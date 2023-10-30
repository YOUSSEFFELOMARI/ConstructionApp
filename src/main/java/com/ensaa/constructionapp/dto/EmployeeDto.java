package com.ensaa.constructionapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
