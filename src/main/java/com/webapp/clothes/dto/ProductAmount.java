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

    private Integer id;
    @NotNull
    @NotBlank
    private String attributeName;

    @NotNull
    private Integer countProductOfAttribute;

    String pathImage;

    public ProductAmount(String attributeName, Integer countProductOfAttribute) {
        this.attributeName = attributeName;
        this.countProductOfAttribute = countProductOfAttribute;
    }
}
