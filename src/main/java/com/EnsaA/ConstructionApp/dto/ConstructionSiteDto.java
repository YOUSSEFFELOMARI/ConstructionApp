package com.EnsaA.ConstructionApp.dto;

import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.Month;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class ConstructionSiteDto {

    private int constructionSiteId;
    private String name;
    private String Address;
    private String startDate;
    private String endDate;

    public ConstructionSiteDto toDto(ConstructionSite constructionSite){
        return ConstructionSiteDto.builder()
                .constructionSiteId(constructionSite.getConstructionSiteId())
                .name(constructionSite.getName())
                .Address(constructionSite.getAddress())
                .startDate(mapDateToFormattedDate(constructionSite.getStartDate()))
                .endDate(mapDateToFormattedDate(constructionSite.getEndDate()))
                .build();
    }

    public ConstructionSite toEntity(ConstructionSiteDto constructionSiteDto) throws ParseException {
        return ConstructionSite.builder()
                .constructionSiteId(constructionSiteDto.getConstructionSiteId())
                .name(constructionSiteDto.getName())
                .Address(constructionSiteDto.getAddress())
                .startDate(mapFormattedDateToDate(constructionSiteDto.getStartDate()))
                .endDate(mapFormattedDateToDate(constructionSiteDto.getEndDate()))
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
