package com.webapp.clothes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull
    private Integer id;

    @NotNull
    @NotBlank
    private String productName;

    @NotNull
    private double price;

    @NotNull
    @NotBlank
    private String color;

    @NotNull
    private String size;

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    private String description;
    @NotNull
    private int status;
}
