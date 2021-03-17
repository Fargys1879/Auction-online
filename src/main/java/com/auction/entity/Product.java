package com.auction.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"uid", "addTime"})
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    private String productName;
    private String description = "NoDescription";
    private float startPrice,rateStep,currentPrice;
    private int timeLot = 30;
    private boolean buyFlag = false;
    private String bidder = "NoBidder";
    private LocalDateTime addTime = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "product")
    Collection<Bid> bids;

    public Product(Long uid,
                   String productName,
                   String description,
                   float startPrice,
                   float rateStep,
                   float currentPrice,
                   int timeLot,
                   boolean buyFlag,
                   String bidder,
                   LocalDateTime addTime) {
        this.uid = uid;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.rateStep = rateStep;
        this.currentPrice = currentPrice;
        this.timeLot = timeLot;
        this.buyFlag = buyFlag;
        this.bidder = bidder;
        this.addTime = addTime;
    }

    public Product(String productName,
                   String description,
                   float startPrice,
                   float rateStep,
                   float currentPrice,
                   int timeLot) {
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.rateStep = rateStep;
        this.currentPrice = currentPrice;
        this.timeLot = timeLot;
    }
}
