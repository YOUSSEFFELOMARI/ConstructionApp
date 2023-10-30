package com.EnsaA.ConstructionApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionSite extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int constructionSiteId;

    private String name;

    private String Address;

    private Date startDate;
    private Date endDate;

}
