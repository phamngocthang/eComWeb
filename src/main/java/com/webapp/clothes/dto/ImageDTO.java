package com.webapp.clothes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    @NotNull
    private Integer id;

    @NotNull
    @NotBlank
    private String pathLeft;
    @NotNull
    @NotBlank
    private String pathMiddle;
    @NotNull
    @NotBlank
    private String pathRight;
}
