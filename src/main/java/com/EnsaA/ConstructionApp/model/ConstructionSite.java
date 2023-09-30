package com.EnsaA.ConstructionApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import static org.hibernate.annotations.CascadeType.MERGE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class ConstructionSite extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("key")
    private int constructionSiteId;

    private String name;

    private String Address;

}
