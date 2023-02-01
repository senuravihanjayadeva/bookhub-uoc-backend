package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.GuestUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GuestUserRepository  extends MongoRepository<GuestUser, String> {
    Optional<GuestUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
