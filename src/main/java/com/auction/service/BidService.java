package com.auction.service;

import com.auction.entity.ProductBidSummary;

import java.util.List;

public interface BidService {
    boolean bidProduct(Long productUid, Long userId);
    List<ProductBidSummary> getProductBidSummary(Long productId);
}
