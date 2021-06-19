package com.auction.controller;

import com.auction.entity.Category;
import com.auction.entity.Product;
import com.auction.service.CategoryService;
import com.auction.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/products")
@Data
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @Transactional
    public String getProductsPage(Model model) {
        categoryService.addCategory(new Category("MainCategory"));
        Product product = new Product("NewProduct","New Product",new BigDecimal(1050.2),new BigDecimal(100.2),new BigDecimal(1050.2),24,true,"bidder", LocalDateTime.now());
        Category category = categoryService.getCategoryById(1L);
        product.setCategory(category);
        productService.addProduct(product);
        List<Product> allProductsList = productService.getAllProducts();
        model.addAttribute("allProductsList",allProductsList);
        return "products";
    }

    public List<Product> getProductsList() {
        List<Product> allProductsList = productService.getAllProducts();
        return allProductsList;
    }

    @GetMapping("/add")
    public String getAddProductPage() {
        return "product-add";
    }

    @GetMapping("/{uid}")
    public String getProductDetails(@PathVariable(value = "uid") Long uid,
                                    Model model) {
        Product product = productService.getProductByUid(uid);
        model.addAttribute("product",product);
        return "product-details";
    }

}
