package com.auction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"id"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String address;
    @NonNull
    private String login;
    private String password;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    Collection<Bid> bids;

    public User(String userName, String address, String login, String password) {
        this.userName = userName;
        this.address = address;
        this.login = login;
        this.password = password;
    }
}
