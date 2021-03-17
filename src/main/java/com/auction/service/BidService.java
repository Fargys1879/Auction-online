package com.auction.service;

import com.auction.entity.UserBid;

import java.util.List;

public interface BidService {
    boolean bidProduct(Long productUid,String login);
    List<UserBid> getBidsByUserId(Long userId);
}
