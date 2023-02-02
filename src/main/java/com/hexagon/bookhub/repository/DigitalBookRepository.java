package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.DigitalBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DigitalBookRepository  extends MongoRepository<DigitalBook, String> {
}
