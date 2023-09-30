package com.EnsaA.ConstructionApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.MERGE;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Month extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("key")
    private int monthId;

    private Date date;

    private boolean isPayed;

    @Cascade(MERGE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
//    @JsonBackReference
    private Employee employee;
}
