package com.webapp.clothes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="path_left")
    private String pathLeft;

    @Column(name="path_middle")
    private String pathMiddle;

    @Column(name="path_right")
    private String pathRight;

    @OneToOne
    @JoinColumn(name = "id")
    private Product product;
}
