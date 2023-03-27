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
public class BillAmount {
    @NotNull
    @NotBlank
    private Integer productId;

    @NotNull
    private Integer sumAmount;
}
