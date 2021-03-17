package com.auction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"id"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName,adress,login,password;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    Collection<Bid> bids;

    public User(String userName) {
        this.userName = userName;
    }

    public User(Long id, String userName, String adress, String login, String password) {
        this.id = id;
        this.userName = userName;
        this.adress = adress;
        this.login = login;
        this.password = password;
    }

    public User(String userName, String adress, String login, String password) {
        this.userName = userName;
        this.adress = adress;
        this.login = login;
        this.password = password;
    }
}
