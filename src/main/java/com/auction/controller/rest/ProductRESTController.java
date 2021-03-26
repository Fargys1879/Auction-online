package com.auction.controller.rest;

import com.auction.entity.Product;
import com.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Response addProduct(@Valid @RequestBody Product product) throws ExceptionRest {
        Response response;
        boolean productCheck = productService.checkProductExist(product);
        if (productCheck) { throw new ExceptionRest("Product Already Exist"); }
        productService.addProduct(product);
        response = new Response(HttpStatus.OK,"Product Add succes",product);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("message","BadFormData");
        });
        return errors;
    }

    @PutMapping("/{uid}/save")
    public Response updateProduct(@PathVariable(value = "uid") Long uid,
                                  @Valid @RequestBody Product product) throws ExceptionRest {
        Response response;
        Product productFromDb;
        productFromDb = productService.getProductByUid(uid);
        if (productFromDb == null) throw new ExceptionRest("Don't exist product with same id");
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setStartPrice(product.getStartPrice());
        productFromDb.setRateStep(product.getRateStep());
        boolean updateProduct = productService.updateProduct(productFromDb);
        if (!updateProduct) { throw new ExceptionRest("Can't Update product");}
        response = new Response(HttpStatus.OK,"Product Update sucess",productFromDb);
        return response;
    }

    @DeleteMapping("/{uid}/delete")
    public Response deleteProduct(@PathVariable(value = "uid") Long uid) throws ExceptionRest {
        Response response;
        boolean succes = productService.deleteProductByUid(uid);
        if (!succes) throw new ExceptionRest("Product already deleted");
        response = new Response(HttpStatus.OK,"Product Delete success",uid);
        return response;
    }
}
