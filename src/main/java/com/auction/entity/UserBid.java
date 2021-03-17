package com.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBid {
    private Long bidId;
    private Long productId;
    private Long userId;
    private float price;
    private String productName,description;
}
