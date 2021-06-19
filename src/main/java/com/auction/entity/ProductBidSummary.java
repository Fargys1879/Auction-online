package com.auction.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select p.UID as PRODUCTUID, p.PRODUCT_NAME as NAME, " +
                "count(b.ID) as NUMBEROFBIDS " +
                "from PRODUCTS p left outer join BIDS b on p.U_ID = b.PRODUCT_ID " +
                "group by i.ID, i.ITEM_NAME"
)
@org.hibernate.annotations.Synchronize({"products", "bids"})
public class ProductBidSummary {
    @Id
    protected Long productId;
    protected String productName;
    protected long numberOfBids;
}
