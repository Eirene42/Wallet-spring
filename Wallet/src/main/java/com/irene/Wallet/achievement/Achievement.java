package com.irene.Wallet.achievement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irene.Wallet.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "achievements")
@Getter
@Setter
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"achievements"})
    private User user;

    public Achievement(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Achievement() {
    }
}
