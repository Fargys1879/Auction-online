package service;

import dao.BidDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Product;
import entity.User;

public class BidService {
    private final ProductDAO productDAO;
    private final UserDAO userDAO;
    private final BidDAO bidDAO;

    public BidService(ProductDAO productDAO, UserDAO userDAO, BidDAO bidDAO) {
        this.productDAO = productDAO;
        this.userDAO = userDAO;
        this.bidDAO = bidDAO;
    }

    public boolean bidProduct(Long product_id, String login) {
        Product product = productDAO.getProductByUid(product_id);
        User bidder = userDAO.getUserByLogin(login);
        if ((bidder != null) && (product != null)) {
            product.setBidder(bidder.getLogin());
            product.setCurrentPrice(product.getCurrentPrice()+product.getRateStep());
            productDAO.updateProductByUid(product_id,product);
            bidDAO.addBid(product_id,bidder.getId(),product.getCurrentPrice());
            return true;
        }
        return false;
    }
}
