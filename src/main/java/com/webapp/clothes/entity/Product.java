package com.webapp.clothes.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name= "color", length = 10, nullable = false)
    private String color;
    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "brand", length = 20, nullable = false)
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Size(min = 0, max = 0 )
    private int status;

    @OneToMany(mappedBy = "product")
    private List<BillDetail> billdetails;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    private List<FeedBack> feedBacks;

    @OneToOne(mappedBy = "product")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;
}
