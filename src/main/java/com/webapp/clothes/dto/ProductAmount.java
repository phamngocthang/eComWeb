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
public class ProductAmount {
    @NotNull
    @NotBlank
    private String CategoryName;

    @NotNull
    private Integer count;

    @NotNull
    @NotBlank String pathImage;
}
