package com.auction.service;

import com.auction.entity.ProductBidSummary;
import com.auction.entity.User;

import java.util.List;

public interface BidService {
    boolean bidProduct(Long productUid, Long userId);
    List<ProductBidSummary> getProductBidSummary(Long productId);
}
