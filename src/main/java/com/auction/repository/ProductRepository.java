package com.auction.repository;

import com.auction.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
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
