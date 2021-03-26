package com.auction.service.impl;

import com.auction.entity.Bid;
import com.auction.entity.Product;
import com.auction.entity.User;
import com.auction.entity.UserBid;
import com.auction.repository.BidRepository;
import com.auction.repository.ProductRepository;
import com.auction.service.BidService;
import com.auction.service.ProductService;
import com.auction.service.ServiceException;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean bidProduct(Long productUid, String login) {
        Product product = productService.getProductByUid(productUid);
        try {
            User bidder = userService.getUserByLogin(login);
            if ((bidder != null) && (product != null)) {
                product.setBidder(bidder.getLogin());
                product.setCurrentPrice(product.getCurrentPrice()+product.getRateStep());
                productRepository.save(product);
                Bid bid = new Bid(product,bidder,product.getCurrentPrice());
                bidRepository.save(bid);
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException("bidProduct()",e);
        }
        return false;
    }

    @Override
    public List<UserBid> getBidsByUserId(Long userId) {
        List<UserBid> result = new ArrayList<>();
        try {
            Iterable<Bid> allBids = bidRepository.findAll();
            for (Bid bid : allBids) {
                if (bid.getUser().getId().equals(userId)) {
                    Long userBidId = bid.getId();
                    Long productUid = bid.getProduct().getUid();
                    float price = bid.getPrice();
                    String productName = bid.getProduct().getProductName();
                    String description = bid.getProduct().getDescription();
                    result.add(new UserBid(userBidId,productUid,userId,price,productName,description));
                }
            }
        } catch (Exception e) {
            throw new ServiceException("getBidsByUserId()",e);
        }
        return result;
    }
}
