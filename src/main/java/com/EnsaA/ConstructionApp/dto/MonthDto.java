package com.EnsaA.ConstructionApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


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



    public int getMonthId() {
        return monthId;
    }

    public String getDate() {
        return date;
    }

    public boolean isPayed() {
        return isPayed;
    }


}
