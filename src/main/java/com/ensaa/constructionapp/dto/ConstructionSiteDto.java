package com.ensaa.constructionapp.dto;

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
