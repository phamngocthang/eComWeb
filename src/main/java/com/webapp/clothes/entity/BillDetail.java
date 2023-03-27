package com.webapp.clothes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "billdetail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="amount", nullable = false)
    @Size(min = 0, max = 10)
    private Integer amount;

    @ManyToOne()
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bill bill;
}
