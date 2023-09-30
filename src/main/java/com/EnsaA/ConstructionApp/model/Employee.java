package com.EnsaA.ConstructionApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.MERGE;
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("key")
    private int  employeeId;

    private String name;
    private String lastName;
    private String homeAddress;
    private double salary;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phone;

    @Cascade(MERGE)
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Month> months=new HashSet<>();

    @Cascade(MERGE)
    @OneToOne(fetch = FetchType.EAGER,targetEntity = ConstructionSite.class)
    @JoinColumn(name = "construction_site_id" , referencedColumnName = "constructionSiteId")
    private ConstructionSite constructionSite;

}
