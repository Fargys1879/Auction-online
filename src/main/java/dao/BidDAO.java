package dao;

import entity.Bid;

import java.util.List;

public interface BidDAO {

    boolean addBid(Long uid, Long id,Float current_price);

    List<Bid> getAllBids();

    List<Bid> getBidsByProductUid(Long uid);
}
