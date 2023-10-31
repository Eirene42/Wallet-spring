package com.irene.Wallet.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irene.Wallet.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private float amount;

    @Column(name = "type")
    private String type;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"transactions"})
    private User user;

    public Transaction(String name, float amount, String type, User user) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.user = user;
    }

    public Transaction() {
    }
}
