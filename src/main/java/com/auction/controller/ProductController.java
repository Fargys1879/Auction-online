package com.auction.controller;

import com.auction.entity.Product;
import com.auction.entity.User;
import com.auction.service.BidService;
import com.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BidService bidService;

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        List<Product> allProductsList = productService.getAllProducts();
        model.addAttribute("products",allProductsList);
        return "products";
    }

    @PostMapping("/products")
    public String postBidProductsPage(@RequestParam String uid,
                                      @AuthenticationPrincipal User user) throws Exception {
        try {
            String userLogin = user.getLogin();
            Long productUid = Long.parseLong(uid);
            bidService.bidProduct(productUid,userLogin);
        } catch (Exception e) {
            throw new Exception("Exception in postBidProductsPage()",e);
        }
        return "redirect:/products";
    }

    @GetMapping("/product/add")
    public String getAddProductPage() {
        return "product-add";
    }

    @GetMapping("/product/{uid}")
    public String getProductDetails(@PathVariable(value = "uid") Long uid,
                                    Model model) {
        Product product = productService.getProductByUid(uid);
        model.addAttribute("product",product);
        return "product-details";
    }
}
