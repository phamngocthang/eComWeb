package com.webapp.clothes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Inforaccount")
public class InforAccount {

    @Id
    private String userName;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;
    @OneToOne()
    @JoinColumn(name="userName")
    private Account account;

    @OneToMany(mappedBy = "inforAccount", cascade = CascadeType.ALL)
    private List<FeedBack> feedbacks;
}
