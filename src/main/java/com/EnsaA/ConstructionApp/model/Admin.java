package com.EnsaA.ConstructionApp.model;

import com.EnsaA.ConstructionApp.annotation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Admin extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("key")
    private int adminId;
    @NotBlank(message = "userName est obligatoire")
    private String userName;
    @Size(min=8, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String password;

    private final String Role="admin";

}
