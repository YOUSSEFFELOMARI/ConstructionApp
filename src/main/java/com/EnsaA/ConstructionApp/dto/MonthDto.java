package com.EnsaA.ConstructionApp.dto;

import com.EnsaA.ConstructionApp.model.Employee;
import com.EnsaA.ConstructionApp.model.Month;
import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class MonthDto {

    private int monthId;
    private String date;
    private boolean isPayed;
    private String EmployeeName;
    private String EmployeeLastName;

    @Autowired
    @JsonIgnore
    private EmployeeRepository employeeRepository;


    public MonthDto toDto(Month month){
        return MonthDto.builder()
                .monthId(month.getMonthId())
                .date(mapDateToFormattedDate(month.getDate()))
                .isPayed(month.isPayed())
                .EmployeeName(month.getEmployee().getName())
                .EmployeeLastName(month.getEmployee().getLastName())
                .build();
    }

    protected Employee getEmployee(MonthDto monthDto){
        return employeeRepository.getEmployeeByNameAndLastName(monthDto.getEmployeeName(),monthDto.getEmployeeLastName());
    }

    public Month toEntity(MonthDto monthDto) throws ParseException {
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
