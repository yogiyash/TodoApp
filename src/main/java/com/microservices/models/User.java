package com.microservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @JsonIgnore
    private Integer id;
    @NotNull(message = "The age field is mandatory")
    @DecimalMin(value = "1",inclusive = true, message = "The age should be in range 1-20")
    @DecimalMax(value = "20", inclusive = true, message = "The age should be in range 1-20")
    private Integer age;
}
