package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.AudioBook;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface AudioBookRepository extends MongoRepository<AudioBook, String> {
}
