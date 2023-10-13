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

    public int getConstructionSiteId() {
        return constructionSiteId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return Address;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
