package entity;

public class Bid {
    private Long bid_id,product_id,user_id;
    private float price;

    public Long getBid_id() {
        return bid_id;
    }

    public void setBid_id(Long bid_id) {
        this.bid_id = bid_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
