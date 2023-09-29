package com.EnsaA.ConstructionApp.dto;

import com.EnsaA.ConstructionApp.model.Month;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MonthDto {
    private int monthId;
    private String date;
    private boolean isPayed;

    SimpleDateFormat formatter = new SimpleDateFormat();

    public MonthDto toDto(Month month){
        return MonthDto.builder()
                .monthId(month.getMonthId())
                .date(mapDateToFormattedDate(month.getDate()))
                .isPayed(month.isPayed())
                .build();
    }

    public Month toEntity(MonthDto monthDto) throws ParseException {
        return Month.builder()
                .monthId(monthDto.getMonthId())
                .date(mapFormattedDateToDate(monthDto.getDate()))
                .isPayed(monthDto.isPayed())
                .build();
    }
    public String mapDateToFormattedDate(Date date){
        if(date==null) return "";
        formatter.applyPattern("yyyy-MM");
        return formatter.format(date);
    }

    public Date mapFormattedDateToDate(String s) throws ParseException {
        formatter.applyPattern("yyyy-MM-dd HH:mm");
        Date date= formatter.parse(s);
        date.setHours(date.getHours()+1);
        return date;
    }
}
