package com.webapp.clothes.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @ManyToOne()
    @JoinColumn(
        name = "userName", referencedColumnName = "userName"
    )
    private Account account;

    @OneToMany(mappedBy="bill")
    private List<BillDetail> billdetails;
}
