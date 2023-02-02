package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.PhysicalBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhysicalBookRepository extends MongoRepository<PhysicalBook, String> {
}
