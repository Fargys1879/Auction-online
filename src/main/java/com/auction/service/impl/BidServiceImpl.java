package com.auction.service.impl;

import com.auction.dao.BidDAO;
import com.auction.dao.ProductDAO;
import com.auction.dao.UserDAO;
import com.auction.entity.Bid;
import com.auction.entity.Product;
import com.auction.entity.ProductBidSummary;
import com.auction.entity.User;
import com.auction.service.BidService;
import com.auction.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BidDAO bidDAO;

    @Override
    @Transactional
    public boolean bidProduct(Long productUid, Long userId) {
        try {
            Product product = productDAO.findOne(productUid);
            User user = userDAO.findOne(userId);
            Bid bid = new Bid(product,user,product.getCurrentPrice());
            bidDAO.save(bid);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public List<ProductBidSummary> getProductBidSummary(Long productId) {
        return null;
    }
}
