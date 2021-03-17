package com.auction.controller;

import com.auction.entity.Product;
import com.auction.service.BidService;
import com.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BidService bidService;

    @GetMapping("/products")
    public String getProductssPage(Model model) {
        List<Product> allProductsList = productService.getAllProducts();
        model.addAttribute("products",allProductsList);
        return "products";
    }

    @PostMapping("/products")
    public String postBidProductsPage(@RequestParam String uid,
                                   String userLogin) {
        Long productUid = Long.parseLong(uid);
        bidService.bidProduct(productUid,userLogin);
        return "redirect:/products";
    }

    @GetMapping("/product/add")
    public String getAddProductPage() {
        return "product-add";
    }

    @PostMapping("/product/add")
    public String postAddProductPage(@RequestParam String productName,
                                     String description,
                                     String startPrice,
                                     String rateStep) {
        Product newProduct = new Product();
        newProduct.setProductName(productName);
        if (!description.equals("")) {newProduct.setDescription(description);}
        newProduct.setStartPrice(Float.parseFloat(startPrice));
        newProduct.setRateStep(Float.parseFloat(rateStep));
        newProduct.setAddTime(LocalDateTime.now());
        boolean userCheck = productService.checkProductExist(newProduct);
        if (!userCheck) {
            productService.addProduct(newProduct);
        }
        return "redirect:/products";
    }

    @GetMapping("/product/{uid}")
    public String getProductDetails(@PathVariable(value = "uid") Long uid, Model model) {
        Product product = productService.getProductByUid(uid);
        model.addAttribute("product",product);
        return "product-details";
    }

    @PostMapping("/product/{uid}/save")
    public String postProductDetails(@PathVariable(value = "uid") Long uid,
                                     @RequestParam String productName,
                                     String description,
                                     String startPrice,
                                     String rateStep) {
        Product product = productService.getProductByUid(uid);
        product.setProductName(productName);
        product.setDescription(description);
        product.setStartPrice(Float.parseFloat(startPrice));
        product.setStartPrice(Float.parseFloat(rateStep));
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/product/{uid}/delete")
    public String postProductDetails(@PathVariable(value = "uid") Long uid) {
        productService.deleteProductByUid(uid);
        return "redirect:/products";
    }
}
