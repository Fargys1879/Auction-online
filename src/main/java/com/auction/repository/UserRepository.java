package com.auction.repository;

import com.auction.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);

    UserDetails findByLogin(String login);

    @Override
    Iterable<User> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void delete(User user);

    @Override
    boolean existsById(Long id);
}
