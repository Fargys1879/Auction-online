package com.auction.repository;

import com.auction.entity.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service("bidRepository")
public interface BidRepository extends CrudRepository<Bid,Long> {
    @Override
    Bid save(Bid bid);

    @Override
    Iterable<Bid> findAll();
}
