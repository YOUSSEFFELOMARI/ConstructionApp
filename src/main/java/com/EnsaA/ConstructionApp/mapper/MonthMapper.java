package com.EnsaA.ConstructionApp.mapper;

import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public class MonthMapper {

    @Autowired
    @JsonIgnore
    private EmployeeRepository employeeRepository;


    public com.EnsaA.ConstructionApp.dto.MonthDto toDto(Month month){
        return com.EnsaA.ConstructionApp.dto.MonthDto.builder()
                .monthId(month.getMonthId())
                .date(mapDateToFormattedDate(month.getDate()))
                .isPayed(month.isPayed())
                .EmployeeName(month.getEmployee().getName())
                .EmployeeLastName(month.getEmployee().getLastName())
                .build();
    }

    protected Employee getEmployee(com.EnsaA.ConstructionApp.dto.MonthDto monthDto){
        return employeeRepository.getEmployeeByNameAndLastName(monthDto.getEmployeeName(),monthDto.getEmployeeLastName());
    }

    public Month toEntity(com.EnsaA.ConstructionApp.dto.MonthDto monthDto) throws ParseException {
        return Month.builder()
                .monthId(monthDto.getMonthId())
                .date(mapFormattedDateToDate(monthDto.getDate()))
                .isPayed(monthDto.isPayed())
                .employee(getEmployee(monthDto))
                .build();
    }
    public String mapDateToFormattedDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        if(date==null) return "";
        formatter.applyPattern("yyyy-MM-dd");
        return formatter.format(date);
    }

    public Date mapFormattedDateToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern("yyyy-MM-dd");
        Date date= formatter.parse(s);
        date.setHours(date.getHours()+1);
        return date;
    }
}
