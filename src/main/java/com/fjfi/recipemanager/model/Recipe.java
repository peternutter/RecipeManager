package com.fjfi.recipemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank()
    private String category;
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotBlank(message = "Description is mandatory")
    private String description;


    @NotNull(message = "Ingredients shouldn't be null")
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> ingredients;

    @NotNull(message = "Directions shouldn't be null")
    @Size(min = 1, message = "Minimal size should be 1")
    @ElementCollection
    private List<String> directions;


}
