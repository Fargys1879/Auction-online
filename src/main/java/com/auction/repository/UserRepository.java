package com.auction.repository;

import com.auction.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userRepository")
public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);

    @Override
    Iterable<User> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void delete(User user);

    @Override
    boolean existsById(Long id);
}
