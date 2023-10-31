package com.irene.Wallet.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irene.Wallet.achievement.Achievement;
import com.irene.Wallet.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "job")
    private String job;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user"})
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user"})
    private List<Achievement> achievements;

    public User(String name, int age, String job, String email, String password) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
