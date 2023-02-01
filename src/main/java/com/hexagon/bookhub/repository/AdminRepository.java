package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);
}