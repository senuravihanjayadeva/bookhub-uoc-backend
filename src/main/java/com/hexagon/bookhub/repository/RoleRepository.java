package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.ERole;
import com.hexagon.bookhub.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
