package com.auction.repository;

import com.auction.entity.Product;
import com.sun.istack.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("productRepository")
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long uid);

    @Override
    boolean existsById(Long uid);

    @Override
    Iterable<Product> findAll();

    @Override
    void deleteById(Long uid);

    @Override
    void delete(Product product);

    @Override
    void deleteAll();

}
