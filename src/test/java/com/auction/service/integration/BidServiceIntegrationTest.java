package com.auction.service.integration;

import com.auction.AuctionApp;
import com.auction.service.BidService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuctionApp.class)
public class BidServiceIntegrationTest {
    @Autowired
    private BidService bidService;

    @Test
    public void bidProduct_ShouldReturn_True() {
        Assert.assertTrue(bidService.bidProduct(1L,"evg123"));
    }
}