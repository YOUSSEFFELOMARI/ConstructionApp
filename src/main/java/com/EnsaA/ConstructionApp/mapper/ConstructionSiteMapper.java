package com.EnsaA.ConstructionApp.mapper;

import com.EnsaA.ConstructionApp.dto.ConstructionSiteDto;
import com.EnsaA.ConstructionApp.model.ConstructionSite;
import org.mapstruct.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public class ConstructionSiteMapper {

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
