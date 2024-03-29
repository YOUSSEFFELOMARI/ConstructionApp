package com.ensaa.constructionapp.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.*;

@Slf4j
@EqualsAndHashCode(callSuper = true, exclude = "{Month}")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "employee_id")
    private int  employeeId;

    private String name;
    private String lastName;
    private String homeAddress;
    private double salary;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phone;

    @Cascade({MERGE,PERSIST})
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Month> months=new HashSet<>();

    @Cascade({MERGE,PERSIST})
    @OneToOne(fetch = FetchType.EAGER,targetEntity = ConstructionSite.class)
    @JoinColumn(name = "construction_site_id" , referencedColumnName = "constructionSiteId")
    private ConstructionSite constructionSite;

}
