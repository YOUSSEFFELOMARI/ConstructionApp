package com.EnsaA.ConstructionApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.MERGE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Employer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("key")
    private int  employerId;

    private String name;
    private String lastName;
    private String homeAddress;
    private double salary;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phone;

    @Cascade(MERGE)
    @OneToMany(mappedBy = "employer")
    private Set<Month> months=new HashSet<>();

    @Cascade(MERGE)
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ConstructionSite.class)
    @JoinColumn(name = "construction_site_id",referencedColumnName = "constructionSiteId")
    private ConstructionSite constructionSite;

}
