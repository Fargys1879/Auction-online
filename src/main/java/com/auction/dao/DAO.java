package com.auction.dao;

import java.util.List;

public interface DAO<K,E> {
    void save(E entity);
    E findOne(K key);
    E update(E entity);
    void delete(E entity);
    List<E> findAll();
}
