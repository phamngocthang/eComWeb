package com.webapp.clothes.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @Column(nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    private int isAdmin;

    @OneToMany(mappedBy = "account")
    private List<Bill> bills;

    @OneToOne(mappedBy = "account")
    private InforAccount inforAccount;

    @OneToMany(mappedBy = "account")
    private List<Cart> carts;

}
