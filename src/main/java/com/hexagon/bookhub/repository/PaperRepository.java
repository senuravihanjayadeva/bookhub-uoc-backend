package com.hexagon.bookhub.repository;

import com.hexagon.bookhub.entity.Paper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaperRepository extends MongoRepository<Paper, String> {
}
