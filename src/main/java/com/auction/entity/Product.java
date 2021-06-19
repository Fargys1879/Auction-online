package com.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"uid", "addTime"})
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long uid;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description = "NoDescription";
    @Column(name = "start_price")
    private BigDecimal startPrice;
    @Column(name = "rate_step")
    private BigDecimal rateStep;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Column(name = "time_lot")
    private int timeLot = 30;
    @Column(name = "buy_flag")
    private boolean buyFlag = false;
    @Column(name = "bidder")
    private String bidder = "NoBidder";
    @Column(name = "add_time")
    private LocalDateTime addTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "product")
    Collection<Bid> bids;

    public Product(String productName,
                   String description,
                   BigDecimal startPrice,
                   BigDecimal rateStep,
                   BigDecimal currentPrice,
                   int timeLot,
                   boolean buyFlag,
                   String bidder,
                   LocalDateTime addTime) {
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
}
