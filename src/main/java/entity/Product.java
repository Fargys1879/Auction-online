package entity;

import java.util.Objects;

public class Product {
    private Long uid;
    private String productName,description;
    private float startPrice,rateStep,currentPrice;
    private int timeLot;
    private boolean buy_flag;
    private String bidder;

    public Product() {
    }

    public Product(Long uid, String productName, String description, float startPrice, float rateStep, int timeLot, boolean buy_flag, String bidder) {
        this.uid = uid;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.rateStep = rateStep;
        this.timeLot = timeLot;
        this.buy_flag = buy_flag;
        this.bidder = bidder;
        this.currentPrice = startPrice;
    }

    public Product(String productName, String description, float startPrice, float rateStep, int timeLot, boolean buy_flag, String bidder) {
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.rateStep = rateStep;
        this.timeLot = timeLot;
        this.buy_flag = buy_flag;
        this.bidder = bidder;
        this.currentPrice = startPrice;
    }

    public Product(String productName, String description, float startPrice, float rateStep, int timeLot, boolean buy_flag) {
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.rateStep = rateStep;
        this.timeLot = timeLot;
        this.buy_flag = buy_flag;
        this.currentPrice = startPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getRateStep() {
        return rateStep;
    }

    public void setRateStep(float rateStep) {
        this.rateStep = rateStep;
    }

    public int getTimeLot() {
        return timeLot;
    }

    public void setTimeLot(int timeLot) {
        this.timeLot = timeLot;
    }

    public boolean isBuy_flag() {
        return buy_flag;
    }

    public void setBuy_flag(boolean buy_flag) {
        this.buy_flag = buy_flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.startPrice, startPrice) == 0 && Float.compare(product.rateStep, rateStep) == 0 && timeLot == product.timeLot && buy_flag == product.buy_flag && uid.equals(product.uid) && Objects.equals(productName, product.productName) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, productName, description, startPrice, rateStep, timeLot, buy_flag);
    }


}
